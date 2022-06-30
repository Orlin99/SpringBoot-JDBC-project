package org.example.repositories.mysql;

import org.example.repositories.MoreInformationRepository;
import org.example.repositories.models.MoreInformationDAO;
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

import static org.example.repositories.mysql.MySQLMoreInformationRepository.Queries.*;
public class MySQLMoreInformationRepository implements MoreInformationRepository {
    private final TransactionTemplate transactionTemplate;
    private final JdbcTemplate jdbcTemplate;

    public MySQLMoreInformationRepository (TransactionTemplate transactionTemplate, JdbcTemplate connection){
        this.transactionTemplate = transactionTemplate;
        this.jdbcTemplate = connection;
    }

    @Override
    public MoreInformationDAO AddMoreInformationToUser(String gender, Double height_cm, Double weight_kg, String activity_level, Integer age, Double goal_kg, Integer user_ID) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    INSERT_MORE_INFORMATION, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, gender);
            preparedStatement.setDouble(2,height_cm);
            preparedStatement.setDouble(3,weight_kg);
            preparedStatement.setString(4,activity_level);
            preparedStatement.setInt(5,age);
            preparedStatement.setDouble(6,goal_kg);
            preparedStatement.setInt(7, user_ID);
            return preparedStatement;
        }, keyHolder);

        Integer secondary_ID = Objects.requireNonNull(keyHolder.getKey()).intValue();
        return new MoreInformationDAO(secondary_ID, gender, height_cm, weight_kg, activity_level, age, goal_kg, user_ID);
    }

    @Override
    public MoreInformationDAO getTheAdditionalInformation(int secondary_ID) {
        return jdbcTemplate.queryForObject(GET_MORE_INFORMATION,
                (rs, rowNum) -> fromResultSet(rs), secondary_ID);
    }

    @Override
    public MoreInformationDAO updateTheAdditionalInformation(Double height_cm, Double weight_kg, String activity_level, Integer age, Double goal_kg, Integer user_ID) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    UPDATE_MORE_INFORMATION, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setDouble(1,height_cm);
            preparedStatement.setDouble(2,weight_kg);
            preparedStatement.setString(3,activity_level);
            preparedStatement.setInt(4,age);
            preparedStatement.setDouble(5,goal_kg);
            preparedStatement.setInt(6, user_ID);
            return preparedStatement;
        }, keyHolder);

        return new MoreInformationDAO(height_cm, weight_kg, activity_level, age, goal_kg, user_ID);
    }

    @Override
    public List<MoreInformationDAO> listOfTheAdditionalInformationOfTheUsers(int page, int pageSize) {
        return jdbcTemplate.query(LIST_MORE_INFORMATION,
                (rs, rowNum) -> fromResultSet(rs), (page-1)*pageSize, pageSize);
    }

    @Override
    public void deleteTheAdditionalInformationOfUser(int secondary_ID) {
        jdbcTemplate.update(DELETE_MORE_INFORMATION, secondary_ID);
    }
    private MoreInformationDAO fromResultSet(ResultSet rs) throws SQLException {
        return new MoreInformationDAO(
                rs.getInt("secondary_ID"),
                rs.getString("gender"),
                rs.getDouble("height_cm"),
                rs.getDouble("weight_kg"),
                rs.getString("activity_level"),
                rs.getInt("age"),
                rs.getDouble("goal_kg"),
                rs.getInt("user_ID")
        );
    }
    static class Queries {
        public static final String INSERT_MORE_INFORMATION =
                "INSERT INTO more_information (gender, height_cm, weight_kg, activity_level, age, goal_kg, user_ID) VALUES (?, ?, ?, ?, ?, ?, ?)";

        public static final String GET_MORE_INFORMATION = "" +
                "SELECT secondary_ID, gender, height_cm, weight_kg, activity_level, age, goal_kg, user_ID " +
                "FROM more_information WHERE secondary_ID = ?";

        public static final String UPDATE_MORE_INFORMATION = "" +
                "UPDATE more_information " +
                "SET height_cm = ?, weight_kg = ?, activity_level = ?, age = ?, goal_kg = ? " +
                "WHERE user_ID = ?";

        public static final String LIST_MORE_INFORMATION = "" +
                "SELECT secondary_ID, gender, height_cm, weight_kg, activity_level, age, goal_kg, user_ID " +
                "FROM more_information LIMIT ?, ?";

        public static final String DELETE_MORE_INFORMATION =
                "DELETE FROM more_information WHERE secondary_ID = ?";
    }
}