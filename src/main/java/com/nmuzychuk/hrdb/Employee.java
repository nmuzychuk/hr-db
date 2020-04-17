package com.nmuzychuk.hrdb;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Entity
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull
    private String firstName;

    private String lastName;

    @ManyToMany
    private Set<Title> titles;

    @OneToMany(mappedBy = "manager")
    private Set<DepartmentManager> departmentManagers;

    @OneToMany(mappedBy = "employee")
    private Set<Salary> salaries;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Set<Title> getTitles() {
        return titles;
    }

    public void setTitles(Set<Title> titles) {
        this.titles = titles;
    }

    public Set<DepartmentManager> getDepartmentManagers() {
        return departmentManagers;
    }

    public void setDepartmentManagers(Set<DepartmentManager> departmentManagers) {
        this.departmentManagers = departmentManagers;
    }

    public Set<Salary> getSalaries() {
        return salaries;
    }

    public void setSalaries(Set<Salary> salaries) {
        this.salaries = salaries;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", titles=" + titles +
                ", departmentManagers=" + departmentManagers +
                ", salaries=" + salaries +
                '}';
    }
}
