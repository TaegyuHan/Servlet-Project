package webapp.entity;

import java.time.LocalDate;

public class Salary {

    private int empNo;
    private int salary;
    private LocalDate fromDate;
    private LocalDate toDate;

    public Salary(int empNo, int salary, LocalDate fromDate, LocalDate toDate) {
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

    public LocalDate getFromDate() {
        return fromDate;
    }

    public LocalDate getToDate() {
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
        private LocalDate fromDate;
        private LocalDate toDate;

        public Builder empNo(int empNo) {
            this.empNo = empNo;
            return this;
        }

        public Builder salary(int salary) {
            this.salary = salary;
            return this;
        }

        public Builder fromDate(LocalDate fromDate) {
            this.fromDate = fromDate;
            return this;
        }

        public Builder toDate(LocalDate toDate) {
            this.toDate = toDate;
            return this;
        }

        public Salary build() {
            return new Salary(this);
        }
    }
}