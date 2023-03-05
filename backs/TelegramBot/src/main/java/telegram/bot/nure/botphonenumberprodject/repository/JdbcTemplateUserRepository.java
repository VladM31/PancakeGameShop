package telegram.bot.nure.botphonenumberprodject.repository;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.util.CollectionUtils;
import telegram.bot.nure.botphonenumberprodject.entities.User;
import telegram.bot.nure.botphonenumberprodject.filter.Range;
import telegram.bot.nure.botphonenumberprodject.filter.UserFilter;
import telegram.bot.nure.botphonenumberprodject.filter.UserUniqueFieldFilter;

import java.util.*;



public class JdbcTemplateUserRepository implements UserRepository {
    private final static String SELECT_USER = "SELECT chat_id as chatId,number as phoneNumber,date_registration as dateRegistration,active FROM user ";
    private final static String SELECT_ONE = SELECT_USER + " WHERE " +
            " (:chatId IS NULL OR chat_id = :chatId) AND " +
            " (:phoneNumber IS NULL OR number = :phoneNumber) " +
            "limit 2";
    private final static String SELECT_ALL = SELECT_USER + " WHERE " +
            " (:chatIdsIsNull = true OR chat_id IN(:chatIds)) AND " +
            " (:phoneNumber IS NULL OR number LIKE CONCAT('%',:phoneNumber,'%')) AND " +
            " (:isActive IS NULL OR active = :isActive) AND " +
            " (:dateTo IS NULL OR date_registration < :dateTo) AND " +
            " (:dateFrom IS NULL OR date_registration > :dateFrom) ";
    private static final String UPDATE = "UPDATE user SET number = ?, active = ? WHERE chat_id = ?;";
    private static final String SAVE = "INSERT INTO user(chat_id,number,date_registration,active) VALUES (?,?,?,?);";

    private final NamedParameterJdbcTemplate namedParameterJdbc;
    private final RowMapper<User> userRowMapper;
    private final JdbcTemplate jdbc;

    public JdbcTemplateUserRepository(NamedParameterJdbcTemplate namedParameterJdbc, JdbcTemplate jdbc) {
        this.namedParameterJdbc = namedParameterJdbc;
        this.jdbc = jdbc;
        this.userRowMapper = new BeanPropertyRowMapper<>(User.class);
    }

    @Override
    public Optional<User> findOne(UserUniqueFieldFilter filter) {
        if (filter.areAllFieldsNull()) {
            throw new IllegalArgumentException("All params is null");
        }

        Map<String, Object> params = new HashMap<>();

        params.put( "chatId", filter.getChatId());
        params.put("phoneNumber", filter.getPhoneNumber());

        return namedParameterJdbc.queryForStream(SELECT_ONE, params, userRowMapper).findFirst();
    }

    @Override
    public List<User> findAll(UserFilter filter) {
        Map<String, Object> params = new HashMap<>();
        params.put("chatIdsIsNull", CollectionUtils.isEmpty(filter.getChatIds()));
        params.put("chatIds", filter.getChatIds());
        params.put("phoneNumber", filter.getPhoneNumber());
        params.put("isActive", filter.getActive());
        params.put("dateTo", Range.to(filter.getDateOfCreation()));
        params.put("dateFrom", Range.from(filter.getDateOfCreation()));

        return namedParameterJdbc.query(SELECT_ALL, params, userRowMapper);
    }

    @Override
    public int update(User user) {
        return jdbc.update(UPDATE, user.getPhoneNumber(), user.isActive(), user.getChatId());
    }

    @Override
    public int save(User user) {
        return jdbc.update(SAVE, user.getChatId(), user.getPhoneNumber(), user.getDateRegistration(), user.isActive());
    }
}
