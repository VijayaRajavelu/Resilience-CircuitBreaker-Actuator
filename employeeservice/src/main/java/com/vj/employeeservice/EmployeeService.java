package com.vj.employeeservice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import jakarta.annotation.PostConstruct;
import org.springframework.aop.framework.AopContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeService {
    private static final Logger logger = LoggerFactory.getLogger(EmployeeService.class);

    @Autowired
    private AddressService addressService;

    List<Employee> employeeList = new ArrayList<>();

    @PostConstruct
    public void populateEmployeeData(){
        Employee e1 = new Employee(1L, "Raj", "raj@gmail.com", "Manager");
        Employee e2 = new Employee(2L, "Sathya", "sathya@gmail.com", "Team Lead");
        Employee e3 = new Employee(3L, "Tanya", "tanya@gmail.com", "Senior Developer");
        Employee e4 = new Employee(4L, "Uma", "uma@gmail.com", "Junior Developer");

        employeeList.add(e1);
        employeeList.add(e2);
        employeeList.add(e3);
        employeeList.add(e4);
    }

    public Employee getEmployeeData(Long empId) {
        Employee emp = employeeList.stream()
                .filter(e -> e.getId().equals(empId))
                .findFirst()
                .orElse(null);
        if (emp != null) {
            EmployeeService proxy = (EmployeeService) AopContext.currentProxy();
            ResponseEntity<Address> addressResponse = proxy.getAddressData(empId);
            emp.setAddress(addressResponse.getBody());
        }
        return emp;
    }

    @CircuitBreaker(name = "addressservice", fallbackMethod = "fallbackAddress")
    @Retry(name = "addressservice")
    public ResponseEntity<Address> getAddressData(Long empId) {
        Address address = addressService.getAddressData(empId).getBody();
        return ResponseEntity.ok(address);
    }

    public ResponseEntity<Address> fallbackAddress(Long empId, Throwable t) {
        logger.error("Fallback triggered for empId={} due to: {}", empId, t.toString());
        Address fallback = new Address(empId, "N/A", "Fallback: Address Service Unavailable", t.getMessage());
        return ResponseEntity.ok(fallback);
    }

    public List<Employee> getAllEmployee(){
        List<Employee> employees = new ArrayList<>();
        for (Employee e : employeeList) {
            employees.add(new Employee(e.getId(), e.getName(), e.getEmail(), e.getRole()));
        }
        return employees;
    }

}