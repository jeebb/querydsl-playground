package com.swisslog.codingdojo.querydsl.repository;

import com.swisslog.codingdojo.querydsl.entity.Department;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class DepartmentRepositoryTest extends BaseRepositoryTest {

    private DepartmentRepository departmentRepository;

    @Before
    public void setup() {
        super.setup();

        departmentRepository = new DepartmentRepository(em);
    }

    @Test
    public void testListAll() {
        assertEquals("Invalid number of departments", 5, departmentRepository.listAll().size());
    }

    @Test
    public void testFindEmptyDepartments() {
        List<Department> departments = departmentRepository.findEmptyDepartments();
        assertEquals("Invalid number of empty departments", 2, departments.size());

        List<String> departmentCodes = departments.stream()
                .map(department -> department.getCode()).collect(Collectors.toList());
        assertTrue("Invalid department codes",
                departmentCodes.contains("SEC") && departmentCodes.contains("CS"));
    }
}
