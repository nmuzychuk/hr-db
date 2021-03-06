package com.nmuzychuk.hrdb;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Import;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.support.Repositories;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@Import(TestConfig.class)
@SpringBootTest
@Transactional
class HrDbApplicationTests {

    @Autowired
    ApplicationContext applicationContext;

    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    TitleRepository titleRepository;

    @Autowired
    DepartmentRepository departmentRepository;

    @Autowired
    DepartmentEmployeeRepository departmentEmployeeRepository;

    @Autowired
    DepartmentManagerRepository departmentManagerRepository;

    @Autowired
    SalaryRepository salaryRepository;

    @Autowired
    Repositories repositories;

    @Test
    void contextLoads() {
    }

    @Test
    void savesEmployee() {
        assertChangesCountBy(1, Employee.class, () -> {
            employeeRepository.save(applicationContext.getBean(Employee.class));
        });
    }

    @Test
    void removesEmployee() {
        employeeRepository.save(applicationContext.getBean(Employee.class));

        assertChangesCountBy(-1, Employee.class, () -> {
            employeeRepository.delete(employeeRepository.findTopByOrderByIdDesc());
        });
    }

    @Test
    void savesDepartment() {
        assertChangesCountBy(1, Department.class, () -> {
            departmentRepository.save(applicationContext.getBean(Department.class));
        });
    }

    @Test
    void savesDepartmentEmployee() {
        assertChangesCountBy(1, DepartmentEmployee.class, () -> {
            departmentEmployeeRepository.save(applicationContext.getBean(DepartmentEmployee.class));
        });
    }

    @Test
    void savesDepartmentManager() {
        assertChangesCountBy(1, DepartmentManager.class, () -> {
            departmentManagerRepository.save(applicationContext.getBean(DepartmentManager.class));
        });
    }

    @Test
    void savesTitle() {
        assertChangesCountBy(1, Title.class, () -> {
            titleRepository.save(applicationContext.getBean(Title.class));
        });
    }

    @Test
    void savesSalary() {
        assertChangesCountBy(1, Salary.class, () -> {
            salaryRepository.save(applicationContext.getBean(Salary.class));
        });
    }

    @Test
    @Transactional(Transactional.TxType.NOT_SUPPORTED)
    void throwsSavingTitleThroughEmployeeWithoutCascade() {
        Employee employee = applicationContext.getBean(Employee.class);
        Set<Title> set = new HashSet<>();
        set.add(applicationContext.getBean(Title.class));
        employee.setTitles(set);

        assertThrows(InvalidDataAccessApiUsageException.class, () -> {
            employeeRepository.save(employee);
        });
    }

    @Test
    void doesNotSaveEmployeeThroughTitle() {
        Title title = applicationContext.getBean(Title.class);
        Set<Employee> set = new HashSet<>();
        set.add(applicationContext.getBean(Employee.class));
        title.setEmployees(set);

        assertChangesCountBy(0, Employee.class, () -> {
            titleRepository.save(title);
        });
    }

    @Test
    void savesSalaryThroughEmployee() {
        Employee employee = applicationContext.getBean(Employee.class);

        Salary salary = applicationContext.getBean(Salary.class);
        salary.setEmployee(employee);

        Set<Salary> salaries = new HashSet<>();
        salaries.add(salary);

        employee.setSalaries(salaries);

        employee = employeeRepository.save(employee);

        assertEquals(1, employee.getSalaries().size());
    }

    private void assertChangesCountBy(int diff, Class klass, Runnable runnable) {
        JpaRepository repository = (JpaRepository) repositories.getRepositoryFor(klass).get();
        long count = repository.count();
        runnable.run();

        assertEquals(count + diff, repository.count());
    }
}
