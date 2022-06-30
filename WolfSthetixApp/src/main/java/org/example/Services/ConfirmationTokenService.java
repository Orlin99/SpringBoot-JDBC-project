package org.example.Services;

import org.example.Services.Models.ConfirmationToken;
import org.example.repositories.ConfirmationTokenRepository;

import java.sql.Timestamp;
import java.time.LocalDateTime;

public class ConfirmationTokenService {
    private final ConfirmationTokenRepository confirmationTokenRepository;

    public ConfirmationTokenService(ConfirmationTokenRepository confirmationTokenRepository) {
        this.confirmationTokenRepository = confirmationTokenRepository;
    }

    public ConfirmationToken createToken(String token, Timestamp expired_At, Timestamp confirmed_At) {
        return Mappers.fromConfirmationTokenDAO(confirmationTokenRepository.createToken(token, expired_At, confirmed_At));
    }

    public ConfirmationToken findByToken(String token) {
        return Mappers.fromConfirmationTokenDAO(confirmationTokenRepository.findByToken(token));
    }
}