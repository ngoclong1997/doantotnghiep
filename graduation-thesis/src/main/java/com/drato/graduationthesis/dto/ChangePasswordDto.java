package com.drato.graduationthesis.dto;

import com.drato.graduationthesis.annotation.FieldMatch;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@FieldMatch.List({
        @FieldMatch(firstField = "password", secondField = "confirmPassword")
})
public class ChangePasswordDto {
    @NotEmpty(message = "{constraints.not-empty}")
    @Size(min=4, max = 50, message = "{constraints.min-4-max-50}")
    private String oldPassword;

    @NotEmpty(message = "{constraints.not-empty}")
    @Size(min=4, max = 50, message = "{constraints.min-4-max-50}")
    private String password;

    @NotEmpty(message = "{constraints.not-empty}")
    @Size(min=4, max = 50, message = "{constraints.min-4-max-50}")
    private String confirmPassword;

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
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

    public ChangePasswordDto() {
    }

    public ChangePasswordDto(@NotEmpty(message = "{constraints.not-empty}") @Size(min = 4, max = 50, message = "{constraints.min-4-max-50}") String oldPassword, @NotEmpty(message = "{constraints.not-empty}") @Size(min = 4, max = 50, message = "{constraints.min-4-max-50}") String password, @NotEmpty(message = "{constraints.not-empty}") @Size(min = 4, max = 50, message = "{constraints.min-4-max-50}") String confirmPassword) {
        this.oldPassword = oldPassword;
        this.password = password;
        this.confirmPassword = confirmPassword;
    }
}
