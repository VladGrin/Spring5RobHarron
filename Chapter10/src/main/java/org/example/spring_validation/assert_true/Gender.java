package org.example.spring_validation.assert_true;

public enum Gender {

    MALE("М"),

    FEMALE("F");

    private final String code;

    Gender(String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return this.code;
    }
}
