package webapp.entity;

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