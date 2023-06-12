package com.example.learningtesting.employee;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "employees")
@AllArgsConstructor
@Setter
@Getter
@ToString
public class Employee {
    @Id
    @SequenceGenerator(name = "empSeq", sequenceName = "emp_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "empSeq")
    private Long id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false, unique = true)
    private String email;

    public Employee(String name, String email) {
        this.name = name;
        this.email = email;
    }
}
