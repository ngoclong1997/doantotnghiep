package com.drato.graduationthesis.dto;

import com.drato.graduationthesis.annotation.FieldMatch;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@FieldMatch.List({
        @FieldMatch(firstField = "password", secondField = "confirmPassword")
})
public class UserDto {

    @NotEmpty(message = "{constraints.not-empty}")
    @Size(min = 4, max = 50, message = "{constraints.min-4-max-50}")
    private String username;

    @NotEmpty(message = "{constraints.not-empty}")
    @Size(max = 50, message = "{constraints.length-smaller-50}")
    private String firstName;

    @NotEmpty(message = "{constraints.not-empty}")
    @Size(max = 50, message = "{constraints.length-smaller-50}")
    private String lastName;

    @NotEmpty(message = "{constraints.not-empty}")
    @Size(min=4, max = 50, message = "{constraints.min-4-max-50}")
    private String password;

    @NotEmpty(message = "{constraints.not-empty}")
    @Size(min=4, max = 50, message = "{constraints.min-4-max-50}")
    private String confirmPassword;

    @Email
    @NotEmpty(message = "{constraints.not-empty}")
    @Size(min=5, max =250, message = "{constraints.min-5-max-250")
    private String email;

    @NotEmpty(message = "{constraints.not-empty}")
    private String role;

    public UserDto() {
    }

    public UserDto(@NotEmpty(message = "{constraints.not-empty}") @Size(min = 4, max = 50, message = "{constraints.min-4-max-50}") String username, @NotEmpty(message = "{constraints.not-empty}") @Size(max = 50, message = "{constraints.length-smaller-50}") String firstName, @NotEmpty(message = "{constraints.not-empty}") @Size(max = 50, message = "{constraints.length-smaller-50}") String lastName, @NotEmpty(message = "{constraints.not-empty}") @Size(min = 4, max = 50, message = "{constraints.min-4-max-50}") String password, @NotEmpty(message = "{constraints.not-empty}") @Size(min = 4, max = 50, message = "{constraints.min-4-max-50}") String confirmPassword, @Email @NotEmpty(message = "{constraints.not-empty}") @Size(min = 5, max = 250, message = "{constraints.min-5-max-250") String email, @NotEmpty(message = "{constraints.not-empty}") String role) {
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.confirmPassword = confirmPassword;
        this.email = email;
        this.role = role;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
