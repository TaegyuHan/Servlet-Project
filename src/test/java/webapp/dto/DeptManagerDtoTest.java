package webapp.dto;

import org.junit.jupiter.api.Test;

import java.sql.Date;

import static org.junit.jupiter.api.Assertions.*;

class DeptManagerDtoTest {

    @Test
    public void testGettersAndSetters() {

        DeptManagerDto dto = new DeptManagerDto.Builder()
                .empNo(10001)
                .deptNo("d001")
                .fromDate(Date.valueOf("1990-01-01"))
                .toDate(Date.valueOf("1991-01-01"))
                .build();

        assertEquals(10001, dto.getEmpNo());
        assertEquals("d001", dto.getDeptNo());
        assertEquals(Date.valueOf("1990-01-01"), dto.getFromDate());
        assertEquals(Date.valueOf("1991-01-01"), dto.getToDate());

        assertThrows(IllegalArgumentException.class, () -> dto.setEmpNo(null));
        assertThrows(IllegalArgumentException.class, () -> dto.setEmpNo(""));
        assertThrows(IllegalArgumentException.class, () -> dto.setEmpNo("abc"));

        assertThrows(IllegalArgumentException.class, () -> dto.setDeptNo(null));
        assertThrows(IllegalArgumentException.class, () -> dto.setDeptNo(""));
        assertThrows(IllegalArgumentException.class, () -> dto.setDeptNo("12345"));
        assertThrows(IllegalArgumentException.class, () -> dto.setDeptNo("d0000"));

        assertThrows(IllegalArgumentException.class, () -> dto.setFromDate((String) null));
        assertThrows(IllegalArgumentException.class, () -> dto.setFromDate(""));
        assertThrows(IllegalArgumentException.class, () -> dto.setFromDate("2020-13-01"));
        assertThrows(IllegalArgumentException.class, () -> dto.setFromDate("2020-12-32"));
        assertThrows(IllegalArgumentException.class, () -> dto.setFromDate("2020/01/01"));
        assertThrows(IllegalArgumentException.class, () -> dto.setFromDate(Date.valueOf("2020-13-01")));
        assertThrows(IllegalArgumentException.class, () -> dto.setFromDate(Date.valueOf("2020-12-32")));

        assertThrows(IllegalArgumentException.class, () -> dto.setToDate((String) null));
        assertThrows(IllegalArgumentException.class, () -> dto.setToDate(""));
        assertThrows(IllegalArgumentException.class, () -> dto.setToDate("2020-13-01"));
        assertThrows(IllegalArgumentException.class, () -> dto.setToDate("2020-12-32"));
        assertThrows(IllegalArgumentException.class, () -> dto.setToDate("2020/01/01"));
        assertThrows(IllegalArgumentException.class, () -> dto.setToDate(Date.valueOf("2020-13-01")));
        assertThrows(IllegalArgumentException.class, () -> dto.setToDate(Date.valueOf("2020-12-32")));
    }
}