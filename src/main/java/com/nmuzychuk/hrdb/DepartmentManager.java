package com.nmuzychuk.hrdb;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class DepartmentManager {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    private Department department;

    @ManyToOne
    private Employee manager;

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

    public Employee getManager() {
        return manager;
    }

    public void setManager(Employee manager) {
        this.manager = manager;
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
        return "DepartmentManager{" +
                "id=" + id +
                ", department=" + department +
                ", manager=" + manager +
                ", fromDate=" + fromDate +
                ", toDate=" + toDate +
                '}';
    }
}
