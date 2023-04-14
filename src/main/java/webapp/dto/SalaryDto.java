package webapp.dto;

import java.sql.Date;
import java.util.Objects;


public class SalaryDto {

    private int empNo;
    private int salary;
    private Date fromDate;
    private Date toDate;

    public SalaryDto() {}

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

    public int getSalary() {
        return salary;
    }

    public void setSalary(String salaryStr) {

        if (salaryStr == null || salaryStr.isEmpty()) {
            throw new IllegalArgumentException("\"salary\" key not found in JSON object or is empty");
        }

        try {
            this.salary = Integer.parseInt(salaryStr);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("\"salary\" value is not valid");
        }

    }

    public void setSalary(int salary) {
        this.salary = salary;
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
        return "Salary{" +
                "empNo=" + empNo +
                ", salary=" + salary +
                ", fromDate=" + fromDate +
                ", toDate=" + toDate +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SalaryDto that = (SalaryDto) o;
        return empNo == that.empNo &&
                salary == that.salary &&
                Objects.equals(fromDate, that.fromDate) &&
                Objects.equals(toDate, that.toDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(empNo, salary, fromDate, toDate);
    }

    public static class Builder {

        private SalaryDto entity = new SalaryDto();

        public Builder empNo(String empNo) {
            entity.setEmpNo(empNo);
            return this;
        }

        public Builder empNo(int empNo) {
            entity.setEmpNo(empNo);
            return this;
        }

        public Builder salary(String salary) {
            entity.setSalary(salary);
            return this;
        }

        public Builder salary(int salary) {
            entity.setSalary(salary);
            return this;
        }

        public Builder fromDate(String fromDate) {
            entity.setFromDate(fromDate);
            return this;
        }

        public Builder fromDate(Date fromDate) {
            entity.setFromDate(fromDate);
            return this;
        }

        public Builder toDate(String toDate) {
            entity.setToDate(toDate);
            return this;
        }

        public Builder toDate(Date toDate) {
            entity.setToDate(toDate);
            return this;
        }

        public SalaryDto build() {
            return entity;
        }
    }
}