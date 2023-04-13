package webapp.dao;

import org.junit.jupiter.api.*;
import webapp.entity.Employee;
import webapp.entity.Gender;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;


@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS) // 기존에 실행한 Test의 저장 변수 다른 곳에서도 실행
class EmployeeDaoImplTest {

    private EmployeeDao dao;
    private int createEmpNo;

    @BeforeEach
    void setUp() {
        dao = new EmployeeDaoImpl();
    }

    @Test
    @Order(1)
    void create() {
        Employee entity = new Employee.Builder()
                .birthDate(Date.valueOf("1990-01-01"))
                .firstName("John")
                .lastName("Doe")
                .gender(Gender.M)
                .hireDate(Date.valueOf("2022-01-01"))
                .build();

        Optional<Employee> result = dao.create(entity);
        assertTrue(result.isPresent());

        createEmpNo = result.get().getEmpNo();
        entity.setEmpNo(createEmpNo);

        assertEquals(entity, result.get());
    }

    @Test
    @Order(2)
    void findAll() {
        List<Employee> entities = dao.findAll();
        assertTrue(0 < entities.size());
    }

    @Test
    @Order(3)
    void findByEmpNo() {
        Optional<Employee> optEntity = dao.findByEmpNo(createEmpNo);
        assertTrue(optEntity.isPresent());

        assertEquals("John", optEntity.get().getFirstName());
        assertEquals("Doe", optEntity.get().getLastName());
    }

    @Test
    @Order(4)
    void update() {
        Optional<Employee> optEmployee = dao.findByEmpNo(createEmpNo);
        assertTrue(optEmployee.isPresent());

        Employee entity = optEmployee.get();
        entity.setFirstName("New Jane");
        entity.setLastName("New Doe");

        dao.update(entity);

        Optional<Employee> optEntity = dao.findByEmpNo(createEmpNo);
        assertTrue(optEntity.isPresent());

        assertEquals(entity, optEntity.get());
    }

    @Test
    @Order(5)
    void delete() {
        Optional<Employee> optEmployee = dao.findByEmpNo(createEmpNo);
        assertTrue(optEmployee.isPresent());

        int affectedRows = dao.delete(optEmployee.get());
        assertEquals(1, affectedRows);

        assertFalse(dao.findByEmpNo(createEmpNo).isPresent());
    }
}