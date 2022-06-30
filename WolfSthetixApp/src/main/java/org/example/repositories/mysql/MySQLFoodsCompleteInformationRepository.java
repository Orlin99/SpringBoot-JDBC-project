package org.example.repositories.mysql;

import org.example.repositories.FoodsCompleteInformationRepository;
import org.example.repositories.models.FoodsCompleteInformationDAO;
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

import static org.example.repositories.mysql.MySQLFoodsCompleteInformationRepository.Queries.*;
public class MySQLFoodsCompleteInformationRepository implements FoodsCompleteInformationRepository {
    private final TransactionTemplate transactionTemplate;
    private final JdbcTemplate jdbcTemplate;

    public MySQLFoodsCompleteInformationRepository (TransactionTemplate transactionTemplate, JdbcTemplate jdbcTemplate) {
        this.transactionTemplate = transactionTemplate;
        this.jdbcTemplate = jdbcTemplate;
    }
    @Override
    public FoodsCompleteInformationDAO putInformationOfFood(String food_name, String food_type, Double protein, Double carbohydrates, Double sugars, Double fiber, Double fats, Integer saturated_fats, Integer polyunsaturated_fats, Integer monounsaturated_fats, Integer trans_fats, Integer cholesterol, String vitamins, String minerals, Integer calories, Integer food_ID) {
        return transactionTemplate.execute(status -> {
            KeyHolder keyHolder = new GeneratedKeyHolder();

            jdbcTemplate.update(connection -> {
                PreparedStatement preparedStatement = connection.prepareStatement(
                        INSERT_FOOD, Statement.RETURN_GENERATED_KEYS);
                preparedStatement.setString(1, food_name);
                preparedStatement.setString(2, food_type);
                return preparedStatement;
            }, keyHolder);

            Integer foods_ID = Objects.requireNonNull(keyHolder.getKey()).intValue();
            jdbcTemplate.update(INSERT_NUTRIENTS, protein, carbohydrates, sugars, fiber, fats, saturated_fats, polyunsaturated_fats, monounsaturated_fats, trans_fats, cholesterol, vitamins, minerals, calories, foods_ID);
            Integer nutrients_ID = Objects.requireNonNull(keyHolder.getKey()).intValue();
            return new FoodsCompleteInformationDAO(foods_ID, food_name, food_type, nutrients_ID, protein, carbohydrates, sugars, fiber, fats, saturated_fats, polyunsaturated_fats, monounsaturated_fats, trans_fats, cholesterol, vitamins, minerals, calories, foods_ID);
        });
    }
    @Override
    public FoodsCompleteInformationDAO getAllInformationOfFoodByName (String food_name) {
        return jdbcTemplate.queryForObject(GET_ALL_INFORMATION_OF_FOOD_BY_NAME,
                (rs, rowNum) -> fromResultSet(rs), food_name);
    }
    @Override
    public List<FoodsCompleteInformationDAO> getMainInformationOfFoodByCharacteristics(String getColumn, Object conditionOfColumn, String orderBy, int page, int pageSize) {
        return jdbcTemplate.query(LIST_MAIN_INFORMATION_OF_FOOD_BY_CHARACTERISTICS,
                (rs, rowNum) -> fromResultSet(rs), getColumn, conditionOfColumn, orderBy, (page-1)*pageSize, pageSize);
    }
    @Override
    public void deleteFoodAndNutrients(Integer food_ID, String food_name) {
        transactionTemplate.execute(status -> {
            jdbcTemplate.update(DELETE_NUTRIENTS, food_ID);
            jdbcTemplate.update(DELETE_FOOD, food_name);
            return null;
        });
    }
    private FoodsCompleteInformationDAO fromResultSet(ResultSet rs) throws SQLException{
        return new FoodsCompleteInformationDAO(
                rs.getInt("foods_ID"),
                rs.getString("food_name"),
                rs.getString("food_type"),
                // From Nutrients:
                rs.getInt("nutrients_ID"),
                rs.getDouble("protein"),
                rs.getDouble("carbohydrates"),
                rs.getDouble("sugars"),
                rs.getDouble("fiber"),
                rs.getDouble("fats"),
                rs.getInt("saturated_fats"),
                rs.getInt("polyunsaturated_fats"),
                rs.getInt("monounsaturated_fats"),
                rs.getInt("trans_fats"),
                rs.getInt("cholesterol"),
                rs.getString("vitamins"),
                rs.getString("minerals"),
                rs.getInt("calories"),
                rs.getInt("food_ID")
        );
    }
    static class Queries {
        public static final String INSERT_FOOD =
                "INSERT INTO foods (food_name, food_type) VALUES (?, ?)";
        public static final String INSERT_NUTRIENTS =
                "INSERT INTO nutrients (protein, carbohydrates, sugars, fiber, fats, saturated_fats, polyunsaturated_fats, monounsaturated_fats, trans_fats, cholesterol, vitamins, minerals, calories, food_ID)\n" +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        public static final String DELETE_NUTRIENTS = "DELETE FROM nutrients WHERE food_ID = ?";
        public static final String DELETE_FOOD = "DELETE FROM foods WHERE food_name = ?";
        public static final String GET_ALL_INFORMATION_OF_FOOD_BY_NAME = "" +
                "SELECT foods.foods_ID, foods.food_name, foods.food_type,\n" +
                "nutrients_ID, protein, carbohydrates, sugars, fiber, fats, saturated_fats, polyunsaturated_fats,\n" +
                "monounsaturated_fats, trans_fats, cholesterol, vitamins, minerals, calories, food_ID\n" +
                "FROM nutrients\n" +
                "JOIN foods\n" +
                "ON nutrients.food_ID = foods.foods_ID\n" +
                "WHERE food_name = ?";
        public static final String LIST_MAIN_INFORMATION_OF_FOOD_BY_CHARACTERISTICS = "" +
                "SELECT foods.foods_ID, foods.food_name, foods.food_type,\n" +
                "nutrients_ID, protein, carbohydrates, sugars, fiber, fats, saturated_fats, polyunsaturated_fats,\n" +
                "monounsaturated_fats, trans_fats, cholesterol, vitamins, minerals, calories, food_ID\n" +
                "FROM nutrients\n" +
                "JOIN foods\n" +
                "ON nutrients.food_ID = foods.foods_ID\n" +
                "WHERE ? >= ?\n" +
                "ORDER BY ? DESC\n" +
                "LIMIT ?, ?";
    }
}