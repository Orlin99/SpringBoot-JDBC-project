package org.example.web.api.models;

public class ActivityInput {
    public final Integer calories_consumed;
    public final Integer calories_burned;
    public final Integer steps_walked;
    public final Double daily_water_consumed_liters;
    public final String  activity_for_this_day;
    public final Integer user_activity_ID;

    public ActivityInput(Integer calories_consumed, Integer calories_burned, Integer steps_walked, Double daily_water_consumed_liters, String activity_for_this_day, Integer user_activity_ID) {
        this.calories_consumed = calories_consumed;
        this.calories_burned = calories_burned;
        this.steps_walked = steps_walked;
        this.daily_water_consumed_liters = daily_water_consumed_liters;
        this.activity_for_this_day = activity_for_this_day;
        this.user_activity_ID = user_activity_ID;
    }
}
