package webapp.dao;


import webapp.config.MariaDBConnectionPool;
import webapp.entity.Employee;
import webapp.entity.Gender;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class EmployeeDaoImpl implements EmployeeDao {

    @Override
    public int create(Employee employee) {

        String sql = "INSERT INTO employees (birth_date, first_name, last_name, gender, hire_date) " +
                "VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = MariaDBConnectionPool.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setDate(1, employee.getBirthDate());
            pstmt.setString(2, employee.getFirstName());
            pstmt.setString(3, employee.getLastName());
            pstmt.setString(4, employee.getGender().name());
            pstmt.setDate(5, employee.getHireDate());

            return pstmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Employee> findAll() {

        String sql = "SELECT * FROM employees";

        try (Connection conn = MariaDBConnectionPool.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            List<Employee> employees = new ArrayList<>();

            while (rs.next()) {
                employees.add(
                        new Employee.Builder()
                                .empNo(rs.getInt("emp_no"))
                                .birthDate(rs.getDate("birth_date"))
                                .firstName(rs.getString("first_name"))
                                .lastName(rs.getString("last_name"))
                                .gender(Gender.valueOf(rs.getString("gender")))
                                .hireDate(rs.getDate("hire_date"))
                                .build()
                );
            }

            return employees;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<Employee> findByNo(int id) {

        String sql = "SELECT * FROM employees " +
                "WHERE emp_no = ?";

        try (Connection conn = MariaDBConnectionPool.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);

            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                return Optional.of(
                        new Employee.Builder()
                                .empNo(rs.getInt("emp_no"))
                                .birthDate(rs.getDate("birth_date"))
                                .firstName(rs.getString("first_name"))
                                .lastName(rs.getString("last_name"))
                                .gender(Gender.valueOf(rs.getString("gender")))
                                .hireDate(rs.getDate("hire_date"))
                                .build()
                );
            }

            return Optional.empty();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int update(Employee employee) {

        String sql = "UPDATE employees " +
                "SET birth_date = ?, " +
                "first_name = ?, " +
                "last_name = ?, " +
                "gender = ?, " +
                "hire_date = ? " +
                "WHERE emp_no = ?";

        try (Connection conn = MariaDBConnectionPool.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setDate(1, employee.getBirthDate());
            pstmt.setString(2, employee.getFirstName());
            pstmt.setString(3, employee.getLastName());
            pstmt.setString(4, employee.getGender().toString());
            pstmt.setDate(5, employee.getHireDate());
            pstmt.setInt(6, employee.getEmpNo());

            return pstmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int delete(Employee employee) {

        String sql = "DELETE FROM employees WHERE emp_no = ?";

        try (Connection conn = MariaDBConnectionPool.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, employee.getEmpNo());

            return pstmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}