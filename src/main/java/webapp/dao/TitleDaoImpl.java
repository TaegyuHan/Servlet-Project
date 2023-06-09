package webapp.dao;

import webapp.config.MariaDBConnectionPool;
import webapp.entity.Title;

import java.sql.*;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TitleDaoImpl implements TitleDao {

    @Override
    public Optional<Title> create(Title entity) {

        String sql = "INSERT INTO titles (emp_no, title, from_date, to_date) VALUES (?, ?, ?, ?)";

        try (Connection conn = MariaDBConnectionPool.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, entity.getEmpNo());
            pstmt.setString(2, entity.getTitle());
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
    public List<Title> findAll() {

        String sql = "SELECT * FROM titles";

        try (Connection conn = MariaDBConnectionPool.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            List<Title> titles = new ArrayList<>();

            while (rs.next()) {
                titles.add(
                        new Title.Builder()
                                .title(rs.getString("title"))
                                .fromDate(rs.getDate("from_date"))
                                .toDate(rs.getDate("to_date"))
                                .empNo(rs.getInt("emp_no"))
                                .build()
                );
            }

            return titles;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Title> findByEmpNo(int empNo) {

        String sql = "SELECT * FROM titles WHERE emp_no = ?";

        try (Connection conn = MariaDBConnectionPool.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, empNo);

            ResultSet rs = pstmt.executeQuery();

            List<Title> titles = new ArrayList<>();

            while (rs.next()) {
                titles.add(
                        new Title.Builder()
                                .title(rs.getString("title"))
                                .fromDate(rs.getDate("from_date"))
                                .toDate(rs.getDate("to_date"))
                                .empNo(rs.getInt("emp_no"))
                                .build()
                );
            }

            return titles;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Title> findByTitle(String title) {

        String sql = "SELECT * FROM titles WHERE title = ?";

        List<Title> titles = new ArrayList<>();

        try (Connection conn = MariaDBConnectionPool.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, title);

            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                titles.add(
                        new Title.Builder()
                                .empNo(rs.getInt("emp_no"))
                                .title(rs.getString("title"))
                                .fromDate(rs.getDate("from_date"))
                                .toDate(rs.getDate("to_date"))
                                .build()
                );
            }

            return titles;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Title> findByToDate(Date toDate) {

        String sql = "SELECT * FROM titles WHERE to_date = ?";

        try (Connection conn = MariaDBConnectionPool.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            List<Title> titles = new ArrayList<>();

            pstmt.setDate(1, toDate);

            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                titles.add(
                        new Title.Builder()
                                .empNo(rs.getInt("emp_no"))
                                .title(rs.getString("title"))
                                .fromDate(rs.getDate("from_date"))
                                .toDate(rs.getDate("to_date"))
                                .build()
                );
            }

            return titles;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Title> findByFromDate(Date fromDate) {

        String sql = "SELECT * FROM titles WHERE from_date = ?";

        try (Connection conn = MariaDBConnectionPool.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            List<Title> titles = new ArrayList<>();

            pstmt.setDate(1, fromDate);

            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                titles.add(
                        new Title.Builder()
                                .empNo(rs.getInt("emp_no"))
                                .title(rs.getString("title"))
                                .fromDate(rs.getDate("from_date"))
                                .toDate(rs.getDate("to_date"))
                                .build()
                );
            }

            return titles;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<Title> findByEmpNoAndTitleAndFromDate(int empNo, String title, Date fromDate) {

        String sql = "SELECT * FROM titles WHERE emp_no = ? AND title = ? AND from_date = ?";

        try (Connection conn = MariaDBConnectionPool.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, empNo);
            pstmt.setString(2, title);
            pstmt.setDate(3, fromDate);

            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                return Optional.of(
                        new Title.Builder()
                                .empNo(rs.getInt("emp_no"))
                                .title(rs.getString("title"))
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
    public Optional<Title> update(Title entity) {

        String sql = "UPDATE titles SET to_date = ? WHERE emp_no = ? AND title = ? AND from_date = ?";

        try (Connection conn = MariaDBConnectionPool.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setDate(1, entity.getToDate());
            pstmt.setInt(2, entity.getEmpNo());
            pstmt.setString(3, entity.getTitle());
            pstmt.setDate(4, entity.getFromDate());

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
    public int delete(Title entity) {

        String sql = "DELETE FROM titles WHERE emp_no = ? AND title = ? AND from_date = ?";

        try (Connection conn = MariaDBConnectionPool.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, entity.getEmpNo());
            pstmt.setString(2, entity.getTitle());
            pstmt.setDate(3, entity.getFromDate());

            return pstmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}