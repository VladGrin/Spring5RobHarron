package com.apress.prospring5.ch18;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class Singer {
    private String firstName;
    private String lastName;
    //best song :D
    private String song;
}
