package com.example.learningtesting.employee;

import com.example.learningtesting.exception.BadRequestException;
import com.example.learningtesting.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeService {
    private final EmployeeRepo employeeRepo;

    public List<Employee> getAllEmployees() {
        return employeeRepo.findAll();
    }

    public Employee getEmployeeByEmail(String email) {
        return employeeRepo.findByEmail(email).orElseThrow(() -> new NotFoundException("Employee not found"));
    }

    public void addEmployee(Employee employee) {
        if (employeeRepo.findByEmail(employee.getEmail()).isPresent()) {
            throw new BadRequestException("Employee already added");
        }
        employeeRepo.save(employee);
    }

    public void deleteEmployeeById(Long id) {
        if (employeeRepo.findById(id).isEmpty()) {
            throw new NotFoundException("Employee not found");
        }
        employeeRepo.deleteById(id);
    }
}
