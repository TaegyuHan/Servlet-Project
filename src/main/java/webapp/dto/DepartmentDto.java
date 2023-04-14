package webapp.dto;


import java.util.Objects;


public class DepartmentDto {

    private String deptNo;
    private String deptName;

    public DepartmentDto() {
    }

    public String getDeptNo() {
        return deptNo;
    }

    public void setDeptNo(String deptNo) {

        if (deptNo == null || deptNo.isEmpty()) {
            throw new IllegalArgumentException("\"dept_no\" key not found in JSON object or is empty");
        }

        if (deptNo.length() != 4) {
            throw new IllegalArgumentException("\"dept_no\" value length is invalid. Please set the data length to 4");
        }

        this.deptNo = deptNo;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {

        if (deptName == null || deptName.isEmpty()) {
            throw new IllegalArgumentException("\"dept_name\" key not found in JSON object or is empty");
        }

        if (40 < deptName.length()) {
            throw new IllegalArgumentException("\"dept_no\" please set the data length to deptName");
        }

        this.deptName = deptName;
    }

    @Override
    public String toString() {
        return "Department{" +
                "deptNo='" + deptNo + '\'' +
                ", deptName='" + deptName + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DepartmentDto that = (DepartmentDto) o;
        return Objects.equals(deptNo, that.deptNo) &&
                Objects.equals(deptName, that.deptName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(deptNo, deptName);
    }

    public static class Builder {

        private DepartmentDto entity = new DepartmentDto();

        public Builder deptNo(String deptNo) {
            entity.setDeptNo(deptNo);
            return this;
        }

        public Builder deptName(String deptName) {
            entity.setDeptName(deptName);
            return this;
        }

        public DepartmentDto build() {
            return entity;
        }
    }
}