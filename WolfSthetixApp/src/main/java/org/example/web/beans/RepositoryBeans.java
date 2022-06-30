package org.example.web.beans;

import org.example.repositories.*;
import org.example.repositories.mysql.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.support.TransactionTemplate;

@Configuration
public class RepositoryBeans {
    @Bean
    public GeneralInformationRepository generalInformationRepository(TransactionTemplate transactionTemplate, JdbcTemplate jdbcTemplate) {
        return new MySQLGeneralInformationRepository(transactionTemplate, jdbcTemplate);
    }

    @Bean
    public MoreInformationRepository moreInformationRepository(TransactionTemplate transactionTemplate, JdbcTemplate jdbcTemplate) {
        return new MySQLMoreInformationRepository(transactionTemplate, jdbcTemplate);
    }

    @Bean
    public FoodsCompleteInformationRepository foodsCompleteInformationRepository(TransactionTemplate transactionTemplate, JdbcTemplate jdbcTemplate) {
        return new MySQLFoodsCompleteInformationRepository(transactionTemplate, jdbcTemplate);
    }

    @Bean
    public ActivityRepository activityRepository(TransactionTemplate transactionTemplate, JdbcTemplate jdbcTemplate) {
        return new MySQLActivityRepository(transactionTemplate, jdbcTemplate);
    }

    @Bean
    public FeedbackRepository feedbackRepository(TransactionTemplate transactionTemplate, JdbcTemplate jdbcTemplate) {
        return new MySQLFeedbackRepository(transactionTemplate, jdbcTemplate);
    }
}
