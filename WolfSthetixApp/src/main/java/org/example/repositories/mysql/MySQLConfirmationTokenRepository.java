package org.example.repositories.mysql;

import org.example.repositories.ConfirmationTokenRepository;
import org.example.repositories.models.ConfirmationTokenDAO;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.transaction.support.TransactionTemplate;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

import static org.example.repositories.mysql.MySQLConfirmationTokenRepository.Queries.FIND_BY_TOKEN;
import static org.example.repositories.mysql.MySQLConfirmationTokenRepository.Queries.INSERT_TOKEN;

public class MySQLConfirmationTokenRepository implements ConfirmationTokenRepository {
    private final TransactionTemplate transactionTemplate;
    private final JdbcTemplate jdbcTemplate;

    public MySQLConfirmationTokenRepository(TransactionTemplate transactionTemplate, JdbcTemplate connection) {
        this.transactionTemplate = transactionTemplate;
        this.jdbcTemplate = connection;
    }

    @Override
    public ConfirmationTokenDAO createToken(String token, Timestamp expired_At, Timestamp confirmed_At) {

        DateTimeFormatter created_At = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();

        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    INSERT_TOKEN, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, token);
            preparedStatement.setString(2, String.valueOf(expired_At));
            preparedStatement.setString(3, String.valueOf(confirmed_At));
            return preparedStatement;
        }, keyHolder);

        Integer confirmation_token_ID = Objects.requireNonNull(keyHolder.getKey()).intValue();
        return new ConfirmationTokenDAO(confirmation_token_ID, token, created_At.format(now), expired_At, confirmed_At);
    }

    @Override
    public ConfirmationTokenDAO findByToken(String token) {
        return jdbcTemplate.queryForObject(FIND_BY_TOKEN,
                (rs, rowNum) -> fromResultSet(rs), token);
    }
    private ConfirmationTokenDAO fromResultSet(ResultSet rs) throws SQLException {
        return new ConfirmationTokenDAO(
                rs.getInt("confirmation_token_ID"),
                rs.getString("token"),
                rs.getString("created_At"),
                rs.getTimestamp("expired_At"),
                rs.getTimestamp("confirmed_At")
        );
    }
    static class Queries {
        public static final String INSERT_TOKEN =
                "INSERT INTO confirmation_token (token, expired_At, confirmed_At) VALUES (?, ?, ?)";
        public static final String FIND_BY_TOKEN =
                "SELECT confirmation_token_ID, token, created_At, expired_At, confirmed_At " +
                "FROM confirmation_token WHERE confirmation_token_ID = ?";
    }
}
