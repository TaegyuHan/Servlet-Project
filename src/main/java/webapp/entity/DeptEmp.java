package webapp.entity;

import java.sql.Date;

public class DeptEmp {

    private int empNo;
    private String deptNo;
    private Date fromDate;
    private Date toDate;

    public DeptEmp() {
    }

    public int getEmpNo() {
        return empNo;
    }

    public void setEmpNo(int empNo) {
        this.empNo = empNo;
    }

    public String getDeptNo() {
        return deptNo;
    }

    public void setDeptNo(String deptNo) {
        this.deptNo = deptNo;
    }

    public Date getFromDate() {
        return fromDate;
    }

    public void setFromDate(Date fromDate) {
        this.fromDate = fromDate;
    }

    public Date getToDate() {
        return toDate;
    }

    public void setToDate(Date toDate) {
        this.toDate = toDate;
    }

    @Override
    public String toString() {
        return "DeptEmp{" +
                "empNo=" + empNo +
                ", deptNo='" + deptNo + '\'' +
                ", fromDate=" + fromDate +
                ", toDate=" + toDate +
                '}';
    }

    public static class Builder {

        private DeptEmp entity = new DeptEmp();

        public Builder empNo(int empNo) {
            entity.setEmpNo(empNo);
            return this;
        }

        public Builder deptNo(String deptNo) {
            entity.setDeptNo(deptNo);
            return this;
        }

        public Builder fromDate(Date fromDate) {
            entity.setFromDate(fromDate);
            return this;
        }

        public Builder toDate(Date toDate) {
            entity.setToDate(toDate);
            return this;
        }

        public DeptEmp build() {
            return entity;
        }
    }
}