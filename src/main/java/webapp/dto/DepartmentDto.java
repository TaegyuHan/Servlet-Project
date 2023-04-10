package webapp.dto;

import org.json.JSONException;
import org.json.JSONObject;
import webapp.entity.Department;
import webapp.util.ReadFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DepartmentDto {

    private String deptNo;
    private String deptName;

    public DepartmentDto() {
    }

    public String getDeptNo() {
        return deptNo;
    }

    public void setDeptNo(String deptNo) {
        this.deptNo = deptNo;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    @Override
    public String toString() {
        return "Department{" +
                "deptNo='" + deptNo + '\'' +
                ", deptName='" + deptName + '\'' +
                '}';
    }

    public static Optional<DepartmentDto> reqeustToDto(HttpServletRequest request) throws IOException {

        String jsonData = ReadFile.read(request);

        JSONObject json = new JSONObject(jsonData);

        try {
            return Optional.of(
                    new Builder()
                            .deptNo(json.getString("dept_no"))
                            .deptName(json.getString("dept_name"))
                            .build()
            );

        } catch (JSONException e) {
            return Optional.empty();
        }
    }

    public Department toEntity() {
        return new Department.Builder()
                .deptNo(this.deptNo)
                .deptName(this.deptName)
                .build();
    }

    public static List<DepartmentDto> entitiesToDtos(List<Department> entities) {

        List<DepartmentDto> dtos = new ArrayList<>();

        for (Department entity : entities) {
            dtos.add(DepartmentDto.entityToDto(entity));
        }

        return dtos;
    }

    public static DepartmentDto entityToDto(Department entity) {
        return new DepartmentDto.Builder()
                .deptNo(entity.getDeptNo())
                .deptName(entity.getDeptName())
                .build();
    }

    public static Optional<DepartmentDto> entityToDto(Optional<Department> optEntity) {
        return Optional.ofNullable(
                entityToDto(optEntity.get())
        );
    }

    public static class Builder {

        private DepartmentDto entity = new DepartmentDto();

        public Builder deptNo(String deptNo) {
            entity.setDeptNo(deptNo);
            return this;
        }

        public Builder deptName(String deptName) {
            entity.setDeptName(deptName);
            return this;
        }

        public DepartmentDto build() {
            return entity;
        }
    }
}