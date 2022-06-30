package org.example.repositories;

import org.example.repositories.models.ActivityDAO;

import java.util.List;
public interface ActivityRepository {
    ActivityDAO setUpUserActivity(Integer calories_consumed, Integer calories_burned, Integer steps_walked, Double daily_water_consumed_liters, Integer user_activity_ID);
    ActivityDAO fetchUserActivity(int activity_ID);
    ActivityDAO updateTheActivityInformation(Integer calories_consumed, Integer calories_burned, Integer steps_walked, Double daily_water_consumed_liters, Integer activity_ID);
    List<ActivityDAO> listOfUsersActivity(int page, int pageSize);
    void deleteUserActivity(int activity_ID);
}
