package webapp.dto;

import java.sql.Date;
import java.util.Objects;


public class DeptManagerDto {

    private int empNo;
    private String deptNo;
    private Date fromDate;
    private Date toDate;

    public DeptManagerDto() {}

    public int getEmpNo() {
        return empNo;
    }

    public void setEmpNo(String empNoStr) {
        if (empNoStr == null || empNoStr.isEmpty()) {
            throw new IllegalArgumentException("\"emp_no\" key not found in JSON object or is empty");
        }

        try {
            this.empNo = Integer.parseInt(empNoStr);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("\"emp_no\" value is not valid");
        }
    }

    public void setEmpNo(int empNo) {

        if (empNo == 0) {
            throw new IllegalArgumentException("\"emp_no\" key not found in JSON object or is empty");
        }

        this.empNo = empNo;
    }

    public String getDeptNo() {
        return deptNo;
    }

    public void setDeptNo(String deptNo) {

        if (deptNo == null || deptNo.isEmpty()) {
            throw new IllegalArgumentException("\"dept_no\" key not found in JSON object or is empty");
        }

        if (deptNo.length() != 4) {
            throw new IllegalArgumentException("\"dept_no\" please set the data length to 4");
        }

        this.deptNo = deptNo;
    }

    public Date getFromDate() {
        return fromDate;
    }

    public void setFromDate(String fromDateStr) {

        if (fromDateStr == null || fromDateStr.isEmpty()) {
            throw new IllegalArgumentException("\"from_date\" key not found in JSON object or is empty");
        }

        try {
            this.fromDate = Date.valueOf(fromDateStr);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("\"from_date\" value cannot be converted to Date");
        }
    }

    public void setFromDate(Date fromDate) {
        this.fromDate = fromDate;
    }

    public Date getToDate() {
        return toDate;
    }

    public void setToDate(String toDateStr) {

        if (toDateStr == null || toDateStr.isEmpty()) {
            throw new IllegalArgumentException("\"to_date\" key not found in JSON object or is empty");
        }

        try {
            this.toDate = Date.valueOf(toDateStr);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("\"to_date\" value cannot be converted to Date");
        }
    }

    public void setToDate(Date toDate) {
        this.toDate = toDate;
    }

    @Override
    public String toString() {
        return "DeptManagerDto{" +
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
        DeptManagerDto that = (DeptManagerDto) o;
        return empNo == that.empNo &&
                deptNo.equals(that.deptNo) &&
                fromDate.equals(that.fromDate) &&
                toDate.equals(that.toDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(empNo, deptNo, fromDate, toDate);
    }


    public static class Builder {

        private DeptManagerDto entity = new DeptManagerDto();

        public DeptManagerDto.Builder empNo(int empNo) {
            entity.setEmpNo(empNo);
            return this;
        }

        public DeptManagerDto.Builder deptNo(String deptNo) {
            entity.setDeptNo(deptNo);
            return this;
        }

        public DeptManagerDto.Builder fromDate(Date fromDate) {
            entity.setFromDate(fromDate);
            return this;
        }

        public DeptManagerDto.Builder toDate(Date toDate) {
            entity.setToDate(toDate);
            return this;
        }

        public DeptManagerDto build() {
            return entity;
        }
    }
}