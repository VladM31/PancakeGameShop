package nure.pancake.game.shop.authorizationservice.services;

import lombok.Builder;
import lombok.RequiredArgsConstructor;
import nure.pancake.game.shop.authorizationservice.clients.TelegramClient;
import nure.pancake.game.shop.authorizationservice.dataobjects.*;
import nure.pancake.game.shop.authorizationservice.entities.AuthCodeEntity;
import nure.pancake.game.shop.authorizationservice.entities.UserEntity;
import nure.pancake.game.shop.authorizationservice.exceptions.LoginException;
import nure.pancake.game.shop.authorizationservice.mappers.LoginResultUserMapper;
import nure.pancake.game.shop.authorizationservice.mappers.UserEntityMapper;
import nure.pancake.game.shop.authorizationservice.repositories.AuthCodeRepository;
import nure.pancake.game.shop.authorizationservice.repositories.UserRepository;
import nure.pancake.game.shop.authorizationservice.search.criteria.AuthCodeSearchCriteria;
import nure.pancake.game.shop.authorizationservice.search.criteria.BookedUser;
import nure.pancake.game.shop.authorizationservice.search.criteria.UserSearchCriteria;
import nure.pancake.game.shop.authorizationservice.utils.Range;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;

@Builder
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private static final String ERROR_LOGIN_MESSAGE = "Phone number or password is not correct";
    private static final Object SIGN_UP_MONITOR = new Object();

    private final TelegramClient telegramClient;
    private final CodeGenerator codeGenerator;
    private final TokenService tokenService;
    private final UserRepository userRepository;
    private final AuthCodeRepository codeRepository;
    private final UserEntityMapper<SignUpUser> entityMapper;
    private final PasswordEncoder passwordEncoder;
    private final LoginResultUserMapper loginUserMapper;
    private final String confirmUrl;

    @Override
    public boolean signUp(SignUpUser u) {
        if (isBooked(u.getPhoneNumber(), u.getEmail())) {
            return false;
        }

        UserEntity user;
        synchronized (SIGN_UP_MONITOR) {
            if (isBooked(u.getPhoneNumber(), u.getEmail())) {
                return false;
            }
            var userForSave = entityMapper.toUserEntity(u);
            userForSave.setPassword(passwordEncoder.encode(userForSave.getPassword()));
            user = userRepository.save(userForSave);
            if (user.getId() == null) {
                return false;
            }
        }

        var code = codeRepository.save(buildCode(user.getId()));
        if (code.getId() == null) {
            return false;
        }

        var message = ConfirmMessage
                .builder()
                .code(code.getValue())
                .phoneNumber(user.getPhoneNumber())
                .callBackUrl(confirmUrl)
                .build().toMessage();

        return telegramClient.sendSurveyMessage(message);
    }

    public AuthCodeEntity buildCode(Long userId) {
        return new AuthCodeEntity(null, codeGenerator.create(), LocalDateTime.now(), true, userId);
    }

    @Override
    public LoginResult login(LoginUser u) {
        var user = userRepository.findOne(
                UserSearchCriteria
                        .builder()
                        .userState(UserState.REGISTERED)
                        .phoneNumberEq(u.getPhoneNumber())
                        .active(true)
                        .build()
        ).orElseThrow(() -> new LoginException(ERROR_LOGIN_MESSAGE));

        if (!passwordEncoder.matches(u.getPassword(), user.getPassword())) {
            throw new LoginException(ERROR_LOGIN_MESSAGE);
        }
        var token = tokenService.generate(Token.User
                .builder()
                .id(user.getId())
                .phoneNumber(user.getPhoneNumber())
                .build());


        return LoginResult.builder().token(token)
                .user(loginUserMapper.toLoginUser(user))
                .build();
    }

    @Override
    public boolean isRegistered(String phoneNumber) {
        return userRepository.exists(
                UserSearchCriteria.builder()
                        .phoneNumberEq(phoneNumber)
                        .userState(UserState.REGISTERED)
                        .build()
        );
    }

    @Override
    public boolean isBooked(String phoneNumber, String email) {
        return userRepository.exists(BookedUser.builder().email(email).phoneNumber(phoneNumber).build());
    }

    @Override
    public boolean confirmSignUp(String phoneNumber, String codeValue) {
        var codeOpt = codeRepository.findOne(
                AuthCodeSearchCriteria
                        .builder()
                        .userPhoneNumberEq(phoneNumber)
                        .valueEq(codeValue)
                        .active(true)
                        .dateOfCreation(Range.ofFrom(LocalDateTime.now().minusMinutes(15)))
                        .build()
        );
        if (codeOpt.isEmpty()) {
            return false;
        }

        var code = codeOpt.get();
        code.setActive(false);
        codeRepository.update(code);

        var user = userRepository.findById(code.getId()).orElseThrow();
        user.setUserState(UserState.REGISTERED);

        return userRepository.update(user) != null;
    }
}
