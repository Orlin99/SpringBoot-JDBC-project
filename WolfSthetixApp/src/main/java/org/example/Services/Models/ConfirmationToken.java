package org.example.Services.Models;

import java.sql.Timestamp;
import java.time.LocalDateTime;

public class ConfirmationToken {
    public final Integer confirmation_token_ID;
    public final String token;
    public final String created_At;
    public final Timestamp expired_At;
    public final Timestamp confirmed_At;

    public ConfirmationToken(Integer confirmation_token_ID, String token, String created_At, Timestamp expired_At, Timestamp confirmed_At) {
        this.confirmation_token_ID = confirmation_token_ID;
        this.token = token;
        this.created_At = created_At;
        this.expired_At = expired_At;
        this.confirmed_At = confirmed_At;
    }
    @Override
    public String toString() {
        return "Confirmation Token { " +
                "confirmation_token_ID = " + confirmation_token_ID +
                ", token = " + token +
                ", created_At = " + created_At +
                ", expired_At = " + expired_At +
                ", confirmed_At = " + confirmed_At + " }";
    }
}
