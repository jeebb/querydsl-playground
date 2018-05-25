package com.swisslog.codingdojo.querydsl.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.util.Date;

@Entity
public class Employee {

    @Id
    @GeneratedValue
    private Long id;

    @Column(unique = true)
    private String code;

    @Column
    private String name;

    @Column
    private Boolean gender;

    @Column
    private Integer age;

    @Column
    private String nationality;

    @Column
    @Enumerated
    private EmployeeTitle title;

    @ManyToOne
    private Department department;

    public Employee() {

    }

    public Employee(String code, String name, Boolean gender, Integer age, String nationality, EmployeeTitle title, Department department) {
        this.code = code;
        this.name = name;
        this.gender = gender;
        this.age = age;
        this.nationality = nationality;
        this.title = title;
        this.department = department;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getGender() {
        return gender;
    }

    public void setGender(Boolean gender) {
        this.gender = gender;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public EmployeeTitle getTitle() {
        return title;
    }

    public void setTitle(EmployeeTitle title) {
        this.title = title;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
