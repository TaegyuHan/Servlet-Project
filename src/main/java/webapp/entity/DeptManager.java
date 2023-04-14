package webapp.entity;

import java.sql.Date;
import java.util.Objects;

public class DeptManager {

    private int empNo;
    private String deptNo;
    private Date fromDate;
    private Date toDate;

    public DeptManager() {}

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
        return "DeptManager{" +
                "empNo=" + empNo +
                ", deptNo='" + deptNo + '\'' +
                ", fromDate=" + fromDate +
                ", toDate=" + toDate +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DeptManager that = (DeptManager) o;
        return this.empNo == that.empNo &&
                Objects.equals(this.deptNo, that.deptNo) &&
                Objects.equals(this.fromDate, that.fromDate) &&
                Objects.equals(this.toDate, that.toDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.empNo, this.deptNo, this.fromDate, this.toDate);
    }


    public static class Builder {

        private DeptManager entity = new DeptManager();

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

        public DeptManager build() {
            return entity;
        }
    }
}