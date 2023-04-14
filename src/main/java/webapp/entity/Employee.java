package webapp.entity;

import java.sql.Date;
import java.util.Objects;


public class Employee {

    private int empNo;
    private Date birthDate;
    private String firstName;
    private String lastName;
    private Gender gender;
    private Date hireDate;

    public Employee() {}

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee that = (Employee) o;
        return this.empNo == that.empNo &&
                Objects.equals(this.birthDate, that.birthDate) &&
                Objects.equals(this.firstName, that.firstName) &&
                Objects.equals(this.lastName, that.lastName) &&
                this.gender == that.gender &&
                Objects.equals(this.hireDate, that.hireDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.empNo, this.birthDate, this.firstName, this.lastName, this.gender, this.hireDate);
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