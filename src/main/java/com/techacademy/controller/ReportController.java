package com.techacademy.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.techacademy.entity.Authentication;
import com.techacademy.entity.Employee;
import com.techacademy.entity.Report;
import com.techacademy.service.ReportService;
import com.techacademy.service.UserDetail;

    @Controller
    @RequestMapping("report")
    public class ReportController {
        private final ReportService service;

        public ReportController(ReportService service) {
            this.service = service;
        }


        // ----- 一自分の日報一覧画面 -----
        @GetMapping("/rlist")
        public String getList(@AuthenticationPrincipal UserDetail user,Model model) {
            model.addAttribute("user", user);
            // 全件検索結果をModelに登録
            List<Report> rlist = service.getReportByEmp(user.getUser());
            model.addAttribute("reportlist", rlist);

            // report/rlist.htmlに画面遷移
            return "report/rlist";
        }


        /** 日報　新規登録画面を表示 */
        @GetMapping("/newreport")
        public String getNewReport(@AuthenticationPrincipal UserDetail user,Model model, @ModelAttribute Report report) {
            model.addAttribute("user", user);
            // 登録画面に遷移
            return "report/newreport";
        }

        /** 日報　新規登録処理 */
        @PostMapping("/newreport")
        public String postNewreport(@AuthenticationPrincipal UserDetail user,@Validated Report report, BindingResult res, Model model) {
            report.setEmp(user.getUser());
            model.addAttribute("user", user);
            service.saveReport(report);


            LocalDateTime datetime = LocalDateTime.now();
            report.setUpdatedAt(datetime);
            report.setCreatedAt(datetime);

            // 一覧画面にリダイレクト
            return "redirect:/report/allreport";
        }

       // ----- 日報一覧画面に戻る -----
        @GetMapping("/allreport")
        public String getAllreport(@AuthenticationPrincipal UserDetail user,Model model) {
            model.addAttribute("user", user);
            // 全件検索結果をModelに登録
            List<Report> rlist = service.getReportList();
            model.addAttribute("reportlist", rlist);

            // report/allreport.htmlに画面遷移
            return "report/allreport";
    }
        /** 日報 詳細ページを表示 */
        @GetMapping("/rdetail/{id}/")
        public String getRdetail(@AuthenticationPrincipal UserDetail user, @PathVariable("id") Integer id, Model model) {
            model.addAttribute("user", user);
            model.addAttribute("report", service.getReport(id));
            // 詳細画面に遷移
            return "report/rdetail";
        }

        /** 日報　編集ページを表示 */
        @GetMapping("/rupdate/{id}/")
        public String getReport(@AuthenticationPrincipal UserDetail user, @PathVariable("id") Integer id, Model model) {
            // Report登録
            model.addAttribute("user", user);
            model.addAttribute("report", service.getReport(id));
            // 一覧画面に遷移
            return "report/rupdate";
        }


        /** 日報　編集処理 */

        @PostMapping("/rupdate/{id}/")
        public String postRdetail(@Validated Report report) {
            // 日報の登録




            LocalDateTime datetime = LocalDateTime.now();
            report.setUpdatedAt(datetime);
            report.setCreatedAt(datetime);
            service.saveReport(report);
            // 一覧画面に遷移
            return "redirect:/report/allreport";
        }








}



