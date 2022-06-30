package org.example.Services.Models;

public enum Positions {
    MEMBER(1),
    VIP(2),
    BANNED(3),
    FITNESS_INSTRUCTOR(4),
    MODERATOR(5),
    ADMIN(6);
    public Integer numberOfPosition;

    Positions(Integer numberOfPosition) {
        this.numberOfPosition = numberOfPosition;
    }
}
