package webapp.dao;

import webapp.config.MariaDBConnectionPool;
import webapp.entity.DeptEmp;

import java.sql.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


public class DeptEmpDaoImpl implements DeptEmpDao {

    @Override
    public Optional<DeptEmp> create(DeptEmp entity) {

        String sql = "INSERT INTO dept_emp (emp_no, dept_no, from_date, to_date) VALUES (?, ?, ?, ?)";

        try (Connection conn = MariaDBConnectionPool.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, entity.getEmpNo());
            pstmt.setString(2, entity.getDeptNo());
            pstmt.setDate(3, entity.getFromDate());
            pstmt.setDate(4, entity.getToDate());

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
    public List<DeptEmp> findByEmpNo(int empNo) {

        String sql = "SELECT * FROM dept_emp WHERE emp_no = ?";

        try (Connection conn = MariaDBConnectionPool.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, empNo);

            ResultSet rs = pstmt.executeQuery();

            List<DeptEmp> deptEmps = new ArrayList<>();

            while (rs.next()) {
                DeptEmp deptEmp = new DeptEmp.Builder()
                        .empNo(rs.getInt("emp_no"))
                        .deptNo(rs.getString("dept_no"))
                        .fromDate(rs.getDate("from_date"))
                        .toDate(rs.getDate("to_date"))
                        .build();
                deptEmps.add(deptEmp);
            }

            return deptEmps;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<DeptEmp> findByDeptNo(String deptNo) {

        String sql = "SELECT * FROM dept_emp WHERE dept_no = ?";

        try (Connection conn = MariaDBConnectionPool.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, deptNo);

            ResultSet rs = pstmt.executeQuery();

            List<DeptEmp> deptEmps = new ArrayList<>();

            while (rs.next()) {
                DeptEmp deptEmp = new DeptEmp.Builder()
                        .empNo(rs.getInt("emp_no"))
                        .deptNo(rs.getString("dept_no"))
                        .fromDate(rs.getDate("from_date"))
                        .toDate(rs.getDate("to_date"))
                        .build();
                deptEmps.add(deptEmp);
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
    public Optional<DeptEmp> update(DeptEmp entity) {

        String sql = "UPDATE dept_emp SET dept_no = ?, from_date = ?, to_date = ? "
                + "WHERE emp_no = ?";

        try (Connection conn = MariaDBConnectionPool.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, entity.getDeptNo());
            pstmt.setDate(2, entity.getFromDate());
            pstmt.setDate(3, entity.getToDate());
            pstmt.setInt(4, entity.getEmpNo());

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
    public int delete(DeptEmp entity) {

        String sql = "DELETE FROM dept_emp WHERE emp_no = ? AND dept_no = ?";

        try (Connection conn = MariaDBConnectionPool.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, entity.getEmpNo());
            pstmt.setString(2, entity.getDeptNo());

            return pstmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}