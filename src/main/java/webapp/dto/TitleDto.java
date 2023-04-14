package webapp.dto;

import java.sql.Date;
import java.util.Objects;


public class TitleDto {

    private int empNo;
    private String title;
    private Date fromDate;
    private Date toDate;

    public TitleDto() {
    }

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
        this.empNo = empNo;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {

        if (title == null || title.isEmpty()) {
            throw new IllegalArgumentException("\"title\" key not found in JSON object or is empty");
        }

        if (50 < title.length()) {
            throw new IllegalArgumentException("\"title\" value length exceeds the limit of 50 characters");
        }


        this.title = title;
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
        return "Title{" +
                "empNo=" + empNo +
                ", title='" + title + '\'' +
                ", fromDate=" + fromDate +
                ", toDate=" + toDate +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TitleDto that = (TitleDto) o;
        return empNo == that.empNo &&
                Objects.equals(title, that.title) &&
                Objects.equals(fromDate, that.fromDate) &&
                Objects.equals(toDate, that.toDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(empNo, title, fromDate, toDate);
    }

    public static class Builder {

        private TitleDto entity = new TitleDto();

        public Builder empNo(String empNo) {
            entity.setEmpNo(empNo);
            return this;
        }

        public Builder empNo(int empNo) {
            entity.setEmpNo(empNo);
            return this;
        }

        public Builder title(String title) {
            entity.setTitle(title);
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

        public TitleDto build() {
            return entity;
        }
    }
}