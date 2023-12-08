package org.example.spring_validation.special_validation;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@ToString
@CheckCountrySinger
public class Singer {

    @NotNull
    @Size(min = 2, max = 60)
    private String firstName;

    private String lastName;

    @NotNull
    private Genre genre;

    private Gender gender;

    public boolean isCountrySinger() {
        return genre == Genre.COUNTRY;
    }
}
