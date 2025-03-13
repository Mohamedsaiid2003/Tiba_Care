package com.example.TibaCare.department;


import com.example.TibaCare.staff.Staff;
import jakarta.persistence.*;

import java.time.LocalDate;

import static jakarta.persistence.GenerationType.SEQUENCE;

@Entity(name = "department")

public class Department extends Staff {
    @Id
    @SequenceGenerator(
            name = "department_sequence",
            sequenceName = "departments_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = SEQUENCE,
            generator = "department_sequence"
    )
    @Column(
            name = "id",
            updatable = false
    )
    private Long id;
    private String department_name;

    public Department() {
    }

    public Department(Long id, String firstname, String lastname,
                      String gender, String role, String mobilnumber,
                      String email, String adress, String national_identity_card,
                      String password, LocalDate date_of_birth, Department department,
                      String department_name) {
        this.department_name = department_name;
        this.id = id;
    }

    public Department(String firstname, String lastname, String gender,
                      String role, String mobilnumber, String email,
                      String adress, String national_identity_card,
                      String password, LocalDate date_of_birth,
                      Department department, String department_name) {
        super(firstname, lastname, gender, role, mobilnumber, email, adress, national_identity_card, password, date_of_birth, department);
        this.department_name = department_name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDepartment_name() {
        return this.department_name;
    }

    public void setDepartment_name() {
        this.department_name = department_name;
    }

    @Override
    public String toString() {
        return "Department{" +
                "id=" + id +
                ", department_name='" + department_name + '\'' +
                '}';
    }
}
