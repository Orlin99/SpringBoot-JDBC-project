package org.example.repositories.mysql;

import org.example.repositories.ActivityRepository;
import org.example.repositories.models.ActivityDAO;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.transaction.support.TransactionTemplate;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Objects;

import static org.example.repositories.mysql.MySQLActivityRepository.Queries.*;
import static org.example.repositories.mysql.MySQLMoreInformationRepository.Queries.UPDATE_MORE_INFORMATION;

public class MySQLActivityRepository implements ActivityRepository {
    private final TransactionTemplate transactionTemplate;
    private final JdbcTemplate jdbcTemplate;

    public MySQLActivityRepository(TransactionTemplate transactionTemplate, JdbcTemplate jdbcTemplate) {
        this.transactionTemplate = transactionTemplate;
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public ActivityDAO setUpUserActivity(Integer calories_consumed, Integer calories_burned, Integer steps_walked, Double daily_water_consumed_liters, Integer user_activity_ID) {

        DateTimeFormatter activity_for_this_day = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate now = LocalDate.now();

        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    INSERT_ACTIVITY, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setInt(1, calories_consumed);
            preparedStatement.setInt(2, calories_burned);
            preparedStatement.setInt(3, steps_walked);
            preparedStatement.setDouble(4, daily_water_consumed_liters);
            preparedStatement.setInt(5, user_activity_ID);
            return preparedStatement;
        }, keyHolder);

        Integer activity_ID = Objects.requireNonNull(keyHolder.getKey()).intValue();
        return new ActivityDAO(activity_ID, calories_consumed, calories_burned, steps_walked, daily_water_consumed_liters, activity_for_this_day.format(now), user_activity_ID);
    }

    @Override
    public ActivityDAO fetchUserActivity(int activity_ID) {
        return jdbcTemplate.queryForObject(FETCH_USER_ACTIVITY,
                (rs, rowNum) -> fromResultSet(rs), activity_ID);
    }

    @Override
    public ActivityDAO updateTheActivityInformation(Integer calories_consumed, Integer calories_burned, Integer steps_walked, Double daily_water_consumed_liters, Integer activity_ID) {

        DateTimeFormatter activity_for_this_day = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate now = LocalDate.now();

        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    UPDATE_THE_ACTIVITY_INFORMATION, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setInt(1, calories_consumed);
            preparedStatement.setInt(2, calories_burned);
            preparedStatement.setInt(3, steps_walked);
            preparedStatement.setDouble(4, daily_water_consumed_liters);
            preparedStatement.setInt(5, activity_ID);
            return preparedStatement;
        }, keyHolder);

        return new ActivityDAO(activity_ID, calories_consumed, calories_burned, steps_walked, daily_water_consumed_liters, activity_for_this_day.format(now));
    }

    @Override
    public List<ActivityDAO> listOfUsersActivity(int page, int pageSize) {
        return jdbcTemplate.query(LIST_OF_ACTIVITIES,
                (rs, rowNum) -> fromResultSet(rs), (page-1)*pageSize, pageSize);
    }

    @Override
    public void deleteUserActivity(int activity_ID) {
        jdbcTemplate.update(DELETE_USER_ACTIVITY, activity_ID);
    }

    private ActivityDAO fromResultSet(ResultSet rs) throws SQLException {
        return new ActivityDAO(
                rs.getInt("activity_ID"),
                rs.getInt("calories_consumed"),
                rs.getInt("calories_burned"),
                rs.getInt("steps_walked"),
                rs.getDouble("daily_water_consumed_liters"),
                rs.getString("activity_for_this_day"),
                rs.getInt("user_activity_ID")
        );
    }

    static class Queries {
        public static final String INSERT_ACTIVITY =
                "INSERT INTO activity (calories_consumed, calories_burned, steps_walked, daily_water_consumed_liters, user_activity_ID) VALUES (?, ?, ?, ?, ?)";
        public static final String FETCH_USER_ACTIVITY = "" +
                "SELECT general_information.nickname, activity_ID, calories_consumed, calories_burned, steps_walked, daily_water_consumed_liters, activity_for_this_day, user_activity_ID\n" +
                "FROM activity\n" +
                "JOIN general_information\n" +
                "ON activity.user_activity_ID = general_information.ID\n" +
                "WHERE activity_ID = ?";
        public static final String UPDATE_THE_ACTIVITY_INFORMATION = "" +
                "UPDATE activity " +
                "SET calories_consumed = ?, calories_burned = ?, steps_walked = ?, daily_water_consumed_liters = ? " +
                "WHERE activity_ID = ?";
        public static final String LIST_OF_ACTIVITIES = "" +
                "SELECT general_information.nickname, activity_ID, calories_consumed, calories_burned, steps_walked, daily_water_consumed_liters, activity_for_this_day, user_activity_ID\n" +
                "FROM activity\n" +
                "JOIN general_information\n" +
                "ON activity.user_activity_ID = general_information.ID\n" +
                "LIMIT ?, ?";
        public static final String DELETE_USER_ACTIVITY =
                "DELETE FROM activity WHERE activity_ID = ?";
    }
}
