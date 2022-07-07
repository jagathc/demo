package com.sf.demo.dao;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sf.demo.model.Employee;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
public class EmployeeDAO {

    private Integer id;
    @JsonIgnore
    private Integer managerId;
    private String name;
    @JsonIgnore
    private EmployeeDAO manager;
    private List<EmployeeDAO> managedEmployees;

    public static EmployeeDAO createEmployeeDAO(Employee employee) {
        EmployeeDAO employeeDAO = new EmployeeDAO();
        employeeDAO.setId(employee.getId());
        employeeDAO.setManagerId(employee.getManagerId());
        employeeDAO.setName(employee.getName());
        return employeeDAO;
    }

    public void addManagedEmployee(EmployeeDAO employee) {
        if (Objects.isNull(managedEmployees)) {
            managedEmployees = new ArrayList<>();
        }
        managedEmployees.add(employee);
    }

    @Override
    public String toString() {
        return "[" + id + "] " + name;
    }
}
