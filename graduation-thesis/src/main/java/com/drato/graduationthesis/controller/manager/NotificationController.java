package com.drato.graduationthesis.controller.manager;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/manager")
public class NotificationController {
    @RequestMapping("/notification")
    public String index() {
        return "manager/notification/index";
    }
}
