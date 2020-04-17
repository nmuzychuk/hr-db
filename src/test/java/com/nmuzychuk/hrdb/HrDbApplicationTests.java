package com.nmuzychuk.hrdb;

import com.github.javafaker.Faker;
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
    Faker faker;

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

    private void assertChangesCountBy(int diff, Class klass, Runnable runnable) {
        JpaRepository repository = (JpaRepository) repositories.getRepositoryFor(klass).get();
        long count = repository.count();
        runnable.run();

        assertEquals(count + diff, repository.count());
    }
}
