package com.swisslog.codingdojo.querydsl.repository;

import com.swisslog.codingdojo.querydsl.entity.Employee;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.Map;

import static junit.framework.TestCase.assertNull;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class EmployeeRepositoryTest extends BaseRepositoryTest {

    private EmployeeRepository employeeRepository;

    @Before
    public void setup() {
        super.setup();

        employeeRepository = new EmployeeRepository(em);
    }

    @Test
    public void testList() {
        assertEquals("Invalid number of employees", 11, employeeRepository.listAll().size());
    }

    @Test
    public void testFindByCode() {
        assertNotNull("Employee not found", employeeRepository.findByCode("E1"));
        assertNull("Employee should not be found", employeeRepository.findByCode("E12"));
    }

    @Test
    public void testCountByGenderAndMinAge() {
        assertEquals("Invalid count", 5, employeeRepository.countByGenderAndMinAge(true, 20));
        assertEquals("Invalid count", 6, employeeRepository.countByGenderAndMinAge(false, 20));
        assertEquals("Invalid count", 3, employeeRepository.countByGenderAndMinAge(true, 25));
        assertEquals("Invalid count", 0, employeeRepository.countByGenderAndMinAge(true, 35));
    }

    @Test
    public void testFindOldestOne() {
        Employee oldestOne = employeeRepository.findOldestOne();
        assertNotNull("Oldest employee not found", oldestOne);
        assertEquals("Invalid employee", "E11", oldestOne.getCode());
    }

    @Test
    public void testFindByDepartmentCode() {
        assertEquals("Invalid count", 3, employeeRepository.findByDepartmentCode("SALE").size());
        assertEquals("Invalid count", 0, employeeRepository.findByDepartmentCode("SEC").size());
    }

    @Test
    public void testGroupByGender() {
        Map<Boolean, List<Employee>> employeeByGenderMap = employeeRepository.groupByGender();

        assertEquals("Invalid number of male", 5, employeeByGenderMap.get(true).size());
        assertEquals("Invalid number of female", 6, employeeByGenderMap.get(false).size());
    }
}
