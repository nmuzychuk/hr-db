package com.nmuzychuk.hrdb;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("cached-employees")
    public List<EmployeeDto> findAllCached() {
        return employeeService.getAllEmployees();
    }
}
