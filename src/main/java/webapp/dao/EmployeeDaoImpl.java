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
    public Optional<Employee> create(Employee entity) {

        String sql = "INSERT INTO employees (birth_date, first_name, last_name, gender, hire_date) " +
                "VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = MariaDBConnectionPool.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            pstmt.setDate(1, entity.getBirthDate());
            pstmt.setString(2, entity.getFirstName());
            pstmt.setString(3, entity.getLastName());
            pstmt.setString(4, entity.getGender().name());
            pstmt.setDate(5, entity.getHireDate());

            int changeRow = pstmt.executeUpdate();

            if (changeRow == 0) {
                return Optional.empty(); // 업데이트 실패
            }

            return Optional.of(entity);

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
    public Optional<Employee> findByEmpNo(int id) {

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
    public Optional<Employee> update(Employee entity) {

        String sql = "UPDATE employees " +
                "SET birth_date = ?, " +
                "first_name = ?, " +
                "last_name = ?, " +
                "gender = ?, " +
                "hire_date = ? " +
                "WHERE emp_no = ?";

        try (Connection conn = MariaDBConnectionPool.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setDate(1, entity.getBirthDate());
            pstmt.setString(2, entity.getFirstName());
            pstmt.setString(3, entity.getLastName());
            pstmt.setString(4, entity.getGender().toString());
            pstmt.setDate(5, entity.getHireDate());
            pstmt.setInt(6, entity.getEmpNo());

            int changeRow = pstmt.executeUpdate();

            if (changeRow == 0) {
                return Optional.empty(); // 업데이트 실패
            }

            return Optional.of(entity);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int delete(Employee entity) {

        String sql = "DELETE FROM employees WHERE emp_no = ?";

        try (Connection conn = MariaDBConnectionPool.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, entity.getEmpNo());

            return pstmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}