package com.techacademy.repository;

    import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;

import com.techacademy.entity.Employee;

    public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

        Employee save(Set<Integer> id);

    }


