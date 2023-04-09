package webapp.dao;

import webapp.config.MariaDBConnectionPool;
import webapp.entity.DeptEmp;

import java.sql.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


public class DeptEmpDaoImpl implements DeptEmpDao {

    @Override
    public int create(DeptEmp deptEmp) {

        String sql = "INSERT INTO dept_emp (emp_no, dept_no, from_date, to_date) VALUES (?, ?, ?, ?)";

        try (Connection conn = MariaDBConnectionPool.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, deptEmp.getEmpNo());
            pstmt.setString(2, deptEmp.getDeptNo());
            pstmt.setDate(3, deptEmp.getFromDate());
            pstmt.setDate(4, deptEmp.getToDate());

            return pstmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<DeptEmp> findAll() {

        String sql = "SELECT * FROM dept_emp";

        try (Connection conn = MariaDBConnectionPool.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            List<DeptEmp> deptEmps = new ArrayList<>();

            while (rs.next()) {
                deptEmps.add(
                        new DeptEmp.Builder()
                                .empNo(rs.getInt("emp_no"))
                                .deptNo(rs.getString("dept_no"))
                                .fromDate(rs.getDate("from_date"))
                                .toDate(rs.getDate("to_date"))
                                .build()
                );
            }

            return deptEmps;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<DeptEmp> findByEmpNoAndDeptNo(int empNo, String deptNo) {

        String sql = "SELECT * FROM dept_emp WHERE emp_no = ? AND dept_no = ?";

        try (Connection conn = MariaDBConnectionPool.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, empNo);
            pstmt.setString(2, deptNo);

            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                return Optional.of(
                        new DeptEmp.Builder()
                                .empNo(rs.getInt("emp_no"))
                                .deptNo(rs.getString("dept_no"))
                                .fromDate(rs.getDate("from_date"))
                                .toDate(rs.getDate("to_date"))
                                .build()
                );
            }

            return Optional.empty();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int update(DeptEmp deptEmp) {

        String sql = "UPDATE dept_emp SET dept_no = ?, from_date = ?, to_date = ? "
                + "WHERE emp_no = ?";

        try (Connection conn = MariaDBConnectionPool.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, deptEmp.getDeptNo());
            pstmt.setDate(2, deptEmp.getFromDate());
            pstmt.setDate(3, deptEmp.getToDate());
            pstmt.setInt(4, deptEmp.getEmpNo());

            return pstmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int delete(DeptEmp deptEmp) {

        String sql = "DELETE FROM dept_emp WHERE emp_no = ? AND dept_no = ?";

        try (Connection conn = MariaDBConnectionPool.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, deptEmp.getEmpNo());
            pstmt.setString(2, deptEmp.getDeptNo());

            return pstmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}