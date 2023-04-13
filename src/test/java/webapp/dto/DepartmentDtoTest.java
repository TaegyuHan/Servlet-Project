package webapp.dto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DepartmentDtoTest {

    @Test
    public void testGettersAndSetters() {

        DepartmentDto dto = new DepartmentDto.Builder()
                .deptNo("d001")
                .deptName("Development")
                .build();

        assertEquals("d001", dto.getDeptNo());
        assertEquals("Development", dto.getDeptName());

        assertThrows(IllegalArgumentException.class, () -> dto.setDeptNo(null));
        assertThrows(IllegalArgumentException.class, () -> dto.setDeptNo(""));
        assertThrows(IllegalArgumentException.class, () -> dto.setDeptNo("12345"));
        assertThrows(IllegalArgumentException.class, () -> dto.setDeptNo("d0000"));

        assertThrows(IllegalArgumentException.class, () -> dto.setDeptName(null));
        assertThrows(IllegalArgumentException.class, () -> dto.setDeptName(""));
        assertThrows(IllegalArgumentException.class, () -> dto.setDeptName("12345678901234567890123456789012345678901")); // 길이 41
        assertThrows(IllegalArgumentException.class, () -> dto.setDeptName("MarketingDepartmentMarketingDepartmentMarketingDepartmentMarketingDepartment")); // 길이 60
    }
}