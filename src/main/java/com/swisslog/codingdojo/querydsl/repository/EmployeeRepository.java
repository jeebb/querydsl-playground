package com.swisslog.codingdojo.querydsl.repository;

import com.querydsl.core.group.GroupBy;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.swisslog.codingdojo.querydsl.entity.Employee;
import com.swisslog.codingdojo.querydsl.entity.QEmployee;

import javax.persistence.EntityManager;
import java.util.List;

public class EmployeeRepository {

    private JPAQueryFactory queryFactory;
    private QEmployee employee;

    public EmployeeRepository(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
        employee = QEmployee.employee;
    }

    public List<Employee> list() {
        return queryFactory.selectFrom(employee).fetch();
    }

}
