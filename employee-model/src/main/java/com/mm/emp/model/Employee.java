package com.mm.emp.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "employee")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    long id;
    @Column(name = "f_name")
    String fName;
    @Column(name = "l_name")
    String lName;
    String email;
    String mobile;
}
