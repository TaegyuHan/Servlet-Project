package webapp.entity;

import java.sql.Date;

public class Salary {

    private int empNo;
    private int salary;
    private Date fromDate;
    private Date toDate;

    public Salary(int empNo, int salary, Date fromDate, Date toDate) {
        this.empNo = empNo;
        this.salary = salary;
        this.fromDate = fromDate;
        this.toDate = toDate;
    }

    public int getEmpNo() {
        return empNo;
    }

    public int getSalary() {
        return salary;
    }

    public Date getFromDate() {
        return fromDate;
    }

    public Date getToDate() {
        return toDate;
    }

    public Salary(Builder builder) {
        this.empNo = builder.empNo;
        this.salary = builder.salary;
        this.fromDate = builder.fromDate;
        this.toDate = builder.toDate;
    }

    public static class Builder {
        private int empNo;
        private int salary;
        private Date fromDate;
        private Date toDate;

        public Builder empNo(int empNo) {
            this.empNo = empNo;
            return this;
        }

        public Builder salary(int salary) {
            this.salary = salary;
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

        public Salary build() {
            return new Salary(this);
        }
    }
}