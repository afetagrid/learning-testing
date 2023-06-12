package com.example.learningtesting.employee;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
class EmployeeRepoTest {

    @Autowired
    private EmployeeRepo underTest;

    @AfterEach
    void tearDown() {
        underTest.deleteAll();
    }

    @Test
    void shouldCheckIfEmployeeEmailExists() {
        //given
        String email = "example@email.com";
        Employee employee = new Employee("John Doe", email);
        underTest.save(employee);

        //when
        boolean exists = underTest.findByEmail(email).isPresent();

        //then
        assertThat(exists).isTrue();
    }

    @Test
    void shouldCheckIfEmployeeEmailDoesNotExist() {
        //given
        String email = "example@email.com";

        //when
        boolean exists = underTest.findByEmail(email).isPresent();

        //then
        assertThat(exists).isFalse();
    }

}