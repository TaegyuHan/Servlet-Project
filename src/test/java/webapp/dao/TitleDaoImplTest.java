package webapp.dao;

import org.junit.jupiter.api.*;
import webapp.entity.Title;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;


@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class TitleDaoImplTest {

    private static TitleDaoImpl dao;

    @BeforeAll
    public static void setup() {
        dao = new TitleDaoImpl();
    }

    @Test
    @Order(1)
    public void create() {
        Title entity = new Title.Builder()
                .empNo(10_001)
                .title("Engineer Test")
                .fromDate(Date.valueOf("2022-01-01"))
                .toDate(Date.valueOf("9999-01-01"))
                .build();

        assertFalse(dao.findByEmpNoAndTitleAndFromDate(
                10_001, "Engineer Test", Date.valueOf("2022-01-01"))
                .isPresent());

        dao.create(entity);

        Optional<Title> optEntity =
                dao.findByEmpNoAndTitleAndFromDate(
                        10_001, "Engineer Test", Date.valueOf("2022-01-01"));
        assertTrue(optEntity.isPresent());

        assertEquals(entity, optEntity.get());
    }

    @Test
    @Order(2)
    public void findAll() {
        List<Title> entities = dao.findAll();
        assertTrue(entities.size() > 0);
    }

    @Test
    @Order(3)
    public void findByEmpNo() {
        List<Title> entities = dao.findByEmpNo(10_001);
        assertEquals(10_001, entities.get(0).getEmpNo());
    }

    @Test
    @Order(4)
    public void findByTitle() {
        List<Title> entities = dao.findByTitle("Engineer Test");
        assertEquals("Engineer Test", entities.get(0).getTitle());
    }

    @Test
    @Order(5)
    public void findByToDate() {
        List<Title> entities = dao.findByToDate(Date.valueOf("9999-01-01"));
        assertEquals(Date.valueOf("9999-01-01"), entities.get(0).getToDate());
    }

    @Test
    @Order(6)
    public void findByFromDate() {
        List<Title> entities = dao.findByFromDate(Date.valueOf("2022-01-01"));
        assertEquals(Date.valueOf("2022-01-01"), entities.get(0).getFromDate());
    }

    @Test
    @Order(7)
    public void findByEmpNoAndTitleAndFromDate() {
        Optional<Title> optEntity = dao.findByEmpNoAndTitleAndFromDate(
                10_001, "Engineer Test", Date.valueOf("2022-01-01"));
        assertTrue(optEntity.isPresent());

        assertEquals(10_001, optEntity.get().getEmpNo());
        assertEquals("Engineer Test", optEntity.get().getTitle());
        assertEquals(Date.valueOf("9999-01-01"), optEntity.get().getToDate());
        assertEquals(Date.valueOf("2022-01-01"), optEntity.get().getFromDate());
    }

    @Test
    @Order(8)
    public void update() {
        Optional<Title> optEntity = dao.findByEmpNoAndTitleAndFromDate(
                10_001, "Engineer Test", Date.valueOf("2022-01-01"));
        assertTrue(optEntity.isPresent());

        Title entity = optEntity.get();
        entity.setToDate(Date.valueOf("2022-01-25"));

        dao.update(entity);

        Optional<Title> updatedOptEntity = dao.findByEmpNoAndTitleAndFromDate(
                10_001, "Engineer Test", Date.valueOf("2022-01-01"));
        assertTrue(updatedOptEntity.isPresent());

        assertEquals(entity, updatedOptEntity.get());
    }

    @Test
    @Order(9)
    public void delete() {
        Optional<Title> optEntity = dao.findByEmpNoAndTitleAndFromDate(
                10_001, "Engineer Test", Date.valueOf("2022-01-01"));
        assertTrue(optEntity.isPresent());

        int affectedRows = dao.delete(optEntity.get());
        assertEquals(1, affectedRows);

        assertFalse(dao.findByEmpNoAndTitleAndFromDate(
                10_001, "Engineer Test", Date.valueOf("2022-01-01"))
                .isPresent());
    }
}
