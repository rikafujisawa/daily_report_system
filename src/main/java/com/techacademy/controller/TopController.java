package com.techacademy.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import com.techacademy.service.UserDetail;

@Controller
public class TopController {

        @GetMapping("/")
        public String getTop(@AuthenticationPrincipal UserDetail user,Model model) {
            model.addAttribute("user", user);
            // top.html画面に画面遷移
            return "Top";
        }



    }


