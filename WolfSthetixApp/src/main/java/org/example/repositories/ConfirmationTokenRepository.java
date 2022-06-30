package org.example.repositories;

import org.example.repositories.models.ConfirmationTokenDAO;

import java.sql.Timestamp;
public interface ConfirmationTokenRepository {
    ConfirmationTokenDAO createToken(String token, Timestamp expired_At, Timestamp confirmed_At);
    ConfirmationTokenDAO findByToken(String token);
}
