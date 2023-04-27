package com.techacademy.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.techacademy.entity.Authentication;
import com.techacademy.entity.Employee;
import com.techacademy.service.AuthenticationService;

@Controller
@RequestMapping("authentication")
public class AuthenticationController {
    private final AuthenticationService service;

    public AuthenticationController(AuthenticationService service) {
        this.service = service;
    }

    // ----- 一覧画面 -----
    @GetMapping("/authen")
    public String getList(Model model) {
        // 全件検索結果をModelに登録
        model.addAttribute("authenlist", service.getAuthenticationList());
        // employee/list.htmlに画面遷移
        return "authen/list";
    }




}


