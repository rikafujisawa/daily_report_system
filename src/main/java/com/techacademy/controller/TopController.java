package com.techacademy.controller;

import java.util.List;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.techacademy.entity.Report;
import com.techacademy.service.ReportService;
import com.techacademy.service.UserDetail;

@Controller
public class TopController {

    private final ReportService service;

    public TopController(ReportService service) {
            this.service = service;
    }

    /** TOPページ（自分の日報一覧）を表示 */
    @GetMapping("/")
    public String getTop(@AuthenticationPrincipal UserDetail user, Model model) {
        model.addAttribute("user", user);
        List<Report> rlist = service.getReportByEmp(user.getUser());
        model.addAttribute("reportlist", rlist);
        // top.html画面に画面遷移
        return "Top";
    }

}
