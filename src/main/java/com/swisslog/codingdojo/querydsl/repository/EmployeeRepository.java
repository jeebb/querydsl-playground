package com.swisslog.codingdojo.querydsl.repository;

import com.querydsl.core.group.GroupBy;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.swisslog.codingdojo.querydsl.entity.Employee;
import com.swisslog.codingdojo.querydsl.entity.QEmployee;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Map;

public class EmployeeRepository {

    private JPAQueryFactory queryFactory;
    private QEmployee employee;

    public EmployeeRepository(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
        employee = QEmployee.employee;
    }

    public List<Employee> listAll() {
        return queryFactory.selectFrom(employee).fetch();
    }

    public Employee findByCode(String code) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    public long countByGenderAndMinAge(boolean gender, int minAge) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    public Employee findOldestOne() {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    public List<Employee> findByDepartmentCode(String departmentCode) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    public Map<Boolean, List<Employee>> groupByGender() {
        throw new UnsupportedOperationException("Not implemented yet");
    }

}
