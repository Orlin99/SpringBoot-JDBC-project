package org.example.Services;

import org.example.Services.Models.Activity;
import org.example.repositories.ActivityRepository;

import java.util.List;
import java.util.stream.Collectors;

public class ActivityService {
    private final ActivityRepository activityRepository;

    public ActivityService(ActivityRepository activityRepository) {
        this.activityRepository = activityRepository;
    }
    public Activity setUpUserActivity(Integer calories_consumed, Integer calories_burned, Integer steps_walked, Double daily_water_consumed_liters, Integer user_activity_ID) {
        return Mappers.fromActivityDAO(activityRepository.setUpUserActivity(calories_consumed, calories_burned, steps_walked, daily_water_consumed_liters, user_activity_ID));
    }
    public Activity fetchUserActivity(int activity_ID) {
        return Mappers.fromActivityDAO(activityRepository.fetchUserActivity(activity_ID));
    }
    public Activity updateTheActivityInformation(Integer calories_consumed, Integer calories_burned, Integer steps_walked, Double daily_water_consumed_liters, Integer activity_ID) {
        return Mappers.fromActivityDAO(activityRepository.updateTheActivityInformation(calories_consumed, calories_burned, steps_walked, daily_water_consumed_liters, activity_ID));
    }
    public List<Activity> listOfUsersActivity(int page, int pageSize) {
        return activityRepository.listOfUsersActivity(page, pageSize)
                .stream()
                .map(Mappers::fromActivityDAO)
                .collect(Collectors.toList());
    }
    public void deleteUserActivity(int activity_ID) {
        activityRepository.deleteUserActivity(activity_ID);
    }
}