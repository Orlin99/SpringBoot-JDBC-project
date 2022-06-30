package org.example.repositories;

import org.example.repositories.models.FoodsCompleteInformationDAO;

import java.util.List;
public interface FoodsCompleteInformationRepository {
    FoodsCompleteInformationDAO putInformationOfFood (String food_name, String food_type, Double protein, Double carbohydrates, Double sugars,
                                                      Double fiber, Double fats, Integer saturated_fats, Integer polyunsaturated_fats,
                                                      Integer monounsaturated_fats, Integer trans_fats, Integer cholesterol,
                                                      String vitamins, String minerals, Integer calories, Integer food_ID);
    FoodsCompleteInformationDAO getAllInformationOfFoodByName (String food_name);
    List<FoodsCompleteInformationDAO> getMainInformationOfFoodByCharacteristics(String getColumn, Object conditionOfColumn, String orderBy, int page, int pageSize);
    void deleteFoodAndNutrients(Integer food_ID, String food_name);
}