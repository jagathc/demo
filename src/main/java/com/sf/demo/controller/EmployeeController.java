package com.sf.demo.controller;

import com.sf.demo.model.Employee;
import com.sf.demo.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/")
public class EmployeeController {
    @Autowired
    EmployeeService employeeService;

    @GetMapping("getAll")
    public List<Employee> getAllEmployees() {
        return employeeService.getAllEmployees();
    }
}
