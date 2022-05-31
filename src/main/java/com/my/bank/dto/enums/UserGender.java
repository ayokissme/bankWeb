package com.my.bank.dto.enums;

public enum UserGender {
    MALE("Male"),
    FEMALE("Female");

    private final String gender;

    UserGender(String gender) {
        this.gender = gender;
    }

    public String getGender() {
        return gender;
    }

}
