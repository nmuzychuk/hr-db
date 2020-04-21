package com.nmuzychuk.hrdb;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class DepartmentEmployee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    private Department department;

    @ManyToOne
    private Employee employee;

    private LocalDate fromDate;

    private LocalDate toDate;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public LocalDate getFromDate() {
        return fromDate;
    }

    public void setFromDate(LocalDate fromDate) {
        this.fromDate = fromDate;
    }

    public LocalDate getToDate() {
        return toDate;
    }

    public void setToDate(LocalDate toDate) {
        this.toDate = toDate;
    }

    @Override
    public String toString() {
        return "DepartmentEmployee{" +
                "id=" + id +
                ", department=" + department +
                ", employee=" + employee +
                ", fromDate=" + fromDate +
                ", toDate=" + toDate +
                '}';
    }
}
