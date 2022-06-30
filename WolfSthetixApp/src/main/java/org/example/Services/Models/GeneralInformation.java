package org.example.Services.Models;

public class GeneralInformation {
    public final Integer ID;
    public final String nickname;
    public final String password;
    public final String email_address;
    public final String phone_number;
    public final String registration_date;
    public final String first_name;
    public final String last_name;
    public final String date_of_birth;
    public final Positions position;
    public GeneralInformation(Integer ID, String nickname, String password, String email_address, String phone_number, String registration_date, String first_name, String last_name, String date_of_birth, Positions position) {
        this.ID = ID;
        this.nickname = nickname;
        this.password = password;
        this.email_address = email_address;
        this.phone_number = phone_number;
        this.registration_date = registration_date;
        this.first_name = first_name;
        this.last_name = last_name;
        this.date_of_birth = date_of_birth;
        this.position = position;
    }
    @Override
    public String toString() {
        return "General Information of User { " +
                "ID = " + ID +
                ", nickname = " + nickname +
                ", password_hash = " + password +
                ", email_address = " + email_address +
                ", phone_number = " + phone_number +
                ", registration_date = " + registration_date +
                ", first_name = " + first_name +
                ", last_name = " + last_name +
                ", date_of_birth = " + date_of_birth +
                ", position = " + position + " }";
    }
}