package com.example.learningtesting.employee;

import com.example.learningtesting.exception.BadRequestException;
import com.example.learningtesting.exception.NotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class EmployeeServiceTest {

    @Mock
    private EmployeeRepo employeeRepo;
    private EmployeeService underTest;

    @BeforeEach
    void setUp() {
        underTest = new EmployeeService(employeeRepo);
    }

    @Test
    void shouldGetAllEmployees() {
        //when
        underTest.getAllEmployees();

        //then
        verify(employeeRepo).findAll();
    }

    @Test
    void shouldGetEmployeeByEmail() {
        //given
        String email = "example@email.com";
        Employee employee = new Employee("John Doe", email);
        given(employeeRepo.findByEmail(email)).willReturn(Optional.of(employee));

        //when
        Employee employeeByEmail = underTest.getEmployeeByEmail(email);

        //then
        assertThat(employeeByEmail).isEqualTo(employee);
    }

    @Test
    void shouldThrowExceptionWhenGettingEmployeeByEmail() {
        //given
        String email = "example@email.com";

        //when + then
        assertThatThrownBy(() -> underTest.getEmployeeByEmail(email))
                .isInstanceOf(NotFoundException.class)
                .hasMessage("Employee not found");
    }

    @Test
    void shouldAddEmployee() {
        //given
        Employee employee = new Employee("John Doe", "example@email.com");

        //when
        underTest.addEmployee(employee);

        //then
        ArgumentCaptor<Employee> employeeArgumentCaptor = ArgumentCaptor.forClass(Employee.class);
        verify(employeeRepo).save(employeeArgumentCaptor.capture());
        Employee capturedEmployee = employeeArgumentCaptor.getValue();
        assertThat(capturedEmployee).isEqualTo(employee);
    }

    @Test
    void shouldThrowExceptionWhenAddingEmployee() {
        //given
        Employee employee = new Employee("John Doe", "example@email.com");
        given(employeeRepo.findByEmail(employee.getEmail())).willReturn(Optional.of(employee));

        //when + then
        assertThatThrownBy(() -> underTest.addEmployee(employee))
                .isInstanceOf(BadRequestException.class)
                .hasMessage("Employee already added");
        verify(employeeRepo, never()).save(any());
    }

    @Test
    void shouldDeleteEmployeeById() {
        //given
        Long id = 1L;
        Employee employee = new Employee(id,"John Doe", "example@email.com");
        given(employeeRepo.findById(id)).willReturn(Optional.of(employee));

        //when
        underTest.deleteEmployeeById(id);

        //then
        ArgumentCaptor<Long> employeeIdArgumentCaptor = ArgumentCaptor.forClass(Long.class);
        verify(employeeRepo).deleteById(employeeIdArgumentCaptor.capture());
        Long capturedId = employeeIdArgumentCaptor.getValue();
        assertThat(capturedId).isEqualTo(id);
    }

    @Test
    void shouldThrowExceptionWhenDeletingEmployeeById() {
        //given
        Long id = 1L;

        //when + then
        assertThatThrownBy(() -> underTest.deleteEmployeeById(id))
                .isInstanceOf(NotFoundException.class)
                .hasMessage("Employee not found");
        verify(employeeRepo, never()).deleteById(any());
    }
}