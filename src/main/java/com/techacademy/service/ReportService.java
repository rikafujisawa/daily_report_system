package com.techacademy.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.techacademy.entity.Employee;
import com.techacademy.entity.Report;
import com.techacademy.repository.ReportRepository;



@Service
public class ReportService {
    private final ReportRepository reportRepository;

    @Autowired
    public ReportService(ReportRepository repository) {
        this.reportRepository = repository;

    }

    // 全件を検索して返す
    public List<Report> getReportList() {
        // リポジトリのfindAllメソッドを呼び出す
        return reportRepository.findAll();
    }
    public List<Report> getReportByEmp(Employee employee) {
        return reportRepository.findByEmp(employee);
    }

    /** 日報の登録を行なう */
    @Transactional
    public Report saveReport(Report report) {

        return reportRepository.save(report);
    }



    /** Reportを1件検索して返す */
    public Report getReport(Integer id) {
        return reportRepository.findById(id).get();
    }


    /** 日報の更新を行なう */
    @Transactional
    public Report setReport(Report report) {
        return reportRepository.save(report);
    }

    }


