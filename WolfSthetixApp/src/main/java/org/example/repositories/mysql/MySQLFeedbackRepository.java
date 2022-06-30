package org.example.repositories.mysql;

import org.example.repositories.FeedbackRepository;
import org.example.repositories.models.FeedbackDAO;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.transaction.support.TransactionTemplate;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Objects;

import static org.example.repositories.mysql.MySQLFeedbackRepository.Queries.*;

public class MySQLFeedbackRepository implements FeedbackRepository {
    private final TransactionTemplate transactionTemplate;
    private final JdbcTemplate jdbcTemplate;

    public MySQLFeedbackRepository(TransactionTemplate transactionTemplate, JdbcTemplate jdbcTemplate){
        this.transactionTemplate = transactionTemplate;
        this.jdbcTemplate = jdbcTemplate;
    }
    @Override
    public FeedbackDAO createFeedback(String content, Integer sent_by) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    INSERT_FEEDBACK, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, content);
            preparedStatement.setInt(2, sent_by);
            return preparedStatement;
        }, keyHolder);

        Integer feedback_ID = Objects.requireNonNull(keyHolder.getKey()).intValue();
        return new FeedbackDAO(feedback_ID, content, sent_by);
    }
    @Override
    public FeedbackDAO getFeedback(int feedback_ID) {
        return jdbcTemplate.queryForObject(GET_FEEDBACK,
                (rs, rowNum) -> fromResultSet(rs), feedback_ID);
    }
    @Override
    public List<FeedbackDAO> listOfFeedbacks(int page, int pageSize) {
        return jdbcTemplate.query(LIST_OF_FEEDBACKS,
                (rs, rowNum) -> fromResultSet(rs), (page-1)*pageSize, pageSize);
    }
    @Override
    public void deleteFeedback(int feedback_ID) {
        jdbcTemplate.update(DELETE_FEEDBACK, feedback_ID);
    }
    private FeedbackDAO fromResultSet (ResultSet rs) throws SQLException {
        return new FeedbackDAO(
                rs.getInt("feedback_ID"),
                rs.getString("content"),
                rs.getInt("sent_by")
        );
    }
    static class Queries {
        public static final String INSERT_FEEDBACK =
                "INSERT INTO feedback (content, sent_by) VALUES (?, ?)";
        public static final String GET_FEEDBACK = "" +
                "SELECT feedback_ID, content, sent_by " +
                "FROM feedback WHERE feedback_ID = ?";
        public static final String LIST_OF_FEEDBACKS = "" +
                "SELECT feedback_ID, content, sent_by " +
                "FROM feedback LIMIT ?, ?";
        public static final String DELETE_FEEDBACK =
                "DELETE FROM feedback WHERE feedback_ID = ?";
    }
}
