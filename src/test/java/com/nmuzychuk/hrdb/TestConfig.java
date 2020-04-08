package com.nmuzychuk.hrdb;

import com.github.javafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.data.repository.support.Repositories;

@TestConfiguration
public class TestConfig {

    @Autowired
    ApplicationContext applicationContext;

    @Bean
    Repositories repositories() {
        return new Repositories(applicationContext);
    }

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
    public Title title() {
        Title title = new Title();
        title.setTitle(faker().name().title());

        return title;
    }
}
