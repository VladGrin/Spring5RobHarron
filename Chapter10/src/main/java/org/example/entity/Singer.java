package org.example.entity;

import lombok.Getter;
import lombok.Setter;
import org.joda.time.DateTime;

import java.net.URL;
import java.text.SimpleDateFormat;

@Getter
@Setter
public class Singer {

    private String firstName;
    private String lastName;
    private DateTime birthDate;
    private URL personalSite;

    @Override
    public String toString() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return String.format("(First name: %s, Last name: %s, Birthday: %s, Site: %s} ",
                firstName, lastName, sdf.format(birthDate.toDate()), personalSite);
    }
}
