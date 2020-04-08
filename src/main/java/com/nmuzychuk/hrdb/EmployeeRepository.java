package com.nmuzychuk.hrdb;

import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    Employee findTopByOrderByIdDesc();
}
