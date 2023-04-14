package webapp.entity;

import java.util.Objects;


public class Department {

    private String deptNo;
    private String deptName;

    public Department() {
    }

    public String getDeptNo() {
        return deptNo;
    }

    public void setDeptNo(String deptNo) {
        this.deptNo = deptNo;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
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
        Department that = (Department) o;
        return Objects.equals(this.deptNo, that.deptNo) &&
                Objects.equals(this.deptName, that.deptName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.deptNo, this.deptName);
    }

    public static class Builder {

        private Department entity = new Department();

        public Builder deptNo(String deptNo) {
            entity.setDeptNo(deptNo);
            return this;
        }

        public Builder deptName(String deptName) {
            entity.setDeptName(deptName);
            return this;
        }

        public Department build() {
            return entity;
        }
    }
}