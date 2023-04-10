package webapp.dto;

import org.json.JSONException;
import org.json.JSONObject;
import webapp.entity.DeptManager;
import webapp.util.ReadFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


public class DeptManagerDto {

    private int empNo;
    private String deptNo;
    private Date fromDate;
    private Date toDate;

    public DeptManagerDto() {}

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
        return "DeptManagerDto{" +
                "empNo=" + empNo +
                ", deptNo='" + deptNo + '\'' +
                ", fromDate=" + fromDate +
                ", toDate=" + toDate +
                '}';
    }

    public static Optional<DeptManagerDto> reqeustToDto(HttpServletRequest request) throws IOException {

        String jsonData = ReadFile.read(request);

        JSONObject json = new JSONObject(jsonData);

        try {
            return Optional.of(
                    new DeptManagerDto.Builder()
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

    public DeptManager toEntity() {
        return new DeptManager.Builder()
                .empNo(this.empNo)
                .deptNo(this.deptNo)
                .fromDate(this.fromDate)
                .toDate(this.toDate)
                .build();
    }

    public static List<DeptManagerDto> entitiesToDtos(List<DeptManager> entities) {

        List<DeptManagerDto> dtos = new ArrayList<>();

        for (DeptManager entity : entities) {
            dtos.add(DeptManagerDto.entityToDto(entity));
        }

        return dtos;
    }

    public static DeptManagerDto entityToDto(DeptManager entity) {
        return new DeptManagerDto.Builder()
                .empNo(entity.getEmpNo())
                .empNo(entity.getEmpNo())
                .deptNo(entity.getDeptNo())
                .fromDate(entity.getFromDate())
                .toDate(entity.getToDate())
                .build();
    }

    public static Optional<DeptManagerDto> entityToDto(Optional<DeptManager> optEntity) {
        return Optional.ofNullable(
                entityToDto(optEntity.get())
        );
    }
    
    public static class Builder {

        private DeptManagerDto entity = new DeptManagerDto();

        public DeptManagerDto.Builder empNo(int empNo) {
            entity.setEmpNo(empNo);
            return this;
        }

        public DeptManagerDto.Builder deptNo(String deptNo) {
            entity.setDeptNo(deptNo);
            return this;
        }

        public DeptManagerDto.Builder fromDate(Date fromDate) {
            entity.setFromDate(fromDate);
            return this;
        }

        public DeptManagerDto.Builder toDate(Date toDate) {
            entity.setToDate(toDate);
            return this;
        }

        public DeptManagerDto build() {
            return entity;
        }
    }
}