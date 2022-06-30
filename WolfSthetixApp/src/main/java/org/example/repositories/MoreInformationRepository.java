package org.example.repositories;

import org.example.repositories.models.MoreInformationDAO;

import java.util.List;
public interface MoreInformationRepository {
    MoreInformationDAO AddMoreInformationToUser(String gender, Double height_cm, Double weight_kg, String activity_level, Integer age, Double goal_kg, Integer user_ID);
    MoreInformationDAO getTheAdditionalInformation (int secondary_ID);
    MoreInformationDAO updateTheAdditionalInformation(Double height_cm, Double weight_kg, String activity_level, Integer age, Double goal_kg, Integer user_ID);
    List<MoreInformationDAO> listOfTheAdditionalInformationOfTheUsers(int page, int pageSize);
    void deleteTheAdditionalInformationOfUser(int secondary_ID);
}
