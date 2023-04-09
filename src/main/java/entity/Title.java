package entity;

import java.time.LocalDate;

public class Title {

    private int empNo;
    private String title;
    private LocalDate fromDate;
    private LocalDate toDate;

    public Title() {
    }

    public Title(int empNo, String title, LocalDate fromDate, LocalDate toDate) {
        this.empNo = empNo;
        this.title = title;
        this.fromDate = fromDate;
        this.toDate = toDate;
    }

    public int getEmpNo() {
        return empNo;
    }

    public String getTitle() {
        return title;
    }

    public LocalDate getFromDate() {
        return fromDate;
    }

    public LocalDate getToDate() {
        return toDate;
    }

    private Title(Builder builder) {
        this.empNo = builder.empNo;
        this.title = builder.title;
        this.fromDate = builder.fromDate;
        this.toDate = builder.toDate;
    }

    public static class Builder {
        private int empNo;
        private String title;
        private LocalDate fromDate;
        private LocalDate toDate;

        public Builder empNo(int empNo) {
            this.empNo = empNo;
            return this;
        }

        public Builder title(String title) {
            this.title = title;
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

        public Title build() {
            return new Title(this);
        }
    }
}