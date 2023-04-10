package webapp.dao;

import webapp.config.MariaDBConnectionPool;
import webapp.entity.Salary;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


public class SalaryDaoImpl implements SalaryDao {

    @Override
    public int create(Salary salary) {

        String sql = "INSERT INTO salaries (emp_no, salary, from_date, to_date) VALUES (?, ?, ?, ?)";

        try (Connection conn = MariaDBConnectionPool.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, salary.getEmpNo());
            pstmt.setInt(2, salary.getSalary());
            pstmt.setDate(3, salary.getFromDate());
            pstmt.setDate(4, salary.getToDate());

            return pstmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Salary> findAll() {

        String sql = "SELECT * FROM salaries";

        try (Connection conn = MariaDBConnectionPool.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            List<Salary> salaries = new ArrayList<>();

            while (rs.next()) {
                salaries.add(
                        new Salary.Builder()
                                .empNo(rs.getInt("emp_no"))
                                .salary(rs.getInt("salary"))
                                .fromDate(rs.getDate("from_date"))
                                .toDate(rs.getDate("to_date"))
                                .build()
                );
            }

            return salaries;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<Salary> findByEmpNoAndFromDate(int empNo, Date fromDate) {

        String sql = "SELECT * FROM salaries WHERE emp_no = ? AND from_date = ?";

        try (Connection conn = MariaDBConnectionPool.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, empNo);
            pstmt.setDate(2, fromDate);

            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                return Optional.of(
                        new Salary.Builder()
                                .empNo(rs.getInt("emp_no"))
                                .salary(rs.getInt("salary"))
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
    public List<Salary> findByEmpNo(int empNo) {

        List<Salary> salaries = new ArrayList<>();

        String sql = "SELECT * FROM salaries WHERE emp_no = ?";

        try (Connection conn = MariaDBConnectionPool.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, empNo);

            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                salaries.add(
                        new Salary.Builder()
                                .empNo(rs.getInt("emp_no"))
                                .salary(rs.getInt("salary"))
                                .fromDate(rs.getDate("from_date"))
                                .toDate(rs.getDate("to_date"))
                                .build()
                );
            }

            return salaries;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Salary> findByFromDate(Date fromDate) {

        String sql = "SELECT * FROM salaries WHERE from_date = ?";

        List<Salary> result = new ArrayList<>();

        try (Connection conn = MariaDBConnectionPool.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setDate(1, fromDate);

            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                Salary salary = new Salary.Builder()
                        .empNo(rs.getInt("emp_no"))
                        .salary(rs.getInt("salary"))
                        .fromDate(rs.getDate("from_date"))
                        .toDate(rs.getDate("to_date"))
                        .build();
                result.add(salary);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return result;
    }

    @Override
    public int update(Salary entity) {

        String sql = "UPDATE salaries SET salary = ?, to_date = ? WHERE emp_no = ? AND from_date = ?";

        try (Connection conn = MariaDBConnectionPool.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, entity.getSalary());
            pstmt.setDate(2, entity.getToDate());
            pstmt.setInt(3, entity.getEmpNo());
            pstmt.setDate(4, entity.getFromDate());

            return pstmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int delete(Salary entity) {

        String sql = "DELETE FROM salaries WHERE emp_no = ? AND from_date = ?";

        try (Connection conn = MariaDBConnectionPool.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, entity.getEmpNo());
            pstmt.setDate(2, entity.getFromDate());

            return pstmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}