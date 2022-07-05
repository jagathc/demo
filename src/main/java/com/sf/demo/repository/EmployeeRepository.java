package com.sf.demo.repository;

import com.sf.demo.model.Employee;
import org.springframework.data.repository.CrudRepository;

public interface EmployeeRepository extends CrudRepository<Employee, Integer> {}
