package com.sf.demo.service;

import com.sf.demo.dao.EmployeeDAO;
import com.sf.demo.model.Employee;
import com.sf.demo.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.groupingBy;

@Service
public class EmployeeService {
    @Autowired
    EmployeeRepository employeeRepository;

    public List<Employee> getAllEmployees() {
        List<Employee> employees = new ArrayList<>();
        employeeRepository.findAll().forEach(employees::add);

//        Map<Integer, List<Employee>> managerEmployees = employees.stream()
//                .collect(groupingBy(emp -> Objects.isNull(emp.getManagerId()) ? -1 : emp.getManagerId()));
        List<EmployeeDAO> daoList = employees.stream().map(EmployeeDAO::createEmployeeDAO).collect(Collectors.toList());
        Map<Integer, EmployeeDAO> empMap = daoList.stream().collect(Collectors.toMap(EmployeeDAO::getId, Function.identity()));

        for (EmployeeDAO emp : daoList) {
            Integer managerId = emp.getManagerId();

            if (Objects.nonNull(managerId)) {
                EmployeeDAO manager = empMap.get(managerId);
                if (Objects.nonNull(manager)) {
                    emp.setManager(manager);
                    manager.addChild(emp);
//                    empMap.put(parentId, parent);
//                    mapTmp.put(current.getId(), current);
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

        assert ceo != null;
        System.out.println(ceo);

        return employees;
    }
}
