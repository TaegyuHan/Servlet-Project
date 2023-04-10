package webapp.dto;

import org.json.JSONException;
import org.json.JSONObject;
import webapp.entity.Salary;
import webapp.util.ReadFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


public class SalaryDto {

    private int empNo;
    private int salary;
    private Date fromDate;
    private Date toDate;

    public SalaryDto() {}

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

    public static Optional<SalaryDto> reqeustToDto(HttpServletRequest request) throws IOException {

        String jsonData = ReadFile.read(request);

        JSONObject json = new JSONObject(jsonData);

        try {
            return Optional.of(
                    new SalaryDto.Builder()
                            .empNo(json.getInt("emp_no"))
                            .salary(json.getInt("salary"))
                            .fromDate(Date.valueOf(json.getString("from_date")))
                            .toDate(Date.valueOf(json.getString("to_date")))
                            .build()
            );

        } catch (JSONException e) {
            return Optional.empty();
        }
    }

    public Salary toEntity() {
        return new Salary.Builder()
                .empNo(this.empNo)
                .salary(this.salary)
                .fromDate(this.fromDate)
                .toDate(this.toDate)
                .build();
    }

    public static List<SalaryDto> entitiesToDtos(List<Salary> entities) {

        List<SalaryDto> dtos = new ArrayList<>();

        for (Salary entity : entities) {
            dtos.add(SalaryDto.entityToDto(entity));
        }

        return dtos;
    }

    public static SalaryDto entityToDto(Salary entity) {
        return new SalaryDto.Builder()
                .empNo(entity.getEmpNo())
                .salary(entity.getSalary())
                .fromDate(entity.getFromDate())
                .toDate(entity.getToDate())
                .build();
    }

    public static Optional<SalaryDto> entityToDto(Optional<Salary> optEntity) {
        return Optional.ofNullable(
                entityToDto(optEntity.get())
        );
    }

    public static class Builder {

        private SalaryDto entity = new SalaryDto();

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

        public SalaryDto build() {
            return entity;
        }
    }
}