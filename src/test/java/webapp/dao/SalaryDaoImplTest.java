package webapp.dao;

import org.junit.jupiter.api.*;
import webapp.entity.Salary;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;


@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class SalaryDaoImplTest {

    private static SalaryDao dao;

    @BeforeAll
    static void setUp() {
        dao = new SalaryDaoImpl();
    }

    @Test
    @Order(1)
    void create() {
        Salary entity = new Salary.Builder()
                .empNo(10_001)
                .salary(50000)
                .fromDate(Date.valueOf("2022-01-01"))
                .toDate(Date.valueOf("2023-01-01"))
                .build();

        assertFalse(dao.findByEmpNoAndFromDate(10_001, Date.valueOf("2022-01-01"))
                .isPresent());

        dao.create(entity);

        Optional<Salary> optEntity = dao.findByEmpNoAndFromDate(10_001, Date.valueOf("2022-01-01"));
        assertTrue(optEntity.isPresent());

        assertEquals(entity, optEntity.get());
    }

    @Test
    @Order(2)
    void findAll() {
        List<Salary> entities = dao.findAll();

        assertTrue(0 < entities.size());
    }

    @Test
    @Order(3)
    void findByEmpNoAndFromDate() {
        Optional<Salary> optEntity = dao.findByEmpNoAndFromDate(10_001, Date.valueOf("2022-01-01"));
        assertTrue(optEntity.isPresent());

        assertEquals(10_001, optEntity.get().getEmpNo());
        assertEquals(Date.valueOf("2022-01-01"), optEntity.get().getFromDate());
    }

    @Test
    @Order(4)
    void findByEmpNo() {
        List<Salary> salaries = dao.findByEmpNo(10_001);

        assertEquals(10_001, salaries.get(0).getEmpNo());
    }

    @Test
    @Order(5)
    void findByFromDate() {
        List<Salary> salaries = dao.findByFromDate(Date.valueOf("2022-01-01"));

        assertEquals(Date.valueOf("2022-01-01"), salaries.get(0).getFromDate());
    }

    @Test
    @Order(6)
    void update() {
        Optional<Salary> optEntity = dao.findByEmpNoAndFromDate(10_001, Date.valueOf("2022-01-01"));
        assertTrue(optEntity.isPresent());

        Salary entity = optEntity.get();
        entity.setSalary(55000);
        entity.setToDate(Date.valueOf("2024-01-01"));

        dao.update(entity);

        Optional<Salary> updatedOptEntity = dao.findByEmpNoAndFromDate(10_001, Date.valueOf("2022-01-01"));
        assertTrue(updatedOptEntity.isPresent());

        assertEquals(entity, updatedOptEntity.get());
    }

    @Test
    @Order(7)
    void delete() {
        Optional<Salary> result = dao.findByEmpNoAndFromDate(10_001, Date.valueOf("2022-01-01"));
        assertTrue(result.isPresent());

        int changeRow = dao.delete(result.get());
        assertEquals(1, changeRow);

        assertFalse(dao.findByEmpNoAndFromDate(10_001, Date.valueOf("2022-01-01")).isPresent());
    }
}
