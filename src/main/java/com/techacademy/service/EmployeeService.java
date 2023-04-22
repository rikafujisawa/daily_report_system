package com.techacademy.service;

    import java.util.List;

    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.stereotype.Service;

    import com.techacademy.entity.Employee;
    import com.techacademy.repository.EmployeeRepository;

    @Service
    public class EmployeeService {
            private final EmployeeRepository repository;

            @Autowired
            public EmployeeService(EmployeeRepository repository) {
                this.repository = repository;
            }

            // 全件を検索して返す
            public List<Employee> getEmployeeList() {
                // リポジトリのfindAllメソッドを呼び出す
                return repository.findAll();
            }

    }


