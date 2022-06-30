package org.example.web.beans;

import org.example.Services.*;
import org.example.repositories.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServicesBeans {

    @Bean
    public GeneralInformationService generalInformationService(GeneralInformationRepository generalInformationRepository) {
        return new GeneralInformationService(generalInformationRepository);
    }

    @Bean
    public MoreInformationService moreInformationService(MoreInformationRepository moreInformationRepository) {
        return new MoreInformationService(moreInformationRepository);
    }

    @Bean
    public FoodsCompleteInformationService foodsCompleteInformationService(FoodsCompleteInformationRepository foodsCompleteInformationRepository) {
        return new FoodsCompleteInformationService(foodsCompleteInformationRepository);
    }

    @Bean
    public ActivityService activityService(ActivityRepository activityRepository) {
        return new ActivityService(activityRepository);
    }

    @Bean
    public FeedbackService feedbackService(FeedbackRepository feedbackRepository) {
        return new FeedbackService(feedbackRepository);
    }
}