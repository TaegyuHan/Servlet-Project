package webapp.entity;

import java.sql.Date;

public class Employee {

    private int empNo;
    private Date birthDate;
    private String firstName;
    private String lastName;
    private Gender gender;
    private Date hireDate;

    public Employee() {};

    public Employee(int empNo, Date birthDate, String firstName, String lastName, Gender gender, Date hireDate) {
        this.empNo = empNo;
        this.birthDate = birthDate;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.hireDate = hireDate;
    }

    public int getEmpNo() {
        return empNo;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Gender getGender() {
        return gender;
    }

    public Date getHireDate() {
        return hireDate;
    }

    private Employee(Builder builder) {
        this.empNo = builder.empNo;
        this.birthDate = builder.birthDate;
        this.firstName = builder.firstName;
        this.lastName = builder.lastName;
        this.gender = builder.gender;
        this.hireDate = builder.hireDate;
    }

    public static class Builder {
        private int empNo;
        private Date birthDate;
        private String firstName;
        private String lastName;
        private Gender gender;
        private Date hireDate;

        public Builder empNo(int empNo) {
            this.empNo = empNo;
            return this;
        }

        public Builder birthDate(Date birthDate) {
            this.birthDate = birthDate;
            return this;
        }

        public Builder firstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public Builder lastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public Builder gender(Gender gender) {
            this.gender = gender;
            return this;
        }

        public Builder hireDate(Date hireDate) {
            this.hireDate = hireDate;
            return this;
        }

        public Employee build() {
            return new Employee(this);
        }
    }
}