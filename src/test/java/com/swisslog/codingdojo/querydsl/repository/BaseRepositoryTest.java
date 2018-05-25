package com.swisslog.codingdojo.querydsl.repository;

import com.swisslog.codingdojo.querydsl.entity.Department;
import com.swisslog.codingdojo.querydsl.entity.Employee;
import com.swisslog.codingdojo.querydsl.entity.EmployeeTitle;
import org.junit.After;
import org.junit.Before;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public abstract class BaseRepositoryTest {

    protected EntityManager em;

    private DepartmentRepository departmentRepository;

    private static boolean setupDone = false;

    @Before
    public void setup() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
        em = emf.createEntityManager();

        if (!setupDone) {
            setupDone = true;

            departmentRepository = new DepartmentRepository(em);

            prepareDepartments();
            prepareEmployees();
        }
    }

    private void prepareDepartments() {
        em.getTransaction().begin();

        em.persist(new Department("SALE", "Sale"));
        em.persist(new Department("HR", "HR"));
        em.persist(new Department("IT", "IT"));
        em.persist(new Department("SEC", "Security"));
        em.persist(new Department("CS", "Support"));

        em.getTransaction().commit();
    }

    private void prepareEmployees() {
        em.getTransaction().begin();

        for (int i = 1; i <= 11; i++) {
            em.persist(new Employee(
                    "E" + i,
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
