package entity;

public class Department {

    private String deptNo;
    private String deptName;

    public Department() {}

    public Department(String deptNo, String deptName) {
        this.deptNo = deptNo;
        this.deptName = deptName;
    }

    public String getDeptNo() {
        return deptNo;
    }

    public String getDeptName() {
        return deptName;
    }

    private Department(Builder builder) {
        this.deptNo = builder.deptNo;
        this.deptName = builder.deptName;
    }

    public static class Builder {
        private String deptNo;
        private String deptName;

        public Builder deptNo(String deptNo) {
            this.deptNo = deptNo;
            return this;
        }

        public Builder deptName(String deptName) {
            this.deptName = deptName;
            return this;
        }

        public Department build() {
            return new Department(this);
        }
    }
}