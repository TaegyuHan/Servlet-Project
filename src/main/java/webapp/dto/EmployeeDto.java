package webapp.dto;

import webapp.entity.Gender;

import java.sql.Date;


public class EmployeeDto {

    private int empNo;
    private Date birthDate;
    private String firstName;
    private String lastName;
    private Gender gender;
    private Date hireDate;

    public EmployeeDto() {}

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

        if (empNo == 0) {
            throw new IllegalArgumentException("\"emp_no\" key not found in JSON object or is empty");
        }

        this.empNo = empNo;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDateStr) {

        if (birthDateStr == null || birthDateStr.isEmpty()) {
            throw new IllegalArgumentException("\"birth_date\" key not found in JSON object or is empty");
        }

        try {
            this.birthDate = Date.valueOf(birthDateStr);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("\"birth_date\" value cannot be converted to Date");
        }
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {

        if (firstName == null || firstName.isEmpty()) {
            throw new IllegalArgumentException("\"first_name\" value not found in JSON object or is empty");
        }

        if (14 < firstName.length()) {
            throw new IllegalArgumentException("\"first_name\" value length is invalid. Please set the data length to 14 or less");
        }

        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {

        if (lastName == null || lastName.isEmpty()) {
            throw new IllegalArgumentException("\"last_name\" key not found in JSON object or is empty");
        }

        if (16 < lastName.length()) {
            throw new IllegalArgumentException("\"last_name\" value length is invalid. Please set the data length to 16 or less");
        }


        this.lastName = lastName;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(String genderStr) {

        if (genderStr == null || genderStr.isEmpty()) {
            throw new IllegalArgumentException("\"gender\" key not found in JSON object or is empty");
        }

        try {
            this.gender = Gender.valueOf(genderStr);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("\"gender\" value is not valid. Please enter M or F.");
        }
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public Date getHireDate() {
        return hireDate;
    }

    public void setHireDate(String hireDateStr) {

        if (hireDateStr == null || hireDateStr.isEmpty()) {
            throw new IllegalArgumentException("\"hire_date\" key not found in JSON object or is empty");
        }

        try {
            this.hireDate = Date.valueOf(hireDateStr);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("\"hire_date\" value cannot be converted to Date");
        }
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

    public static class Builder {

        private EmployeeDto entity = new EmployeeDto();

        public Builder empNo(String empNo) {
            entity.setEmpNo(empNo);
            return this;
        }

        public Builder empNo(int empNo) {
            entity.setEmpNo(empNo);
            return this;
        }

        public Builder birthDate(String birthDate) {
            entity.setBirthDate(birthDate);
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

        public Builder gender(String gender) {
            entity.setGender(gender);
            return this;
        }

        public Builder gender(Gender gender) {
            entity.setGender(gender);
            return this;
        }

        public Builder hireDate(String hireDate) {
            entity.setHireDate(hireDate);
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