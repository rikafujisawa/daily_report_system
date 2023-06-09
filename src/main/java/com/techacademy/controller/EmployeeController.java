package com.techacademy.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.password.PasswordEncoder;
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
import com.techacademy.service.EmployeeService;
import com.techacademy.service.UserDetail;

@Controller
@RequestMapping("employee")
public class EmployeeController {
    private final EmployeeService service;

    public EmployeeController(EmployeeService service) {
        this.service = service;
    }

    // ----- 一覧画面 -----
    @GetMapping("/list")
    public String getList(@AuthenticationPrincipal UserDetail user, Model model) {
        model.addAttribute("user", user);
        // 全件検索結果をModelに登録
        List<Employee> elist = service.getEmployeeList();
        model.addAttribute("emplist", elist);
        // employee/list.htmlに画面遷移
        return "employee/list";
    }

    // ----- 詳細画面 -----
    /** Employeeを1件検索して返す */
    @GetMapping("/detail/{id}/")
    public String getDetail(@AuthenticationPrincipal UserDetail user, @PathVariable("id") Integer id, Model model) {
        model.addAttribute("user", user);
        model.addAttribute("emp", service.getEmployee(id));
        // employee/detail.htmlに画面遷移
        return "employee/detail";
    }

    /** 従業員情報編集ページを表示 */
    @GetMapping("/update/{id}/")
    public String getEmployee(@AuthenticationPrincipal UserDetail user, @PathVariable("id") Integer id, Model model) {
        model.addAttribute("user", user);
        // Employee登録
        model.addAttribute("emp", service.getEmployee(id));
        // 一覧画面に遷移
        return "employee/update";
    }

    /** 従業員情報編集処理 */

    @PostMapping("/update/{id}/")
    public String postEmployee(@AuthenticationPrincipal UserDetail user, @RequestParam("newpass") String newpass, @Validated Employee emp, BindingResult res,
            Model model) {
        model.addAttribute("user", user);
        // Employee更新
        if (res.hasErrors()) {
            // エラーあり
            return getEmployee(user, emp.getId(), model);
        }

        @SuppressWarnings("unused")
        Employee tableEmployee = service.getEmployee(emp.getId());
        Authentication au = emp.getAuthentication();

        if (newpass.equals("")) {
            au.setPassword(tableEmployee.getAuthentication().getPassword());
        } else {
            // パスワードの暗号化
            String pass = passwordEncoder.encode(newpass);
            au.setPassword(pass);
        }
        au.setEmp(emp);
        LocalDateTime datetime = LocalDateTime.now();
        emp.setUpdatedAt(datetime);
        emp.setDelete_flag(0);
        service.saveEmployee(emp);

        // 一覧画面に遷移
        return "redirect:/employee/list";
    }

    /** 従業員 論理削除処理 */
    @GetMapping("/delete/{id}")
    public String deleteEmployee(@PathVariable("id") Integer id) {

        Employee emp = service.getEmployee(id);
        emp.setDelete_flag(1);
        service.saveEmployee(emp);

        return "redirect:/employee/list";
    }

    /** Employee登録画面を表示 */
    @GetMapping("/register")
    public String getRegister(@AuthenticationPrincipal UserDetail user, @ModelAttribute Employee emp, Model model) {
        model.addAttribute("user", user);
        // 登録画面に遷移
        return "employee/register";
    }

    /** Employee登録処理 */
    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/register")
    public String postRegister(@AuthenticationPrincipal UserDetail user, @Validated Employee emp, BindingResult res, Model model) {
        model.addAttribute("user", user);
        if (res.hasErrors()) {
            // エラーあり
            return getRegister(user, emp, model);
        }

        // 新規登録
        Authentication au = emp.getAuthentication();
        // パスワードの暗号化
        String password = emp.getAuthentication().getPassword();
        au.setPassword(passwordEncoder.encode(password));
        // 暗号化後の登録
        au.setEmp(emp);
        LocalDateTime datetime = LocalDateTime.now();
        emp.setCreatedAt(datetime);
        emp.setUpdatedAt(datetime);
        emp.setDelete_flag(0);
        service.saveEmployee(emp);
        // 一覧画面にリダイレクト

        return "redirect:/employee/list";
    }

}
