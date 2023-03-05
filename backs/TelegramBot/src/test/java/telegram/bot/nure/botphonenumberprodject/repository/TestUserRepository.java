package telegram.bot.nure.botphonenumberprodject.repository;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;
import org.assertj.core.api.Assertions;
import org.junit.*;
import org.junit.runner.RunWith;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import telegram.bot.nure.botphonenumberprodject.config.DataBaseConfiguration;
import telegram.bot.nure.botphonenumberprodject.config.RepositoryConfig;
import telegram.bot.nure.botphonenumberprodject.entities.User;
import telegram.bot.nure.botphonenumberprodject.filter.Range;
import telegram.bot.nure.botphonenumberprodject.filter.UserFilter;
import telegram.bot.nure.botphonenumberprodject.filter.UserUniqueFieldFilter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.function.Supplier;


@RunWith(SpringRunner.class)
@Import({DataBaseConfiguration.class, RepositoryConfig.class, TestRepositoryConfig.class})
@TestPropertySource(
        locations = "classpath:bd/user_repository_test.properties")
public class TestUserRepository {
    private static final LoggerContext LOGGER_CONTEXT = (LoggerContext) LoggerFactory.getILoggerFactory();
    private static final Logger LOGGER = LOGGER_CONTEXT.getLogger(TestUserRepository.class);
    private static final Supplier<? extends RuntimeException> ERROR_NOT_FOUND = () -> new RuntimeException("User not found");
    private static final UserFilter EMPTY_FILTER = new UserFilter();
    private static final User TEST_USER = new User(9000000L, "9000000", LocalDateTime.now(), true);
    private static final String IMAGE_NAME = "mysql:latest";
    private static final String DATA_BASE_NAME = "telegram_bot";
    private static final MysqlTestContainer MYSQL = new MysqlTestContainer(IMAGE_NAME, DATA_BASE_NAME);

    @Autowired
    private UserRepository userRepository;

    static {
        MYSQL.start();
    }

    @DynamicPropertySource
    public static void setDatasourceProperties(final DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", MYSQL::getJdbcUrl);
        registry.add("spring.datasource.username", MYSQL::getUsername);
        registry.add("spring.datasource.password", MYSQL::getPassword);
    }

    @BeforeClass
    public static void turnOffAnyLoggers() {
        LOGGER_CONTEXT.getLoggerList().forEach(l -> l.setLevel(Level.OFF));
    }

    @BeforeClass
    public static void turnOnTestUserRepositoryLogger() {
        LOGGER.setLevel(Level.ALL);
    }

    @Test
    public void findOneByChatId() {
        var chatId = this.getUser()
                .getChatId();

        var findUser = this.userRepository
                .findOne(
                        UserUniqueFieldFilter
                                .builder()
                                .chatId(chatId)
                                .build())
                .orElseThrow(ERROR_NOT_FOUND);

        Assertions.assertThat(findUser.getChatId())
                .isEqualTo(chatId);
    }

    @Test
    public void findOneByPhoneNumber() {
        var phoneNumber = this.getUser()
                .getPhoneNumber();

        var findUser = this.userRepository
                .findOne(
                        UserUniqueFieldFilter
                                .builder()
                                .phoneNumber(phoneNumber)
                                .build())
                .orElseThrow(ERROR_NOT_FOUND);

        Assertions.assertThat(findUser.getPhoneNumber())
                .isEqualTo(phoneNumber);
    }

    @Test
    public void findByChatIds() {
        var chatIds = getUsers().stream().limit(2).map(User::getChatId).toList();

        var users = userRepository.findAll(UserFilter.builder().chatIds(chatIds).build());

        Assertions.assertThat(users).map(User::getChatId).containsAll(chatIds);
    }

    @Test
    public void findByPhoneNumberContains() {
        final String containPeace = "1";

        var users = userRepository.findAll(UserFilter.builder().phoneNumber(containPeace).build());

        Assertions
                .assertThat(users)
                .map(User::getPhoneNumber)
                .allMatch(p -> p.contains(containPeace));
    }

    @Test
    public void findByActiveIsFalse() {
        var users = userRepository.findAll(UserFilter
                .builder()
                .active(false)
                .build());

        Assertions.assertThat(users)
                .allMatch(u -> !u.isActive());
    }

    @Test
    public void findByDateRegistrationBefore() {
        var testDateRegistration = getUser().getDateRegistration();

        var users = userRepository.findAll(
                UserFilter.builder()
                        .dateOfCreation(Range.<LocalDateTime>builder().to(testDateRegistration).build())
                        .build()
        );

        Assertions.assertThat(users)
                .allMatch(u -> u.getDateRegistration().isBefore(testDateRegistration));
    }

    @Test
    public void findByDateRegistrationAfter() {
        var testDateRegistration = getUser().getDateRegistration();

        var users = userRepository.findAll(
                UserFilter.builder()
                        .dateOfCreation(Range.<LocalDateTime>builder().from(testDateRegistration).build())
                        .build()
        );

        Assertions.assertThat(users)
                .allMatch(u -> u.getDateRegistration().isAfter(testDateRegistration));
    }

    @Test
    public void save() {
        Assertions.assertThat(userRepository.save(TEST_USER)).isNotEqualTo(0);

        Assertions.assertThat(
                        userRepository
                                .findOne(UserUniqueFieldFilter
                                        .builder()
                                        .chatId(TEST_USER.getChatId())
                                        .build())
                                .orElseThrow(ERROR_NOT_FOUND))
                .isEqualTo(TEST_USER);
    }

    @Test
    public void updateActive() {
        var oldUser = getUser();

        var newUser = new User(oldUser.getChatId(), oldUser.getPhoneNumber(),
                oldUser.getDateRegistration(),
                !oldUser.isActive());

        Assertions.assertThat(userRepository.update(newUser)).isNotEqualTo(0);

        Assertions.assertThat(
                        userRepository
                                .findOne(UserUniqueFieldFilter
                                        .builder()
                                        .chatId(oldUser.getChatId())
                                        .build())
                                .orElseThrow(ERROR_NOT_FOUND))
                .isNotEqualTo(oldUser);
    }

    @After
    public void showMessageTestEnd() {
        LOGGER.debug("Test end");
    }

    @AfterClass
    public static void stopContainer() {
        MYSQL.stop();
        LOGGER.debug("Stop container");
    }

    @Ignore
    private List<User> getUsers() {
        return userRepository.findAll(EMPTY_FILTER);
    }

    @Ignore
    private User getUser() {
        return this.getUsers()
                .stream()
                .findAny()
                .orElseThrow(ERROR_NOT_FOUND);
    }
}
