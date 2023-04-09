package webapp.entity;

import java.sql.Date;

public class DeptEmp {

    private int empNo;
    private String deptNo;
    private Date fromDate;
    private Date toDate;

    public DeptEmp() {}

    public DeptEmp(int empNo, String deptNo, Date fromDate, Date toDate) {
        this.empNo = empNo;
        this.deptNo = deptNo;
        this.fromDate = fromDate;
        this.toDate = toDate;
    }

    public int getEmpNo() {
        return empNo;
    }

    public String getDeptNo() {
        return deptNo;
    }

    public Date getFromDate() {
        return fromDate;
    }

    public Date getToDate() {
        return toDate;
    }

    private DeptEmp(Builder builder) {
        empNo = builder.empNo;
        deptNo = builder.deptNo;
        fromDate = builder.fromDate;
        toDate = builder.toDate;
    }

    public static class Builder {
        private int empNo;
        private String deptNo;
        private Date fromDate;
        private Date toDate;

        public Builder empNo(int empNo) {
            this.empNo = empNo;
            return this;
        }

        public Builder deptNo(String deptNo) {
            this.deptNo = deptNo;
            return this;
        }

        public Builder fromDate(Date fromDate) {
            this.fromDate = fromDate;
            return this;
        }

        public Builder toDate(Date toDate) {
            this.toDate = toDate;
            return this;
        }

        public DeptEmp build() {
            return new DeptEmp(this);
        }
    }
}