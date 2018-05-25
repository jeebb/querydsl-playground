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
        return queryFactory.selectFrom(employee).where(employee.code.eq(code)).fetchOne();
    }

    public long countByGenderAndMinAge(boolean gender, int minAge) {
        return queryFactory.selectFrom(employee)
                .where(employee.gender.eq(gender), employee.age.goe(minAge))
                .fetchCount();
    }

    public Employee findOldestOne() {
        return queryFactory.selectFrom(employee).orderBy(employee.age.desc()).limit(1).fetchOne();
    }

    public List<Employee> findByDepartmentCode(String departmentCode) {
        return queryFactory.selectFrom(employee).where(employee.department.code.eq(departmentCode)).fetch();
    }

    public Map<Boolean, List<Employee>> groupByGender() {
        return queryFactory.selectFrom(employee).transform(GroupBy.groupBy(employee.gender).as(GroupBy.list(employee)));
    }

}
