package com.techacademy.repository;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;

import com.techacademy.entity.Report;

public interface ReportRepository extends JpaRepository<Report, Integer> {

    Report save(Set<Integer> id);

}




