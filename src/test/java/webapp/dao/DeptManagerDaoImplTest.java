package webapp.dao;

import org.junit.jupiter.api.*;
import webapp.entity.DeptManager;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;


@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class DeptManagerDaoImplTest {

    private DeptManagerDao dao;

    @BeforeEach
    void setUp() {
        dao = new DeptManagerDaoImpl();
    }

    @Test
    @Order(1)
    void create() {
        DeptManager entity = new DeptManager.Builder()
                .empNo(10_001)
                .deptNo("d001")
                .fromDate(Date.valueOf("2020-01-01"))
                .toDate(Date.valueOf("2021-01-01"))
                .build();

        assertFalse(dao.findByEmpNoAndDeptNo(10_001, "d001")
                .isPresent());

        dao.create(entity);

        Optional<DeptManager> optEntity = dao.findByEmpNoAndDeptNo(10_001, "d001");
        assertTrue(optEntity.isPresent());

        assertEquals(entity, optEntity.get());
    }

    @Test
    @Order(2)
    void findByEmpNoAndDeptNo() {
        Optional<DeptManager> optEntity = dao.findByEmpNoAndDeptNo(10_001, "d001");
        assertTrue(optEntity.isPresent());

        assertEquals(10_001, optEntity.get().getEmpNo());
        assertEquals("d001", optEntity.get().getDeptNo());
    }

    @Test
    @Order(3)
    void findByEmpNo() {
        List<DeptManager> entities = dao.findByEmpNo(10_001);

        assertEquals(10_001, entities.get(0).getEmpNo());
    }

    @Test
    @Order(4)
    void findByDeptNo() {
        List<DeptManager> entities = dao.findByDeptNo("d001");

        assertEquals("d001", entities.get(0).getDeptNo());
    }

    @Test
    @Order(5)
    void update() {
        Optional<DeptManager> optEntity = dao.findByEmpNoAndDeptNo(10_001, "d001");
        assertTrue(optEntity.isPresent());

        DeptManager entity = optEntity.get();
        entity.setToDate(Date.valueOf("2022-01-01"));

        dao.update(entity);

        Optional<DeptManager> updatedOptEntity = dao.findByEmpNoAndDeptNo(10_001, "d001");
        assertTrue(updatedOptEntity.isPresent());

        assertEquals(entity, updatedOptEntity.get());
    }

    @Test
    @Order(6)
    void delete() {
        Optional<DeptManager> optEntity = dao.findByEmpNoAndDeptNo(10_001, "d001");
        assertTrue(optEntity.isPresent());

        int affectedRows = dao.delete(optEntity.get());
        assertEquals(1, affectedRows);

        assertFalse(dao.findByEmpNoAndDeptNo(10_001, "d001").isPresent());
    }
}