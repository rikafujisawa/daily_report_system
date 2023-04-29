package com.techacademy.controller;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

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

@Controller
@RequestMapping("employee")
public class EmployeeController {
    private final EmployeeService service;

    public EmployeeController(EmployeeService service) {
        this.service = service;
    }

    // ----- 一覧画面 -----
    @GetMapping("/list")
    public String getList(Model model) {
        // 全件検索結果をModelに登録
        List<Employee> elist=service.getEmployeeList();
        model.addAttribute("emplist", elist);
        // employee/list.htmlに画面遷移
        return "employee/list";
    }

    // ----- 詳細画面 -----
    /** Employeeを1件検索して返す */
    @GetMapping("/detail/{id}/")
    public String getDetail(@PathVariable("id") Integer id, Model model) {
        model.addAttribute("emp", service.getEmployee(id));
        // employee/detail.htmlに画面遷移
        return "employee/detail";
    }

    /** 従業員情報編集ページを表示 */
    @GetMapping("/update/{id}/")
    public String getEmployee(@PathVariable("id") Integer id, Model model) {
        //Employee登録
        model.addAttribute("emp", service.getEmployee(id));
        // 一覧画面に遷移
        return "employee/update";
    }

    /** 従業員情報編集処理 */
    @PostMapping("/update/{id}/")
    public String postEmployee(Employee emp) {
        //Employee登録
        service.saveEmployee(emp);
        // 一覧画面に遷移
        return "redirect:/employee/list";
    }

    /** 従業員削除処理 */
    @PostMapping(path="list", params="deleteRun")
    public String deleteRun(@RequestParam(name="idck") Set<Integer> idck, Model model) {
        // Userを一括削除
        service.deleteEmployee(idck);
        // 一覧画面にリダイレクト
        return "redirect:/employee/list";
    }


    /** Employee登録画面を表示 */
    @GetMapping("/register")
    public String getRegister(@ModelAttribute Employee emp) {
        // 登録画面に遷移
        return "employee/register";
    }

    /** Employee登録処理 */
    @PostMapping("/register")
    public String postRegister(@Validated Employee emp,
            BindingResult res, Model model) {
             if(res.hasErrors()) {
               //エラーあり
                 return getRegister(emp);
             }
        // 新規登録
        Authentication au=emp.getAuthentication();
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

