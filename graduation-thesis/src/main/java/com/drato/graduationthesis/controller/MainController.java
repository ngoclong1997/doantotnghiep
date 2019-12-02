package com.drato.graduationthesis.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping("/")
    public String root() {
        return "dashboard/index";
    }

    @GetMapping("/i18n")
    public String getInternationalPage() {
        return "i18n";
    }

    @GetMapping("/login")
    public String login(Model model) {
        return "login";
    }

}
