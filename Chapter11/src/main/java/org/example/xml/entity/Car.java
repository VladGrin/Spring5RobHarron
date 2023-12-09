package org.example.xml.entity;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Type;
import org.joda.time.DateTime;

import java.text.SimpleDateFormat;

@Entity
@Table(name = "car")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Car {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "LICENSE_PLATE")
    private String licensePlate;

    @Column(name = "MANUFACTURER")
    private String manufacturer;

    @Column(name = "MANUFACTURE_DATE")
    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
    private DateTime manufactureDate;

    @Column(name = "AGE")
    private int age;

    @Version
    private int version;

    @Override
    public String toString() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return String.format("{License: %s, Manufacturer %s, Manufacture Date: %s, Age: %d)",
                licensePlate, manufacturer, sdf.format(manufactureDate.toDate()), age);
    }
}
