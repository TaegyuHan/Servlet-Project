package webapp.dao;

import webapp.config.MariaDBConnectionPool;
import webapp.entity.Salary;

import java.sql.*;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SalariesDaoImpl implements SalariesDao {

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
    public int update(Salary salaries) {

        String sql = "UPDATE salaries SET salary = ?, to_date = ? WHERE emp_no = ? AND from_date = ?";

        try (Connection conn = MariaDBConnectionPool.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, salaries.getSalary());
            pstmt.setDate(2, salaries.getToDate());
            pstmt.setInt(3, salaries.getEmpNo());
            pstmt.setDate(4, salaries.getFromDate());

            return pstmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int delete(int empNo, Date fromDate) {

        String sql = "DELETE FROM salaries WHERE emp_no = ? AND from_date = ?";

        try (Connection conn = MariaDBConnectionPool.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, empNo);
            pstmt.setDate(2, fromDate);

            return pstmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}