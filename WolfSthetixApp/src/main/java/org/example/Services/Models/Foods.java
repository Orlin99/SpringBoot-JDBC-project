package org.example.Services.Models;

public class Foods {
    public final Integer foods_ID;
    public final String food_name;
    public final String food_type;
    public final Nutrients nutrients;

    public Foods(Integer foods_ID, String food_name, String food_type, Nutrients nutrients) {
        this.foods_ID = foods_ID;
        this.food_name = food_name;
        this.food_type = food_type;
        this.nutrients = nutrients;
    }
    @Override
    public String toString() {
        return "Food Name And Type { " +
                "food_ID = " + foods_ID +
                ", food_name = " + food_name +
                ", food_type = " + food_type +
                ", nutrients = " + nutrients + " }";
    }
}