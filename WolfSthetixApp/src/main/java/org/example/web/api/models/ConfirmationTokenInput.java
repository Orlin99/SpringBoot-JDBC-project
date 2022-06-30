package org.example.web.api.models;

import java.time.LocalDateTime;

public class ConfirmationTokenInput {
    public final String token;
    public final LocalDateTime expired_At;
    public final LocalDateTime confirmed_At;

    public ConfirmationTokenInput(String token, LocalDateTime expired_At, LocalDateTime confirmed_At) {
        this.token = token;
        this.expired_At = expired_At;
        this.confirmed_At = confirmed_At;
    }
}
