package com.vj.employeeservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/emp")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/EmployeeData/{empId}")
    public ResponseEntity<Employee> getData(@PathVariable Long empId){
        Employee emp = employeeService.getEmployeeData(empId);
        if(emp != null){
            return ResponseEntity.ok(emp);
        }
        return ResponseEntity.notFound().build();
    }

}
