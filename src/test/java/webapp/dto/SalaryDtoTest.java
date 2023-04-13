package webapp.dto;

import org.junit.jupiter.api.Test;

import java.sql.Date;

import static org.junit.jupiter.api.Assertions.*;

class SalaryDtoTest {

    @Test
    void testGettersAndSetters() {

        SalaryDto dto = new SalaryDto.Builder()
                .empNo(10001)
                .salary(5000)
                .fromDate(Date.valueOf("2020-01-01"))
                .toDate(Date.valueOf("2022-01-01"))
                .build();

        assertEquals(10001, dto.getEmpNo());
        assertEquals(5000, dto.getSalary());
        assertEquals(Date.valueOf("2020-01-01"), dto.getFromDate());
        assertEquals(Date.valueOf("2022-01-01"), dto.getToDate());

        assertThrows(IllegalArgumentException.class, () -> dto.setEmpNo(null));
        assertThrows(IllegalArgumentException.class, () -> dto.setEmpNo(""));
        assertThrows(IllegalArgumentException.class, () -> dto.setEmpNo("abc"));
        assertThrows(IllegalArgumentException.class, () -> dto.setEmpNo(0));

        assertThrows(IllegalArgumentException.class, () -> dto.setSalary(null));
        assertThrows(IllegalArgumentException.class, () -> dto.setSalary(""));
        assertThrows(IllegalArgumentException.class, () -> dto.setSalary("abc"));

        assertThrows(IllegalArgumentException.class, () -> dto.setFromDate((String) null));
        assertThrows(IllegalArgumentException.class, () -> dto.setFromDate(""));
        assertThrows(IllegalArgumentException.class, () -> dto.setFromDate("2020-22-31"));

        assertThrows(IllegalArgumentException.class, () -> dto.setToDate((String) null));
        assertThrows(IllegalArgumentException.class, () -> dto.setToDate(""));
        assertThrows(IllegalArgumentException.class, () -> dto.setToDate("2020-22-31"));
    }
}