package telegram.bot.nure.botphonenumberprodject.repository;

import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;
import org.slf4j.LoggerFactory;
import org.testcontainers.containers.ContainerLaunchException;
import org.testcontainers.containers.JdbcDatabaseContainer;
import org.testcontainers.utility.DockerImageName;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class MysqlTestContainer extends JdbcDatabaseContainer<MysqlTestContainer> {
    private static final LoggerContext LOGGER_CONTEXT = (LoggerContext) LoggerFactory.getILoggerFactory();
    private static final Logger LOGGER = LOGGER_CONTEXT.getLogger(MysqlTestContainer.class);

    private static final String MYSQL_ROOT_USER = "root";
    private static final int WAIT_STEPS = 15;
    private static final long ONE_MINUTE = 1000 * 60;
    private static final long TWENTY_SECONDS = 1000 * 20;
    private static final DockerImageName DEFAULT_IMAGE_NAME = DockerImageName.parse("mysql");

    public static final int MYSQL_PORT = 3306;
    public static final String TEST_QUERY = "SELECT 1";

    private String databaseName;
    private String username;
    private String password;

    public MysqlTestContainer(String dockerImageName, String databaseName) {
        this(DockerImageName.parse(dockerImageName), databaseName);
    }

    public MysqlTestContainer(DockerImageName dockerImageName, String databaseName) {
        super(dockerImageName);
        this.databaseName = databaseName;
        this.username = MYSQL_ROOT_USER;
        this.password = MYSQL_ROOT_USER;
        dockerImageName.assertCompatibleWith(new DockerImageName[]{DEFAULT_IMAGE_NAME});
        this.addExposedPort(MYSQL_PORT);
    }

    @Override
    public String getDriverClassName() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            return "com.mysql.cj.jdbc.Driver";
        } catch (ClassNotFoundException var2) {
            return "com.mysql.jdbc.Driver";
        }
    }

    @Override
    public String getJdbcUrl() {
        String additionalUrlParams = this.constructUrlParameters("?", "&");
        return "jdbc:mysql://" + this.getHost() + ":" + this.getMappedPort(MYSQL_PORT) + "/" + this.databaseName + additionalUrlParams;
    }

    public String getDatabaseName() {
        return this.databaseName;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    protected String getTestQueryString() {
        return TEST_QUERY;
    }

    @Override
    protected void configure() {
        this.optionallyMapResourceParameterAsVolume("TC_MY_CNF", "/etc/mysql/conf.d", "mysql-default-conf");

        this.addEnv("MYSQL_DATABASE", this.databaseName);
        if (!"root".equalsIgnoreCase(this.username)) {
            this.addEnv("MYSQL_USER", this.username);
        }

        if (this.password != null && !this.password.isEmpty()) {
            this.addEnv("MYSQL_PASSWORD", this.password);
            this.addEnv("MYSQL_ROOT_PASSWORD", this.password);
        } else {
            if (!"root".equalsIgnoreCase(this.username)) {
                throw new ContainerLaunchException("Empty password can be used only with the root user");
            }

            this.addEnv("MYSQL_ALLOW_EMPTY_PASSWORD", "yes");
        }

        this.setStartupAttempts(3);
    }

    public MysqlTestContainer withDatabaseName(String databaseName) {
        this.databaseName = databaseName;
        return this.self();
    }

    public MysqlTestContainer withUsername(String username) {
        this.username = username;
        return this.self();
    }

    public MysqlTestContainer withPassword(String password) {
        this.password = password;
        return this.self();
    }

    @Override
    protected void waitUntilContainerStarted() {
        try {
            this.logger().info("Waiting for database connection to become available at {} using query '{}'", this.getJdbcUrl(), this.getTestQueryString());
            long step = 0;
            Exception lastConnectionException = null;

            for (; step < WAIT_STEPS; step++) {

                if (this.isRunning()) {
                    try (Connection connection = this.createConnection("");
                         Statement statement = connection.createStatement()) {

                        boolean testQuerySucceeded = statement.execute(this.getTestQueryString());

                        if (testQuerySucceeded) {
                            this.logger().info("Container is started (JDBC URL: {})", this.getJdbcUrl());
                            break;
                        }
                    } catch (JdbcDatabaseContainer.NoDriverFoundException var44) {
                        throw var44;
                    } catch (Exception var45) {
                        lastConnectionException = var45;
                        this.logger().warn("Failure when trying test query", var45);
                        LOGGER.debug("Wait until container started. Step = " + step);
                        Thread.sleep(ONE_MINUTE);
                    }
                } else {
                    this.logger().warn("Container is not running");
                    LOGGER.debug("Wait until container started. Step = " + step);
                    Thread.sleep(ONE_MINUTE);
                }
            }

            if (step == WAIT_STEPS) {
                throw new IllegalStateException(String.format("Container is started, but cannot be accessed by (JDBC URL: %s), please check container logs", this.getJdbcUrl()), lastConnectionException);
            }

        } catch (InterruptedException var46) {
            throw new RuntimeException(var46);
        }
    }

    @Override
    protected String constructUrlForConnection(String queryString) {
        String url = super.constructUrlForConnection(queryString);
        if (!url.contains("useSSL=")) {
            String separator = url.contains("?") ? "&" : "?";
            url = url + separator + "useSSL=false";
        }

        if (!url.contains("allowPublicKeyRetrieval=")) {
            url = url + "&allowPublicKeyRetrieval=true";
        }

        return url;
    }

    @Override
    public Connection createConnection(String queryString) throws SQLException, JdbcDatabaseContainer.NoDriverFoundException {
        return this.createConnection(queryString, new Properties());
    }

    @Override
    public Connection createConnection(String queryString, Properties info) throws SQLException, JdbcDatabaseContainer.NoDriverFoundException {
        Properties properties = new Properties(info);
        properties.put("user", this.getUsername());
        properties.put("password", this.getPassword());
        String url = this.constructUrlForConnection(queryString);
        Driver jdbcDriverInstance = this.getJdbcDriverInstance();
        SQLException lastException = null;

        try {
            long step = 0;

            for (; step < WAIT_STEPS; step++) {
                while (!this.isRunning()) {
                    Thread.sleep(100L);
                    this.logger().debug("Is not running");
                }

                try {
                    this.logger().debug("Trying to create JDBC connection using {} to {} with properties: {}", new Object[]{jdbcDriverInstance.getClass().getName(), url, properties});
                    return jdbcDriverInstance.connect(url, properties);
                } catch (SQLException var10) {
                    lastException = var10;
                    LOGGER.debug("Create connection. Step = " + step);
                    Thread.sleep(TWENTY_SECONDS);
                }
            }


        } catch (InterruptedException var11) {
            Thread.currentThread().interrupt();
        }

        throw new SQLException("Could not create new connection", lastException);
    }
}
