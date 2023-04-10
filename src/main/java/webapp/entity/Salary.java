package webapp.entity;

import java.sql.Date;

public class Salary {

    private int empNo;
    private int salary;
    private Date fromDate;
    private Date toDate;

    public Salary() {}

    public int getEmpNo() {
        return empNo;
    }

    public void setEmpNo(int empNo) {
        this.empNo = empNo;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
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
        return "Salary{" +
                "empNo=" + empNo +
                ", salary=" + salary +
                ", fromDate=" + fromDate +
                ", toDate=" + toDate +
                '}';
    }

    public static class Builder {

        private Salary entity = new Salary();

        public Builder empNo(int empNo) {
            entity.setEmpNo(empNo);
            return this;
        }

        public Builder salary(int salary) {
            entity.setSalary(salary);
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

        public Salary build() {
            return entity;
        }
    }
}