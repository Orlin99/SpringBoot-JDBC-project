package org.example.repositories.mysql;

import org.example.Services.Models.Positions;
import org.example.repositories.GeneralInformationRepository;
import org.example.repositories.models.GeneralInformationDAO;
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

import static org.example.repositories.mysql.MySQLGeneralInformationRepository.Queries.*;

public class MySQLGeneralInformationRepository implements GeneralInformationRepository {
    private final TransactionTemplate transactionTemplate;
    private final JdbcTemplate jdbcTemplate;

    public MySQLGeneralInformationRepository(TransactionTemplate transactionTemplate, JdbcTemplate connection) {
        this.transactionTemplate = transactionTemplate;
        this.jdbcTemplate = connection;
    }

    @Override
    public GeneralInformationDAO createUser(String nickname, String password, String email_address, String phone_number, String first_name, String last_name, String date_of_birth, Positions position) {

        DateTimeFormatter registration_date = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate now = LocalDate.now();

        return transactionTemplate.execute(status -> {
            KeyHolder keyHolder = new GeneratedKeyHolder();

            jdbcTemplate.update(connection -> {
                PreparedStatement preparedStatement = connection.prepareStatement(
                        INSERT_USER, Statement.RETURN_GENERATED_KEYS);
                preparedStatement.setString(1, nickname);
                preparedStatement.setString(2, password);
                preparedStatement.setString(3, email_address);
                preparedStatement.setString(4, phone_number);
                preparedStatement.setString(5, first_name);
                preparedStatement.setString(6, last_name);
                preparedStatement.setString(7, date_of_birth);
                return preparedStatement;
            }, keyHolder);

            Integer ID = Objects.requireNonNull(keyHolder.getKey()).intValue();
            jdbcTemplate.update(INSERT_PERSONAL_POSITION, keyHolder.getKey(), position.numberOfPosition);
            return new GeneralInformationDAO(ID, nickname, password, email_address, phone_number, registration_date.format(now), first_name, last_name, date_of_birth, position);
        });
    }

    @Override
    public GeneralInformationDAO getUser(int ID) {
        return jdbcTemplate.queryForObject(GET_USER, (rs, rowNum) -> fromResultSet(rs), ID);
    }

    @Override
    public List<GeneralInformationDAO> listOfUsers(int page, int pageSize) {
        return jdbcTemplate.query(LIST_USERS, (rs, rowNum) -> fromResultSet(rs), (page-1)*pageSize, pageSize);
    }

    @Override
    public void deleteUser(int ID) {
        jdbcTemplate.update(DELETE_PERSONAL_POSITION, ID);
        jdbcTemplate.update(DELETE_USER, ID);
    }

    private GeneralInformationDAO fromResultSet(ResultSet rs) throws SQLException {
        return new GeneralInformationDAO(
                rs.getInt("ID"),
                rs.getString("nickname"),
                rs.getString("password"),
                rs.getString("email_address"),
                rs.getString("phone_number"),
                rs.getString("registration_date"),
                rs.getString("first_name"),
                rs.getString("last_name"),
                rs.getString("date_of_birth")
        );
    }
    static class Queries {
        public static final String INSERT_USER =
                "INSERT INTO general_information (nickname, password, email_address, phone_number, first_name, last_name, date_of_birth) " +
                        "VALUES (?, ?, ?, ?, ?, ?, ?)";
        public static final String INSERT_PERSONAL_POSITION =
                "INSERT INTO personal_position (person_ID, position_ID) VALUES (?, ?)";
        public static final String GET_USER = "" +
                "SELECT ID, nickname, password, email_address, phone_number, registration_date, first_name, last_name, date_of_birth, positions.position\n" +
                "FROM general_information\n" +
                "JOIN personal_position\n" +
                "ON general_information.ID = personal_position.person_ID\n" +
                "JOIN positions\n" +
                "ON personal_position.position_ID = positions.positions_ID\n" +
                "WHERE ID = ?";
        public static final String LIST_USERS = "" +
                "SELECT ID, nickname, password, email_address, phone_number, registration_date, first_name, last_name, date_of_birth, positions.position\n" +
                "FROM general_information\n" +
                "JOIN personal_position\n" +
                "ON general_information.ID = personal_position.person_ID\n" +
                "JOIN positions\n" +
                "ON personal_position.position_ID = positions.positions_ID\n" +
                "LIMIT ?, ?";
        public static final String DELETE_PERSONAL_POSITION = "DELETE FROM personal_position WHERE person_ID = ?";
        public static final String DELETE_USER = "DELETE FROM general_information WHERE ID = ?";
    }
}