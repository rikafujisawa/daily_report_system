package com.techacademy.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.techacademy.entity.Report;
import com.techacademy.service.ReportService;

@Controller
    @RequestMapping("report")
    public class ReportController {
        private final ReportService service;


        public ReportController(ReportService service) {
            this.service = service;
        }

        // ----- 一覧画面 -----
        @GetMapping("/rlist")
        public String getList(Model model) {
            // 全件検索結果をModelに登録
            List<Report> list = service.getReportList();
            model.addAttribute("reportlist", list);
            // report/rlist.htmlに画面遷移
            return "report/rlist";
        }
    }



