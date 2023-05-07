package com.techacademy.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LogoutController {

    @PostMapping("/logout")
    public String postLogout() {
        // login画面にリダイレクト
        return "redirect:/login";
    }
}


