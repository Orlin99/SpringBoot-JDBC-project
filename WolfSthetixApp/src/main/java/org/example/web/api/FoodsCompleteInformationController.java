package org.example.web.api;

import org.example.Services.FoodsCompleteInformationService;
import org.example.Services.Models.Foods;
import org.example.web.api.models.FoodsCompleteInformationInput;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/foods")
public class FoodsCompleteInformationController {
    private FoodsCompleteInformationService foodsCompleteInformationService;

    public FoodsCompleteInformationController(FoodsCompleteInformationService foodsCompleteInformationService) {
        this.foodsCompleteInformationService = foodsCompleteInformationService;
    }

    @GetMapping
    public List<Foods> getMainInformationOfFoodByCharacteristics(
            @RequestParam String getColumn,
            @RequestParam Object conditionOfColumn,
            @RequestParam String orderBy,
            @RequestParam Integer page,
            @RequestParam Integer pageSize) {
        return foodsCompleteInformationService.getMainInformationOfFoodByCharacteristics(getColumn, conditionOfColumn, orderBy, page, pageSize);
    }

    @GetMapping(value = "/{food_name}")
    public Foods getAllInformationOfFoodByName(@PathVariable String food_name) {
        return foodsCompleteInformationService.getAllInformationOfFoodByName(food_name);
    }

    @PostMapping
    public Foods putInformationOfFood(@RequestBody FoodsCompleteInformationInput foodsInput) {
        return foodsCompleteInformationService.putInformationOfFood(
                foodsInput.food_name, foodsInput.food_type, foodsInput.protein, foodsInput.carbohydrates, foodsInput.sugars, foodsInput.fiber, foodsInput. fats,
                foodsInput.saturated_fats, foodsInput.polyunsaturated_fats, foodsInput.monounsaturated_fats, foodsInput.trans_fats, foodsInput.cholesterol, foodsInput.vitamins,
                foodsInput.minerals, foodsInput.calories, foodsInput.food_ID);
    }

    @DeleteMapping
    public void deleteFoodAndNutrients(@PathVariable
            @RequestParam Integer food_ID,
            @RequestParam String food_name) {
        foodsCompleteInformationService.deleteFoodAndNutrients(food_ID, food_name);
    }
}