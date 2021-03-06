package com.nmuzychuk.hrdb;

import com.github.javafaker.Faker;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;

import java.time.LocalDate;

@TestConfiguration
public class TestConfig {

    @Bean
    public Faker faker() {
        return new Faker();
    }

    @Bean
    @Scope("prototype")
    public Employee employee() {
        Employee employee = new Employee();
        employee.setFirstName(faker().name().firstName());
        employee.setLastName(faker().name().lastName());

        return employee;
    }

    @Bean
    @Scope("prototype")
    public Department department() {
        Department department = new Department();
        department.setName(faker().lorem().word());

        return department;
    }

    @Bean
    @Scope("prototype")
    public DepartmentEmployee departmentEmployee() {
        DepartmentEmployee departmentEmployee = new DepartmentEmployee();
        departmentEmployee.setFromDate(LocalDate.now());
        departmentEmployee.setToDate(LocalDate.now().plusDays(30));

        return departmentEmployee;
    }

    @Bean
    @Scope("prototype")
    public DepartmentManager departmentManager() {
        DepartmentManager departmentManager = new DepartmentManager();
        departmentManager.setFromDate(LocalDate.now());
        departmentManager.setToDate(LocalDate.now().plusDays(30));

        return departmentManager;
    }

    @Bean
    @Scope("prototype")
    public Title title() {
        Title title = new Title();
        title.setTitle(faker().name().title());

        return title;
    }

    @Bean
    @Scope("prototype")
    public Salary salary() {
        Salary salary = new Salary();
        salary.setSalary(faker().number().numberBetween(1000_00, 2000_00));
        salary.setFromDate(LocalDate.now());
        salary.setToDate(LocalDate.now().plusDays(30));

        return salary;
    }
}
