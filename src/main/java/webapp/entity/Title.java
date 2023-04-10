package webapp.entity;

import java.sql.Date;

public class Title {

    private int empNo;
    private String title;
    private Date fromDate;
    private Date toDate;

    public Title() {}

    public int getEmpNo() {
        return empNo;
    }

    public void setEmpNo(int empNo) {
        this.empNo = empNo;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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
        return "Title{" +
                "empNo=" + empNo +
                ", title='" + title + '\'' +
                ", fromDate=" + fromDate +
                ", toDate=" + toDate +
                '}';
    }

    public static class Builder {

        private Title entity = new Title();

        public Builder empNo(int empNo) {
            entity.setEmpNo(empNo);
            return this;
        }

        public Builder title(String title) {
            entity.setTitle(title);
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

        public Title build() {
            return entity;
        }
    }
}