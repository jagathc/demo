package com.sf.demo.dao;

import com.sf.demo.model.Employee;
import lombok.Getter;
import lombok.Setter;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
public class EmployeeDAO {

    private Integer id;
    private Integer managerId;
    private String name;
    private EmployeeDAO manager;
    private List<EmployeeDAO> children;

    public static EmployeeDAO createEmployeeDAO(Employee employee) {
        EmployeeDAO employeeDAO = new EmployeeDAO();
        employeeDAO.setId(employee.getId());
        employeeDAO.setManagerId(employee.getManagerId());
        employeeDAO.setName(employee.getName());
        return employeeDAO;
    }

    public void addChild(EmployeeDAO child) {
        if (Objects.isNull(children)) {
            children = new ArrayList<>();
        }
        children.add(child);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(id + " : " + name );

        if (!CollectionUtils.isEmpty(children)) {
            children.forEach(child -> sb.append(child.toString()));
        }

        return sb.toString();
    }
}
