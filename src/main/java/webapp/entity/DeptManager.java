package webapp.entity;

import java.time.LocalDate;

public class DeptManager {

    private int empNo;
    private String deptNo;
    private LocalDate fromDate;
    private LocalDate toDate;

    public DeptManager() {}

    public DeptManager(int empNo, String deptNo, LocalDate fromDate, LocalDate toDate) {
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

    public LocalDate getFromDate() {
        return fromDate;
    }

    public LocalDate getToDate() {
        return toDate;
    }

    private DeptManager(Builder builder) {
        this.empNo = builder.empNo;
        this.deptNo = builder.deptNo;
        this.fromDate = builder.fromDate;
        this.toDate = builder.toDate;
    }

    public static class Builder {
        private int empNo;
        private String deptNo;
        private LocalDate fromDate;
        private LocalDate toDate;

        public Builder empNo(int empNo) {
            this.empNo = empNo;
            return this;
        }

        public Builder deptNo(String deptNo) {
            this.deptNo = deptNo;
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

        public DeptManager build() {
            return new DeptManager(this);
        }
    }
}