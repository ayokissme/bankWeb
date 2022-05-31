package com.my.bank.dto.enums;

public enum Country {
    RUSSIA("Russia"),
    BELARUS("Belarus"),
    USA("United States"),
    FRANCE("France"),
    GERMANY("Germany");

    private final String country;

    Country(String country) {
        this.country = country;
    }

    public String getCountry() {
        return country;
    }

}
