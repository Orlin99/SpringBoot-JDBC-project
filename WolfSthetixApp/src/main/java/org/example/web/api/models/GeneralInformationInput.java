package org.example.web.api.models;

import org.example.Services.Models.Positions;

public class GeneralInformationInput {
    public final String nickname;
    public final String password;
    public final String email_address;
    public final String phone_number;
    public final String first_name;
    public final String last_name;
    public final String date_of_birth;
    public final Positions position;

    public GeneralInformationInput(String nickname, String password, String email_address, String phone_number, String first_name, String last_name, String date_of_birth, Positions position) {
        this.nickname = nickname;
        this.password = password;
        this.email_address = email_address;
        this.phone_number = phone_number;
        this.first_name = first_name;
        this.last_name = last_name;
        this.date_of_birth = date_of_birth;
        this.position = position;
    }
}