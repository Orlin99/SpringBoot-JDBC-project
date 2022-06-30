package org.example.repositories.models;

public class MoreInformationDAO {
    public final Integer secondary_ID;
    public final String gender;
    public final Double height_cm;
    public final Double weight_kg;
    public final String activity_level;
    public final Integer age;
    public final Double goal_kg;
    public final Integer user_ID;
    public MoreInformationDAO(Integer secondary_ID, String gender, Double height_cm, Double weight_kg, String activity_level, Integer age, Double goal_kg, Integer user_ID) {
        this.secondary_ID = secondary_ID;
        this.gender = gender;
        this.height_cm = height_cm;
        this.weight_kg = weight_kg;
        this.activity_level = activity_level;
        this.age = age;
        this.goal_kg = goal_kg;
        this.user_ID = user_ID;
    }
    public MoreInformationDAO(Double height_cm, Double weight_kg, String activity_level, Integer age, Double goal_kg, Integer user_ID) {
        this.secondary_ID = null;
        this.gender = null;
        this.height_cm = height_cm;
        this.weight_kg = weight_kg;
        this.activity_level = activity_level;
        this.age = age;
        this.goal_kg = goal_kg;
        this.user_ID = user_ID;
    }
}