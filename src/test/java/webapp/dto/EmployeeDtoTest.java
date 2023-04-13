package webapp.dto;

import org.junit.jupiter.api.Test;
import webapp.entity.Gender;

import java.sql.Date;

import static org.junit.jupiter.api.Assertions.*;

class EmployeeDtoTest {

    @Test
    public void testGettersAndSetters() {

        EmployeeDto dto = new EmployeeDto.Builder()
                .empNo(10001)
                .birthDate(Date.valueOf("1953-09-02"))
                .firstName("Georgi")
                .lastName("Facello")
                .gender(Gender.M)
                .hireDate(Date.valueOf("1986-06-26"))
                .build();

        assertEquals(10001, dto.getEmpNo());
        assertEquals(Date.valueOf("1953-09-02"), dto.getBirthDate());
        assertEquals("Georgi", dto.getFirstName());
        assertEquals("Facello", dto.getLastName());
        assertEquals(Gender.M, dto.getGender());
        assertEquals(Date.valueOf("1986-06-26"), dto.getHireDate());

        assertThrows(IllegalArgumentException.class, () -> dto.setEmpNo(null));
        assertThrows(IllegalArgumentException.class, () -> dto.setEmpNo(""));
        assertThrows(IllegalArgumentException.class, () -> dto.setEmpNo("abc"));

        assertThrows(IllegalArgumentException.class, () -> dto.setFirstName(null));
        assertThrows(IllegalArgumentException.class, () -> dto.setFirstName(""));
        assertThrows(IllegalArgumentException.class, () -> dto.setFirstName("ThisNameIsTooLong"));

        assertThrows(IllegalArgumentException.class, () -> dto.setLastName(null));
        assertThrows(IllegalArgumentException.class, () -> dto.setLastName(""));
        assertThrows(IllegalArgumentException.class, () -> dto.setLastName("ThisLastNameIsTooLong"));

        assertThrows(IllegalArgumentException.class, () -> dto.setGender((String) null));
        assertThrows(IllegalArgumentException.class, () -> dto.setGender(""));
        assertThrows(IllegalArgumentException.class, () -> dto.setGender("G"));

        assertThrows(IllegalArgumentException.class, () -> dto.setBirthDate((String) null));
        assertThrows(IllegalArgumentException.class, () -> dto.setBirthDate(""));
        assertThrows(IllegalArgumentException.class, () -> dto.setBirthDate("2020-22-31"));

        assertThrows(IllegalArgumentException.class, () -> dto.setHireDate((String) null));
        assertThrows(IllegalArgumentException.class, () -> dto.setHireDate(""));
        assertThrows(IllegalArgumentException.class, () -> dto.setHireDate("2020-22-31"));
    }
}