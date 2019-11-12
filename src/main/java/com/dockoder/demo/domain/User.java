package com.dockoder.demo.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;

//Entity annotation: maps the User.class into database entity.
//Data annotation: lombok creates getters and setters.
//NoArgsConstructor annotation: lombok creates a constructor with no arguments.

@Entity
@Data
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull(message = "Name can't be null or empty")
    @Max(value = 100)
    private String name;

    @NotNull(message = "Email can't be null or empty")
    @Max(value = 255)
    @Email
    private String email;
}
