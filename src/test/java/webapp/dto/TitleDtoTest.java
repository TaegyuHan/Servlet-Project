package webapp.dto;

import org.junit.jupiter.api.Test;

import java.sql.Date;

import static org.junit.jupiter.api.Assertions.*;


class TitleDtoTest {

    @Test
    public void testGettersAndSetters() {

        TitleDto dto = new TitleDto.Builder()
                .empNo(10001)
                .title("Engineer")
                .fromDate(Date.valueOf("2020-01-01"))
                .toDate(Date.valueOf("2022-01-01"))
                .build();

        assertEquals(10001, dto.getEmpNo());
        assertEquals("Engineer", dto.getTitle());
        assertEquals(Date.valueOf("2020-01-01"), dto.getFromDate());
        assertEquals(Date.valueOf("2022-01-01"), dto.getToDate());

        assertThrows(IllegalArgumentException.class, () -> dto.setEmpNo(null));
        assertThrows(IllegalArgumentException.class, () -> dto.setEmpNo(""));
        assertThrows(IllegalArgumentException.class, () -> dto.setEmpNo("abc"));

        assertThrows(IllegalArgumentException.class, () -> dto.setTitle(null));
        assertThrows(IllegalArgumentException.class, () -> dto.setTitle(""));

        assertThrows(IllegalArgumentException.class, () -> dto.setFromDate((String) null));
        assertThrows(IllegalArgumentException.class, () -> dto.setFromDate(""));
        assertThrows(IllegalArgumentException.class, () -> dto.setFromDate("2020-22-31"));

        assertThrows(IllegalArgumentException.class, () -> dto.setToDate((String) null));
        assertThrows(IllegalArgumentException.class, () -> dto.setToDate(""));
        assertThrows(IllegalArgumentException.class, () -> dto.setToDate("2020-22-31"));
    }
}