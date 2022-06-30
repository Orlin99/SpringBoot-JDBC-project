package org.example.repositories.models;

import java.sql.Timestamp;

public class ConfirmationTokenDAO {
    public final Integer confirmation_token_ID;
    public final String token;
    public final String  created_At;
    public final Timestamp expired_At;
    public final Timestamp confirmed_At;
    public ConfirmationTokenDAO(Integer confirmation_token_ID, String token, String created_At, Timestamp expired_At, Timestamp confirmed_At) {
        this.confirmation_token_ID = confirmation_token_ID;
        this.token = token;
        this.created_At = created_At;
        this.expired_At = expired_At;
        this.confirmed_At = confirmed_At;
    }
    public ConfirmationTokenDAO(String token, String created_At, Timestamp expired_At, Timestamp confirmed_At) {
        this.confirmation_token_ID = null;
        this.token = token;
        this.created_At = created_At;
        this.expired_At = expired_At;
        this.confirmed_At = confirmed_At;
    }
}