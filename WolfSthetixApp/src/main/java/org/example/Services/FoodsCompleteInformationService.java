package org.example.Services;

import org.example.Services.Models.Foods;
import org.example.repositories.FoodsCompleteInformationRepository;


import java.util.List;
import java.util.stream.Collectors;

public class FoodsCompleteInformationService {
    private final FoodsCompleteInformationRepository foodsCompleteInformationRepository;

    public FoodsCompleteInformationService(FoodsCompleteInformationRepository foodsCompleteInformationRepository) {
        this.foodsCompleteInformationRepository = foodsCompleteInformationRepository;
    }

    public Foods putInformationOfFood(String food_name, String food_type, Double protein, Double carbohydrates, Double sugars, Double fiber, Double fats, Integer saturated_fats,
                                      Integer polyunsaturated_fats, Integer monounsaturated_fats, Integer trans_fats, Integer cholesterol, String vitamins, String minerals,
                                      Integer calories, Integer food_ID) {

        return Mappers.fromFoodsCompleteInformationDAO(foodsCompleteInformationRepository.putInformationOfFood(
                food_name, food_type, protein, carbohydrates, sugars, fiber, fats, saturated_fats,
                polyunsaturated_fats, monounsaturated_fats, trans_fats, cholesterol, vitamins, minerals,
                calories, food_ID));
    }
    public Foods getAllInformationOfFoodByName(String food_name) {
        return Mappers.fromFoodsCompleteInformationDAO(foodsCompleteInformationRepository.getAllInformationOfFoodByName(food_name));
    }

    public List<Foods> getMainInformationOfFoodByCharacteristics(String getColumn, Object conditionOfColumn, String orderBy, int page, int pageSize) {
        return foodsCompleteInformationRepository.getMainInformationOfFoodByCharacteristics(getColumn, conditionOfColumn, orderBy, page, pageSize)
                .stream()
                .map(Mappers::fromFoodsCompleteInformationDAO)
                .collect(Collectors.toList());
    }
    public void deleteFoodAndNutrients(Integer food_ID, String food_name) {
        foodsCompleteInformationRepository.deleteFoodAndNutrients(food_ID, food_name);
    }
}