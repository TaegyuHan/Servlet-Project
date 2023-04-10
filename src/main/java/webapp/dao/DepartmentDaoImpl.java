package webapp.dao;

import webapp.config.MariaDBConnectionPool;
import webapp.entity.Department;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DepartmentDaoImpl implements DepartmentDao {

    @Override
    public int create(Department entity) {

        String sql = "INSERT INTO departments (dept_no, dept_name) VALUES (?, ?)";

        try (Connection conn = MariaDBConnectionPool.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, entity.getDeptNo());
            pstmt.setString(2, entity.getDeptName());

            return pstmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Department> findAll() {

        String sql = "SELECT * FROM departments";

        try (Connection conn = MariaDBConnectionPool.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            List<Department> departments = new ArrayList<>();

            while (rs.next()) {
                departments.add(
                        new Department.Builder()
                                .deptNo(rs.getString("dept_no"))
                                .deptName(rs.getString("dept_name"))
                                .build()
                );
            }

            return departments;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<Department> findByDeptNo(String deptNo) {

        String sql = "SELECT * FROM departments WHERE dept_no = ?";

        try (Connection conn = MariaDBConnectionPool.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, deptNo);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                return Optional.of(
                        new Department.Builder()
                                .deptNo(deptNo)
                                .deptName(rs.getString("dept_name"))
                                .build()
                );
            }
            return Optional.empty();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<Department> findByDeptName(String deptName) {

        String sql = "SELECT * FROM departments WHERE dept_name = ?";

        try (Connection conn = MariaDBConnectionPool.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, deptName);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                return Optional.of(
                        new Department.Builder()
                                .deptNo(rs.getString("dept_no"))
                                .deptName(deptName)
                                .build()
                );
            }

            return Optional.empty();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int update(Department entity) {

        String sql = "UPDATE departments SET dept_name = ? WHERE dept_no = ?";

        try (Connection conn = MariaDBConnectionPool.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, entity.getDeptName());
            pstmt.setString(2, entity.getDeptNo());

            return pstmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int delete(Department entity) {

        String sql = "DELETE FROM departments WHERE dept_no = ?";

        try (Connection conn = MariaDBConnectionPool.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, entity.getDeptNo());

            return pstmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}