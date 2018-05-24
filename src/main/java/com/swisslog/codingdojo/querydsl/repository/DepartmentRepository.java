package com.swisslog.codingdojo.querydsl.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.swisslog.codingdojo.querydsl.entity.Department;
import com.swisslog.codingdojo.querydsl.entity.QDepartment;
import com.swisslog.codingdojo.querydsl.entity.QEmployee;

import javax.persistence.EntityManager;
import java.util.List;

public class DepartmentRepository {

    private JPAQueryFactory queryFactory;
    private QDepartment department;

    public DepartmentRepository(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
        department = QDepartment.department;
    }

    public List<Department> list() {
        return queryFactory.selectFrom(department).fetch();
    }

    public Department findByKey(Long key) {
        return queryFactory.selectFrom(department).where(department.id.eq(key)).fetchOne();
    }
}
