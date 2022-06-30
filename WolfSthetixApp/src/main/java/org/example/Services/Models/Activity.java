package org.example.Services.Models;

public class Activity {
    public final Integer activity_ID;
    public final Integer calories_consumed;
    public final Integer calories_burned;
    public final Integer steps_walked;
    public final Double daily_water_consumed_liters;
    public final String activity_for_this_day;
    public final Integer user_activity_ID;

    public Activity(Integer activity_ID, Integer calories_consumed, Integer calories_burned, Integer steps_walked, Double daily_water_consumed_liters, String activity_for_this_day, Integer user_activity_ID) {
        this.activity_ID = activity_ID;
        this.calories_consumed = calories_consumed;
        this.calories_burned = calories_burned;
        this.steps_walked = steps_walked;
        this.daily_water_consumed_liters = daily_water_consumed_liters;
        this.activity_for_this_day = activity_for_this_day;
        this.user_activity_ID = user_activity_ID;
    }
    @Override
    public String toString() {
        return "User's activity { " +
                ", activity_ID = " + activity_ID +
                ", calories_consumed = " + calories_consumed +
                ", calories_burned = " + calories_burned +
                ", steps_walked = " + steps_walked +
                ", daily_water_consumed_liters = " + daily_water_consumed_liters +
                ", activity_for_this_day = " + activity_for_this_day +
                ", user_activity_ID = " + user_activity_ID + " }";
    }
}
