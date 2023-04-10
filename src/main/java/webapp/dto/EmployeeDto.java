package webapp.dto;

import org.json.JSONException;
import org.json.JSONObject;
import webapp.entity.Employee;
import webapp.entity.Gender;
import webapp.util.ReadFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


public class EmployeeDto {

    private int empNo;
    private Date birthDate;
    private String firstName;
    private String lastName;
    private Gender gender;
    private Date hireDate;

    public EmployeeDto() {};

    public int getEmpNo() {
        return empNo;
    }

    public void setEmpNo(int empNo) {
        this.empNo = empNo;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public Date getHireDate() {
        return hireDate;
    }

    public void setHireDate(Date hireDate) {
        this.hireDate = hireDate;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "empNo=" + empNo +
                ", birthDate=" + birthDate +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", gender=" + gender +
                ", hireDate=" + hireDate +
                '}';
    }

    public static Optional<EmployeeDto> reqeustToDto(HttpServletRequest request) throws IOException {

        String jsonData = ReadFile.read(request);

        JSONObject json = new JSONObject(jsonData);

        try {
            return Optional.of(
                    new Builder()
                            .birthDate(Date.valueOf(json.getString("birth_date")))
                            .firstName(json.getString("first_name"))
                            .lastName(json.getString("last_name"))
                            .gender(Gender.valueOf(json.getString("gender")))
                            .hireDate(Date.valueOf(json.getString("hire_date")))
                            .build()
            );

        } catch (JSONException e) {
            return Optional.empty();
        }
    }

    public Employee toEntity() {
        return new Employee.Builder()
                .empNo(this.empNo)
                .birthDate(this.birthDate)
                .firstName(this.firstName)
                .lastName(this.lastName)
                .gender(this.gender)
                .hireDate(this.hireDate)
                .build();
    }

    public static List<EmployeeDto> entitiesToDtos(List<Employee> entities) {

        List<EmployeeDto> dtos = new ArrayList<>();

        for (Employee entity : entities) {
            dtos.add(EmployeeDto.entityToDto(entity));
        }

        return dtos;
    }

    public static EmployeeDto entityToDto(Employee entity) {
        return new EmployeeDto.Builder()
                .empNo(entity.getEmpNo())
                .birthDate(entity.getBirthDate())
                .firstName(entity.getFirstName())
                .lastName(entity.getLastName())
                .gender(entity.getGender())
                .hireDate(entity.getHireDate())
                .build();
    }

    public static Optional<EmployeeDto> entityToDto(Optional<Employee> optEntity) {
        return Optional.ofNullable(
                entityToDto(optEntity.get())
        );
    }

    public static class Builder {

        private EmployeeDto entity = new EmployeeDto();

        public Builder empNo(int empNo) {
            entity.setEmpNo(empNo);
            return this;
        }

        public Builder birthDate(Date birthDate) {
            entity.setBirthDate(birthDate);
            return this;
        }

        public Builder firstName(String firstName) {
            entity.setFirstName(firstName);
            return this;
        }

        public Builder lastName(String lastName) {
            entity.setLastName(lastName);
            return this;
        }

        public Builder gender(Gender gender) {
            entity.setGender(gender);
            return this;
        }

        public Builder hireDate(Date hireDate) {
            entity.setHireDate(hireDate);
            return this;
        }

        public EmployeeDto build() {
            return entity;
        }
    }
}