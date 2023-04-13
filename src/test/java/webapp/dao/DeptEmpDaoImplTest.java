package webapp.dao;

import org.junit.jupiter.api.*;
import webapp.entity.DeptEmp;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;


@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class DeptEmpDaoImplTest {

    private DeptEmpDao dao;

    @BeforeEach
    void setUp() {
        dao = new DeptEmpDaoImpl();
    }

    @Test
    @Order(1)
    void create() {
        DeptEmp entity = new DeptEmp.Builder()
                .empNo(10_001)
                .deptNo("d001")
                .fromDate(Date.valueOf("2020-01-01"))
                .toDate(Date.valueOf("2021-01-01"))
                .build();

        assertFalse(dao.findByEmpNoAndDeptNo(10_001, "d001")
                .isPresent());

        dao.create(entity);

        Optional<DeptEmp> optEntity = dao.findByEmpNoAndDeptNo(10_001, "d001");
        assertTrue(optEntity.isPresent());

        assertEquals(entity, optEntity.get());
    }

    @Test
    @Order(2)
    void findAll() {
        List<DeptEmp> entities = dao.findAll();

        assertTrue(0 < entities.size());
    }

    @Test
    @Order(3)
    void findByEmpNo() {
        List<DeptEmp> entities = dao.findByEmpNo(10_001);

        assertEquals(10_001, entities.get(0).getEmpNo());
    }

    @Test
    @Order(4)
    void findByDeptNo() {
        List<DeptEmp> entities = dao.findByDeptNo("d001");

        assertEquals("d001", entities.get(0).getDeptNo());
    }

    @Test
    @Order(5)
    void findByEmpNoAndDeptNo() {
        Optional<DeptEmp> optEntity = dao.findByEmpNoAndDeptNo(10_001, "d001");
        assertTrue(optEntity.isPresent());

        assertEquals(10_001, optEntity.get().getEmpNo());
        assertEquals("d001", optEntity.get().getDeptNo());
    }

    @Test
    @Order(6)
    void update() {
        Optional<DeptEmp> optEntity = dao.findByEmpNoAndDeptNo(10_001, "d001");
        assertTrue(optEntity.isPresent());

        DeptEmp entity = optEntity.get();
        entity.setToDate(Date.valueOf("2022-01-01"));

        dao.update(entity);

        Optional<DeptEmp> updatedEntity = dao.findByEmpNoAndDeptNo(10_001, "d001");
        assertTrue(updatedEntity.isPresent());
        
        assertEquals(entity, updatedEntity.get());
    }

    @Test
    @Order(7)
    void delete() {
        Optional<DeptEmp> optEntity = dao.findByEmpNoAndDeptNo(10_001, "d001");
        assertTrue(optEntity.isPresent());

        int affectedRows = dao.delete(optEntity.get());
        assertEquals(1, affectedRows);

        assertFalse(dao.findByEmpNoAndDeptNo(10_001, "d001").isPresent());
    }
}