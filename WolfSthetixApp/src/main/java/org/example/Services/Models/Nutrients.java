package org.example.Services.Models;

public class Nutrients {
    public final Integer nutrients_ID;
    public final Double protein;
    public final Double carbohydrates;
    public final Double sugars;
    public final Double fiber;
    public final Double fats;
    public final Integer saturated_fats;
    public final Integer polyunsaturated_fats;
    public final Integer monounsaturated_fats;
    public final Integer trans_fats;
    public final Integer cholesterol;
    public final String vitamins;
    public final String minerals;
    public final Integer calories;
    public final Integer food_ID;

    public Nutrients(Integer nutrients_ID, Double protein, Double carbohydrates, Double sugars, Double fiber, Double fats, Integer saturated_fats, Integer polyunsaturated_fats,
                     Integer monounsaturated_fats, Integer trans_fats, Integer cholesterol, String vitamins, String minerals, Integer calories, Integer food_ID) {
        this.nutrients_ID = nutrients_ID;
        this.protein = protein;
        this.carbohydrates = carbohydrates;
        this.sugars = sugars;
        this.fiber = fiber;
        this.fats = fats;
        this.saturated_fats = saturated_fats;
        this.polyunsaturated_fats = polyunsaturated_fats;
        this.monounsaturated_fats = monounsaturated_fats;
        this.trans_fats = trans_fats;
        this.cholesterol = cholesterol;
        this.vitamins = vitamins;
        this.minerals = minerals;
        this.calories = calories;
        this.food_ID = food_ID;
    }
    @Override
    public String toString() {
        return "Food Information (Nutrients and Calories) { " +
                "nutrients_ID = " + nutrients_ID +
                ", protein = " + protein +
                ", carbohydrates = " + carbohydrates +
                ", sugars = " + sugars +
                ", fiber = " + fiber +
                ", fats = " + fats +
                ", saturated_fats = " + saturated_fats +
                ", polyunsaturated_fats = " + polyunsaturated_fats +
                ", monounsaturated_fats = " + monounsaturated_fats +
                ", trans_fats = " + trans_fats +
                ", cholesterol = " + cholesterol +
                ", vitamins = " + vitamins +
                ", minerals = " + minerals +
                ", calories = " + calories +
                ", food_ID = " + food_ID + " }";
    }
}
