package com.drato.graduationthesis.controller.user;

import com.drato.graduationthesis.dto.ChangePasswordDto;
import com.drato.graduationthesis.dto.PersonalInfoDto;
import com.drato.graduationthesis.model.User;
import com.drato.graduationthesis.service.interfaces.SecurityService;
import com.drato.graduationthesis.service.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/user")
public class ProfileController {

    @Autowired
    SecurityService securityService;

    @Autowired
    UserService userService;

    @GetMapping("/profile")
    public String index(Model model) {
        User currentUser = securityService.getCurrentUser();
        model.addAttribute("user", currentUser);
        model.addAttribute("title", "lang.profile");
        return "user/profile";
    }

    @GetMapping("/profile/edit")
    public String editProfile(Model model) {
        User currentUser = securityService.getCurrentUser();
        PersonalInfoDto personalInfoDto = new PersonalInfoDto(currentUser.getUsername(), currentUser.getFirstName(), currentUser.getLastName(), currentUser.getEmail());
        model.addAttribute("personalInfoDto", personalInfoDto);
        model.addAttribute("title", "lang.edit-profile");
        return "user/edit-profile";
    }

    @PostMapping("/profile/edit")
    public String editProfile(RedirectAttributes redirectAttributes, @ModelAttribute("personalInfoDto") @Valid PersonalInfoDto personalInfoDto, Model model, BindingResult result) {
        User updUser = securityService.getCurrentUser();
        if (userService.emailUsedByOther(updUser.getId(), personalInfoDto.getEmail())) {
            result.rejectValue("email", "err.email-existed", "Email đã tồn tại");
        }
        if (userService.usernameUsedByOther(updUser.getId(), personalInfoDto.getUsername())) {
            result.rejectValue("username", "err.username-existed", "Tên đăng nhập đã tồn tại");
        }
        if (result.hasErrors()){
            model.addAttribute("title", "lang.edit-profile");
            return "user/edit-profile";
        }

        updUser.setEmail(personalInfoDto.getEmail());
        updUser.setFirstName(personalInfoDto.getFirstName());
        updUser.setLastName(personalInfoDto.getLastName());
        updUser.setUsername(personalInfoDto.getUsername());
        userService.editUser(updUser);
        redirectAttributes.addFlashAttribute("message", "lang.edit-succeed");
        return "redirect:/user/profile";
    }

    @GetMapping("/change-password")
    public String changePassword(Model model) {
        model.addAttribute("user", new ChangePasswordDto());
        model.addAttribute("title", "lang.change-password");
        return "user/change-password";
    }

    @PostMapping("/change-password")
    public String changePassword(RedirectAttributes redirectAttributes, @ModelAttribute("user") @Valid ChangePasswordDto user, Model model, BindingResult result) {

        if (!userService.currentUserPasswordMatch(user.getOldPassword())) {
            result.rejectValue("oldPassword", "err.old-pass-mismatch", "Old password mismatch");
        }
        if (result.hasErrors()){
            model.addAttribute("title", "lang.edit-profile");
            return "user/change-password";
        }

        User updUser = securityService.getCurrentUser();
        userService.updatePassword(updUser.getId(), user.getPassword());
        redirectAttributes.addFlashAttribute("message", "lang.update-password-succeed");
        return "redirect:/user/profile";
    }


}
