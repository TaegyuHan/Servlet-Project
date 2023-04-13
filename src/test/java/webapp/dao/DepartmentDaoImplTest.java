package webapp.dao;

import org.junit.jupiter.api.*;
import webapp.entity.Department;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;


@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class DepartmentDaoImplTest {

    private DepartmentDao dao;

    @BeforeEach
    void setUp() {
        dao = new DepartmentDaoImpl();
    }

    @AfterEach
    void tearDown() {
        dao = null;
    }

    @Test
    @Order(1)
    void create() {
        Department entity = new Department.Builder()
                .deptNo("d010")
                .deptName("Test Department")
                .build();

        assertFalse(dao.findByDeptNo("d010").isPresent());
        assertFalse(dao.findByDeptName("Test Department").isPresent());

        dao.create(entity);

        Optional<Department> optEntity;

        optEntity = dao.findByDeptNo("d010");
        assertTrue(optEntity.isPresent());
        assertEquals(entity, optEntity.get());

        optEntity = dao.findByDeptName("Test Department");
        assertTrue(optEntity.isPresent());
        assertEquals(entity, optEntity.get());
    }

    @Test
    @Order(2)
    void findAll() {
        List<Department> entities = dao.findAll();

        assertTrue(0 < entities.size());
    }

    @Test
    @Order(3)
    void findByDeptNo() {
        Optional<Department> optEntity = dao.findByDeptNo("d010");
        assertTrue(optEntity.isPresent());

        assertEquals("Test Department", optEntity.get().getDeptName());
    }

    @Test
    @Order(4)
    void findByDeptName() {
        Optional<Department> optEntity = dao.findByDeptName("Test Department");
        assertTrue(optEntity.isPresent());

        assertEquals("d010", optEntity.get().getDeptNo());
    }

    @Test
    @Order(5)
    void update() {
        Optional<Department> optEntity = dao.findByDeptNo("d010");
        assertTrue(optEntity.isPresent());

        Department entity = optEntity.get();
        entity.setDeptName("new Name");

        dao.update(entity);

        Optional<Department> updatedEntity = dao.findByDeptNo("d010");
        assertTrue(updatedEntity.isPresent());

        assertEquals(entity, updatedEntity.get());
    }

    @Test
    @Order(6)
    void delete() {
        Optional<Department> optEntity = dao.findByDeptNo("d010");
        assertTrue(optEntity.isPresent());

        int affectedRows = dao.delete(optEntity.get());
        assertEquals(1, affectedRows);

        assertFalse(dao.findByDeptNo("d010").isPresent());
    }
}