package webapp.dao;

import webapp.entity.Title;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

public interface TitleDao {

    Optional<Title> create(Title entity);

    List<Title> findAll();

    List<Title> findByEmpNo(int empNo);

    List<Title> findByTitle(String title);

    List<Title> findByToDate(Date toDate);

    List<Title> findByFromDate(Date fromDate);

    Optional<Title> findByEmpNoAndTitleAndFromDate(int empNo, String title, Date fromDate);

    Optional<Title> update(Title entity);

    int delete(Title entity);
}