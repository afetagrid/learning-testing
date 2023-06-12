package com.example.learningtesting.employee;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/employee")
@RequiredArgsConstructor
public class EmployeeController {
    private final EmployeeService employeeService;

    @GetMapping
    public List<Employee> getAllEmployees() {
        return employeeService.getAllEmployees();
    }

    @GetMapping(path = "/{email}")
    public Employee getEmployeeByEmail(@PathVariable("email") String email) {
        return employeeService.getEmployeeByEmail(email);
    }

    @PostMapping
    public void addNewEmployee(@RequestBody Employee employee) {
        employeeService.addEmployee(employee);
    }

    @DeleteMapping(path = "/{id}")
    public void deleteEmployeeById(@PathVariable("id") Long id) {
        employeeService.deleteEmployeeById(id);
    }
}
