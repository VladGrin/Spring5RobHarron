package com.apress.prospring5.ch16.entities;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "singer")
@Getter
@Setter
@ToString
public class Singer implements Serializable {

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "ID")
	private Long id;

	@Version
	@Column(name = "VERSION")
	private int version;

	@NotBlank(message = "{validation.firstname.NotBlank.message}")
	@Size(min = 2, max = 60, message = "{validation.firstname.Size.message}")
	@Column(name = "FIRST_NAME")
	private String firstName;

	@NotBlank(message = "{validation.lastname.NotBlank.message}")
	@Size(min = 1, max = 40, message = "{validation.lastname.Size.message}")
	@Column(name = "LAST_NAME")
	private String lastName;

	@NotNull
	@Temporal(TemporalType.DATE)
	@Column(name = "BIRTH_DATE")
	private Date birthDate;

	@Column(name = "DESCRIPTION")
	private String description;

}
