package com.techacademy.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class TopController {
        @PostMapping("/")
        public String postTop() {
            // top.htmlに画面遷移
            return "Top";
        }

        @GetMapping("/")
        public String getTop() {
            // top.html画面に画面遷移
            return "Top";
        }
    }


