package com.sf.demo.service;

import com.sf.demo.dao.EmployeeDAO;
import com.sf.demo.model.Employee;
import com.sf.demo.repository.EmployeeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

@Slf4j
@Service
public class EmployeeService {
    @Autowired
    EmployeeRepository employeeRepository;

    public EmployeeDAO getEmployeeTree() {
        List<Employee> employees = new ArrayList<>();
        employeeRepository.findAll().forEach(employees::add);
        log.info("Retrieved employee list from the database.");
        return buildTree(employees);
    }

    private EmployeeDAO buildTree(List<Employee> employees) {
        List<EmployeeDAO> daoList = employees.stream().map(EmployeeDAO::createEmployeeDAO).collect(Collectors.toList());
        Map<Integer, EmployeeDAO> empMap = daoList.stream().collect(Collectors.toMap(EmployeeDAO::getId, Function.identity()));

        for (EmployeeDAO emp : daoList) {
            Integer managerId = emp.getManagerId();
            if (Objects.nonNull(managerId)) {
                EmployeeDAO manager = empMap.get(managerId);
                if (Objects.nonNull(manager)) {
                    emp.setManager(manager);
                    manager.addManagedEmployee(emp);
                }
            }
        }

        EmployeeDAO ceo = null;
        for (EmployeeDAO emp : empMap.values()) {
            if(Objects.isNull(emp.getManager())) {
                ceo = emp;
                break;
            }
        }
        return ceo;
    }
}
