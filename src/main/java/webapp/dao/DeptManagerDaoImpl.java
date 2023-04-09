package webapp.dao;

import webapp.config.MariaDBConnectionPool;
import webapp.entity.DeptManager;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DeptManagerDaoImpl implements DeptManagerDao {

    @Override
    public int create(DeptManager deptManager) {

        String sql = "INSERT INTO dept_manager (dept_no, emp_no, from_date, to_date) VALUES (?, ?, ?, ?)";

        try (Connection conn = MariaDBConnectionPool.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, deptManager.getDeptNo());
            pstmt.setInt(2, deptManager.getEmpNo());
            pstmt.setDate(3, deptManager.getFromDate());
            pstmt.setDate(4, deptManager.getToDate());

            return pstmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<DeptManager> findAll() {

        String sql = "SELECT * FROM dept_manager";

        try (Connection conn = MariaDBConnectionPool.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            List<DeptManager> deptManagers = new ArrayList<>();

            while (rs.next()) {
                deptManagers.add(
                        new DeptManager.Builder()
                                .deptNo(rs.getString("dept_no"))
                                .empNo(rs.getInt("emp_no"))
                                .fromDate(rs.getDate("from_date"))
                                .toDate(rs.getDate("to_date"))
                                .build()
                );
            }

            return deptManagers;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<DeptManager> findByEmpNoAndDeptNo(int empNo, String deptNo) {

        String sql = "SELECT * FROM dept_manager WHERE emp_no = ? AND dept_no = ?";

        try (Connection conn = MariaDBConnectionPool.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, empNo);
            pstmt.setString(2, deptNo);

            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                return Optional.of(
                        new DeptManager.Builder()
                                .empNo(rs.getInt("emp_no"))
                                .deptNo(rs.getString("dept_no"))
                                .fromDate(rs.getDate("from_date"))
                                .toDate(rs.getDate("to_date"))
                                .build()
                );
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return Optional.empty();
    }

    @Override
    public int update(DeptManager deptManager) {

        String sql = "UPDATE dept_manager SET from_date = ?, to_date = ? WHERE emp_no = ? AND dept_no = ?";

        try (Connection conn = MariaDBConnectionPool.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setDate(1, deptManager.getFromDate());
            pstmt.setDate(2, deptManager.getToDate());
            pstmt.setInt(3, deptManager.getEmpNo());
            pstmt.setString(4, deptManager.getDeptNo());

            return pstmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int delete(DeptManager deptManager) {

        String sql = "DELETE FROM dept_manager WHERE emp_no = ? AND dept_no = ?";

        try (Connection conn = MariaDBConnectionPool.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, deptManager.getEmpNo());
            pstmt.setString(2, deptManager.getDeptNo());

            return pstmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}