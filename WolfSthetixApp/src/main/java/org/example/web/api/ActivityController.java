package org.example.web.api;

import org.example.Services.ActivityService;
import org.example.Services.Models.Activity;
import org.example.web.api.models.ActivityInput;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/activity")
public class ActivityController {
    private ActivityService activityService;

    public ActivityController(ActivityService activityService) {
        this.activityService = activityService;
    }

    @GetMapping
    public List<Activity> listOfUsersActivity(
            @RequestParam Integer page,
            @RequestParam Integer pageSize) {
        return activityService.listOfUsersActivity(page, pageSize);
    }

    @GetMapping(value = "/{activity_ID}")
    public Activity fetchUserActivity(@PathVariable Integer activity_ID) {
        return activityService.fetchUserActivity(activity_ID);
    }

    @PostMapping
    public Activity setUpUserActivity(@RequestBody ActivityInput activity) {
        return activityService.setUpUserActivity(
                activity.calories_consumed, activity.calories_burned, activity.steps_walked, activity.daily_water_consumed_liters, activity.user_activity_ID);
    }

    @PutMapping(value = "/{activity_ID}")
    public Activity updateTheActivityInformation(@RequestBody ActivityInput activityInput, @PathVariable Integer activity_ID) {
        return activityService.updateTheActivityInformation(
                activityInput.calories_consumed, activityInput.calories_burned, activityInput.steps_walked, activityInput.daily_water_consumed_liters, activity_ID);
    }

    @DeleteMapping(value = "/{activity_ID}")
    public void deleteUserActivity(@PathVariable Integer activity_ID) {
        activityService.deleteUserActivity(activity_ID);
    }
}
