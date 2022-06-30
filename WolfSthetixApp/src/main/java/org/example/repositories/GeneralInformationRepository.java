package org.example.repositories;

import org.example.Services.Models.Positions;
import org.example.repositories.models.GeneralInformationDAO;

import java.util.List;
public interface GeneralInformationRepository {
    GeneralInformationDAO createUser(String nickname, String password, String email_address, String phone_number,
                                     String first_name, String last_name, String date_of_birth, Positions position);
    GeneralInformationDAO getUser(int ID);
    List<GeneralInformationDAO> listOfUsers(int page, int pageSize);
    void deleteUser(int ID);
}
