package org.example.spring_validation.assert_true;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@ToString
public class Singer {

    @NotNull
    @Size(min = 2, max = 60)
    private String firstName;

    private String lastName;

    @NotNull
    private Genre genre;

    private Gender gender;

    @AssertTrue(message="ERROR! Individual customer should have gender and last name defined")
    public boolean isCountrySinger() {
        boolean result = true;
        if (genre != null && (genre.equals(Genre.COUNTRY) &&
                (gender == null || lastName == null))) {
            result = false;
        }
        return result;
    }
}
