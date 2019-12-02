package com.drato.graduationthesis.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class MyErrorController implements ErrorController {

    @GetMapping(value = "/error1")
    public String renderErrorPage(HttpServletRequest httpRequest, Model model) {

        String code = "", message = "", messageDetail = "";
        int httpErrorCode = getErrorCode(httpRequest);

        switch (httpErrorCode) {
            case 400: {
                code = "400";
                message = "Truy cập không hợp lệ";
                messageDetail = "Server không thể xử lý request của bạn vì có xảy ra lỗi.";
                break;
            }
            case 401: {
                code = "401";
                message = "Yêu cầu xác thực";
                messageDetail = "Bạn không thể truy cập vào trang này do chưa được xác thực.";
                //Http Error Code: 401. Unauthorized
                break;
            }
            case 403: {
                code = "403";
                message = "Không có quyền truy cập";
                messageDetail = "Bạn không thể truy cập vào trang này vì không có quyền.";
                break;
            }
            case 404: {
                code = "404";
                message = "Không tìm thấy trang";
                messageDetail = "Trang bạn vừa tìm kiếm không thể tìm thấy trên server.";
                break;
            }
            case 500: {
                code = "500";
                message = "Server bị lỗi";
                messageDetail = "Server xảy ra lỗi. Vui lòng thử lại sau.";
                break;
            }
            default: {
                code = "XXX";
                message = "Lỗi không xác định";
                messageDetail = "Xảy ra lỗi không xác định. Vui lòng thử lại sau.";
                break;
            }
        }
        model.addAttribute("code", code);
        model.addAttribute("err-message", message);
        model.addAttribute("messageDetail", messageDetail);
        return "error-page";
    }

    private int getErrorCode(HttpServletRequest httpRequest) {
        return (Integer) httpRequest
                .getAttribute("javax.servlet.error.status_code");
    }

    @Override
    public String getErrorPath() {
        return "/error";
    }
}
