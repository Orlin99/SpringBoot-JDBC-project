package org.example.repositories.models;

public class FoodsCompleteInformationDAO {
    public final Integer foods_ID;
    public final String food_name;
    public final String food_type;
    // Nutrients And Information Of The Selected Food:
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

    public FoodsCompleteInformationDAO(Integer foods_ID, String food_name, String food_type, Integer nutrients_ID, Double protein, Double carbohydrates, Double sugars,
                                       Double fiber, Double fats, Integer saturated_fats, Integer polyunsaturated_fats, Integer monounsaturated_fats, Integer trans_fats,
                                       Integer cholesterol, String vitamins, String minerals, Integer calories, Integer food_ID) {

        this.foods_ID = foods_ID;
        this.food_name = food_name;
        this.food_type = food_type;
        // Nutrients And Information Of The Selected Food:
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
}