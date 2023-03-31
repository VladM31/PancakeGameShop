package nure.pancake.game.shop.authorizationservice.controllers;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.RequiredArgsConstructor;
import nure.pancake.game.shop.authorizationservice.dataobjects.LoginResult;
import nure.pancake.game.shop.authorizationservice.dto.*;
import nure.pancake.game.shop.authorizationservice.mappers.AuthResponseMapper;
import nure.pancake.game.shop.authorizationservice.mappers.AuthResponseUserMapper;
import nure.pancake.game.shop.authorizationservice.mappers.LoginUserMapper;
import nure.pancake.game.shop.authorizationservice.mappers.SignUpUserMapper;
import nure.pancake.game.shop.authorizationservice.services.AuthService;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@ResponseBody
@RestController
@RequestMapping(value = "/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;
    private final LoginUserMapper<LoginRequest> loginUserMapper;
    private final AuthResponseMapper<LoginResult> authResponseMapper;
    private final SignUpUserMapper<SignUpRequest> signUpUserMapper;

    @PostMapping(value = "/login")
    public AuthResponse login(@Valid @RequestBody LoginRequest request) {
        return Optional.of(request)
                .map(loginUserMapper::toLoginUser)
                .map(authService::login)
                .map(authResponseMapper::toAuthResponse).
                orElseThrow();
    }

    @PostMapping(value = "/sign-up")
    public boolean signUp(@Valid @RequestBody SignUpRequest request) {
        return authService.signUp(signUpUserMapper.toSignUpUser(request));
    }

    @GetMapping(value = "/is-registered")
    public boolean isRegistered(@Valid @NotNull(message = "Phone number mustn't be null")
                                @Pattern(regexp = "\\d{10,15}", message = "Phone number length must be between 10 and 15")
                                        String phoneNumber) {
        return authService.isRegistered(phoneNumber);
    }

    @GetMapping(value = "/confirm")
    public boolean confirm(@Valid @ModelAttribute ConfirmCodeRequest req) {
        return authService.confirmSignUp(
                req.getPhoneNumber(),
                req.getCode());
    }
}
