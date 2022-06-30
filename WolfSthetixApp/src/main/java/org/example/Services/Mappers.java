package org.example.Services;

import org.example.Services.Models.*;
import org.example.repositories.models.*;

class Mappers {
    public static GeneralInformation fromGeneralInformationDAO(GeneralInformationDAO user) {
        return new GeneralInformation(user.ID, user.nickname, user.password, user.email_address, user.phone_number,
                user.registration_date, user.first_name, user.last_name, user.date_of_birth, user.position);
    }
    public static MoreInformation fromMoreInformationDAO(MoreInformationDAO moreInformation) {
        System.out.println(moreInformation);
        return new MoreInformation(moreInformation.secondary_ID, moreInformation.gender, moreInformation.height_cm, moreInformation.weight_kg, moreInformation.activity_level, moreInformation.age, moreInformation.goal_kg, moreInformation.user_ID);
    }
    public static Foods fromFoodsCompleteInformationDAO(FoodsCompleteInformationDAO foodsFullInfo) {
        return new Foods(
                foodsFullInfo.foods_ID, foodsFullInfo.food_name, foodsFullInfo.food_type,
                new Nutrients(foodsFullInfo.nutrients_ID, foodsFullInfo.protein, foodsFullInfo.carbohydrates, foodsFullInfo.sugars, foodsFullInfo.fiber, foodsFullInfo.fats,
                              foodsFullInfo.saturated_fats, foodsFullInfo.polyunsaturated_fats, foodsFullInfo.monounsaturated_fats, foodsFullInfo.trans_fats, foodsFullInfo.cholesterol,
                              foodsFullInfo.vitamins, foodsFullInfo.minerals, foodsFullInfo.calories, foodsFullInfo.food_ID));
    }
    public static Feedback fromFeedbackDAO(FeedbackDAO feedback) {
        return new Feedback(feedback.feedback_ID, feedback.content, feedback.sent_by);
    }
    public static Activity fromActivityDAO(ActivityDAO activityDAO) {
        return new Activity(activityDAO.activity_ID, activityDAO.calories_consumed, activityDAO.calories_burned, activityDAO.steps_walked, activityDAO.daily_water_consumed_liters, activityDAO.activity_for_this_day, activityDAO.user_activity_ID);
    }
    public static ConfirmationToken fromConfirmationTokenDAO(ConfirmationTokenDAO confirmationTokenDAO) {
        return new ConfirmationToken(confirmationTokenDAO.confirmation_token_ID, confirmationTokenDAO.token, confirmationTokenDAO.created_At, confirmationTokenDAO.expired_At, confirmationTokenDAO.confirmed_At);
    }
}