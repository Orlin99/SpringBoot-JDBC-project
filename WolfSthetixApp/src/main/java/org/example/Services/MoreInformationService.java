package org.example.Services;

import org.example.Services.Models.MoreInformation;
import org.example.repositories.MoreInformationRepository;

import java.util.List;
import java.util.stream.Collectors;

public class MoreInformationService {
    private final MoreInformationRepository moreInformationRepository;

    public MoreInformationService(MoreInformationRepository moreInformationRepository) {
        this.moreInformationRepository = moreInformationRepository;
    }
    public MoreInformation AddMoreInformationToUser(String gender, Double height_cm, Double weight_kg, String activity_level, Integer age, Double goal_kg, Integer user_ID) {
        return Mappers.fromMoreInformationDAO(moreInformationRepository.AddMoreInformationToUser(gender, height_cm, weight_kg, activity_level, age, goal_kg, user_ID));
    }
    public MoreInformation getTheAdditionalInformation(int secondary_ID) {
        return Mappers.fromMoreInformationDAO(moreInformationRepository.getTheAdditionalInformation(secondary_ID));
    }
    public MoreInformation updateTheAdditionalInformation (Double height_cm, Double weight_kg, String activity_level, Integer age, Double goal_kg, Integer user_ID) {
        return Mappers.fromMoreInformationDAO(moreInformationRepository.updateTheAdditionalInformation(height_cm, weight_kg, activity_level, age, goal_kg, user_ID));
    }
    public List<MoreInformation> listOfTheAdditionalInformationOfTheUsers (int page, int pageSize) {
        return moreInformationRepository.listOfTheAdditionalInformationOfTheUsers(page, pageSize)
                .stream()
                .map(Mappers::fromMoreInformationDAO)
                .collect(Collectors.toList());
    }
    public void deleteTheAdditionalInformationOfUser (int secondary_ID) {
        moreInformationRepository.deleteTheAdditionalInformationOfUser(secondary_ID);
    }
}