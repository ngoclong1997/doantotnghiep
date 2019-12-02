package com.drato.graduationthesis.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class PersonalInfoDto {
    @NotEmpty(message = "{constraints.not-empty}")
    @Size(min = 4, max = 50, message = "{constraints.min-4-max-50}")
    private String username;

    @NotEmpty(message = "{constraints.not-empty}")
    @Size(max = 50, message = "{constraints.length-smaller-50}")
    private String firstName;

    @NotEmpty(message = "{constraints.not-empty}")
    @Size(max = 50, message = "{constraints.length-smaller-50}")
    private String lastName;

    @Email
    @NotEmpty(message = "{constraints.not-empty}")
    @Size(min=5, max =250, message = "{constraints.min-5-max-250")
    private String email;

    public PersonalInfoDto() {
    }

    public PersonalInfoDto(@NotEmpty(message = "{constraints.not-empty}") @Size(min = 4, max = 50, message = "{constraints.min-4-max-50}") String username, @NotEmpty(message = "{constraints.not-empty}") @Size(max = 50, message = "{constraints.length-smaller-50}") String firstName, @NotEmpty(message = "{constraints.not-empty}") @Size(max = 50, message = "{constraints.length-smaller-50}") String lastName, @Email @NotEmpty(message = "{constraints.not-empty}") @Size(min = 5, max = 250, message = "{constraints.min-5-max-250") String email) {
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
