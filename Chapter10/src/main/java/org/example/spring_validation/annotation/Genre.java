package org.example.spring_validation.annotation;

public enum Genre {

    POP("Р"),
    JAZZ("J"),
    BLUES("В"),
    COUNTRY("С");

    private final String code;

    Genre(String code) {
        this.code = code;
    }

    public String toString() {
        return this.code;
    }
}
