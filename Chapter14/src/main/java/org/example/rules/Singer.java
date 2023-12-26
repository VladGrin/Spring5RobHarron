package org.example.rules;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.joda.time.DateTime;

@Getter
@Setter
@ToString
public class Singer {

    private Long id;
    private String firstName;
    private String lastName;
    private DateTime birthDate;
    private String ageCategory;
}
