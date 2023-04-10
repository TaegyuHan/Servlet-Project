package webapp.dto;

import org.json.JSONException;
import org.json.JSONObject;
import webapp.entity.DeptEmp;
import webapp.util.ReadFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


public class DeptEmpDto {

    private int empNo;
    private String deptNo;
    private Date fromDate;
    private Date toDate;

    public DeptEmpDto() {
    }

    public int getEmpNo() {
        return empNo;
    }

    public void setEmpNo(int empNo) {
        this.empNo = empNo;
    }

    public String getDeptNo() {
        return deptNo;
    }

    public void setDeptNo(String deptNo) {
        this.deptNo = deptNo;
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
        return "DeptEmp{" +
                "empNo=" + empNo +
                ", deptNo='" + deptNo + '\'' +
                ", fromDate=" + fromDate +
                ", toDate=" + toDate +
                '}';
    }

    public static Optional<DeptEmpDto> reqeustToDto(HttpServletRequest request) throws IOException {

        String jsonData = ReadFile.read(request);

        JSONObject json = new JSONObject(jsonData);

        try {
            return Optional.of(
                    new DeptEmpDto.Builder()
                            .empNo(json.getInt("emp_no"))
                            .deptNo(json.getString("dept_no"))
                            .fromDate(Date.valueOf(json.getString("from_date")))
                            .toDate(Date.valueOf(json.getString("to_date")))
                            .build()
            );

        } catch (JSONException e) {
            return Optional.empty();
        }
    }

    public DeptEmp toEntity() {
        return new DeptEmp.Builder()
                .empNo(this.empNo)
                .deptNo(this.deptNo)
                .fromDate(this.fromDate)
                .toDate(this.toDate)
                .build();
    }

    public static List<DeptEmpDto> entitiesToDtos(List<DeptEmp> entities) {

        List<DeptEmpDto> dtos = new ArrayList<>();

        for (DeptEmp entity : entities) {
            dtos.add(DeptEmpDto.entityToDto(entity));
        }

        return dtos;
    }

    public static DeptEmpDto entityToDto(DeptEmp entity) {
        return new DeptEmpDto.Builder()
                .empNo(entity.getEmpNo())
                .empNo(entity.getEmpNo())
                .deptNo(entity.getDeptNo())
                .fromDate(entity.getFromDate())
                .toDate(entity.getToDate())
                .build();
    }

    public static Optional<DeptEmpDto> entityToDto(Optional<DeptEmp> optEntity) {
        return Optional.ofNullable(
                entityToDto(optEntity.get())
        );
    }
    
    public static class Builder {

        private DeptEmpDto entity = new DeptEmpDto();

        public Builder empNo(int empNo) {
            entity.setEmpNo(empNo);
            return this;
        }

        public Builder deptNo(String deptNo) {
            entity.setDeptNo(deptNo);
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

        public DeptEmpDto build() {
            return entity;
        }
    }
}