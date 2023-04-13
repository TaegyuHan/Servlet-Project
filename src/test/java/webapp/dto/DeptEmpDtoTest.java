package webapp.dto;

import org.junit.jupiter.api.Test;

import java.sql.Date;

import static org.junit.jupiter.api.Assertions.*;

class DeptEmpDtoTest {

    @Test
    public void testGettersAndSetters() {
        DeptEmpDto dto = new DeptEmpDto.Builder()
                .empNo("10001")
                .deptNo("d001")
                .fromDate("2000-01-01")
                .toDate("2000-12-31")
                .build();

        assertEquals(10001, dto.getEmpNo());
        assertEquals("d001", dto.getDeptNo());
        assertEquals(Date.valueOf("2000-01-01"), dto.getFromDate());
        assertEquals(Date.valueOf("2000-12-31"), dto.getToDate());

        assertThrows(IllegalArgumentException.class, () -> dto.setEmpNo(""));
        assertThrows(IllegalArgumentException.class, () -> dto.setEmpNo("abc"));
        assertThrows(IllegalArgumentException.class, () -> dto.setEmpNo(0));

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