package com.sf.demo.controller;

import com.sf.demo.dao.EmployeeDAO;
import com.sf.demo.service.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.ResponseEntity.ok;

@Slf4j
@RestController
@RequestMapping("/")
public class EmployeeController {
    private static final String LINE_BREAK = "\n";
    private static final String SPACES = "    ";
    @Autowired
    EmployeeService employeeService;

    @GetMapping(value="getAll", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<EmployeeDAO> getAllEmployees() {
        EmployeeDAO employeeTree = employeeService.getEmployeeTree();
        String response = displayEmployee(employeeTree, 1);
        log.info("\n" + response);
        return ResponseEntity.ok(employeeTree);
    }

    private String displayEmployee(EmployeeDAO emp, int number) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < number; i++) {
            sb.append(SPACES);
        }
        sb.append(emp.toString()).append(LINE_BREAK);
        if (!CollectionUtils.isEmpty(emp.getManagedEmployees())) {
            for (EmployeeDAO child: emp.getManagedEmployees()) {
                sb.append(displayEmployee(child, number + 1));
            }
        }
        return sb.toString();
    }
}
