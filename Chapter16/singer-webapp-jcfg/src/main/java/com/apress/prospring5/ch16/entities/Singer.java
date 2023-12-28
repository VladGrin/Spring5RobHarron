package com.apress.prospring5.ch16.entities;


import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

import static javax.persistence.GenerationType.IDENTITY;

@Getter
@Setter
@Entity
@Table(name = "singer")
public class Singer implements Serializable {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Version
    @Column(name = "VERSION")
    private int version;

    @NotBlank(message="{validation.firstname.NotBlank.message}")
    @Size(min=2, max=60, message="{validation.firstname.Size.message}")
    @Column(name = "FIRST_NAME")
    private String firstName;

    @NotBlank(message="{validation.lastname.NotBlank.message}")
    @Size(min=1, max=40, message="{validation.lastname.Size.message}")
    @Column(name = "LAST_NAME")
    private String lastName;

    @NotNull
    @Temporal(TemporalType.DATE)
    @Column(name = "BIRTH_DATE")
    private Date birthDate;

    @Column(name = "DESCRIPTION")
    private String description;

    @Basic(fetch= FetchType.LAZY)
    @Lob
    @Column(name = "PHOTO")
    private byte[] photo;

    public void setPhoto(byte[] photo) {
        this.photo = photo;
    }
    @Transient
    public String getBirthDateString() {
        String birthDateString = "";
        if (birthDate != null) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            birthDateString = sdf.format(birthDate);
        }
        return birthDateString;
    }

    @Override
    public String toString() {
        return "Singer - Id: " + id + ", First name: " + firstName
                + ", Last name: " + lastName + ", Birthday: " + birthDate
                + ", Description: " + description;
    }
}
