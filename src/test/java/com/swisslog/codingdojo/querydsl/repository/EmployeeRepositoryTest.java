package com.swisslog.codingdojo.querydsl.repository;

import com.swisslog.codingdojo.querydsl.entity.Department;
import com.swisslog.codingdojo.querydsl.entity.Employee;
import com.swisslog.codingdojo.querydsl.entity.EmployeeTitle;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import static org.junit.Assert.assertEquals;

public class EmployeeRepositoryTest {

    private EntityManager em;

    private DepartmentRepository departmentRepository;
    private EmployeeRepository employeeRepository;

    @Before
    public void setup() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
        em = emf.createEntityManager();

        departmentRepository = new DepartmentRepository(em);
        employeeRepository = new EmployeeRepository(em);

        prepareDepartments();
        prepareEmployees();
    }

    @Test
    public void testPreparedData() {
        assertEquals("Invalid number of departments", 5, departmentRepository.list().size());
        assertEquals("Invalid number of employees", 10, employeeRepository.list().size());
    }

    private void prepareDepartments() {
        em.getTransaction().begin();

        em.persist(new Department("Sale"));
        em.persist(new Department("HR"));
        em.persist(new Department("IT"));
        em.persist(new Department("Security"));
        em.persist(new Department("Support"));

        em.getTransaction().commit();
    }

    private void prepareEmployees() {
        em.getTransaction().begin();

        for (int i = 1; i <= 10; i++) {
            em.persist(new Employee(
                    "Employee" + i,
                    i % 2 == 0,
                    20 + i,
                    i % 2 == 0 ? "VN" : "DE",
                    i % 2 == 0 ? EmployeeTitle.OFFICER : EmployeeTitle.MANAGER,
                    departmentRepository.findByKey(Long.valueOf(i % 3 + 1))
            ));
        }

        em.getTransaction().commit();
    }

    @After
    public void tearDown() {
        if (em != null) {
            em.close();
        }
    }
}
