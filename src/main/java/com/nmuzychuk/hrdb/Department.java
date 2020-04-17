package com.nmuzychuk.hrdb;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Entity
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull
    private String name;

    @OneToMany(mappedBy = "department")
    private Set<DepartmentManager> departmentManagers;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<DepartmentManager> getDepartmentManagers() {
        return departmentManagers;
    }

    public void setDepartmentManagers(Set<DepartmentManager> departmentManagers) {
        this.departmentManagers = departmentManagers;
    }

    @Override
    public String toString() {
        return "Department{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", departmentManagers=" + departmentManagers +
                '}';
    }
}
