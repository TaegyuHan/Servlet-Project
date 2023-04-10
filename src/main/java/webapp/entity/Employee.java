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

    public static class Builder {

        private Employee entity = new Employee();

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

        public Employee build() {
            return entity;
        }
    }
}