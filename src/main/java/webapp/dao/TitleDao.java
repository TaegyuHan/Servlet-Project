package webapp.dao;

import webapp.entity.Title;

import java.time.LocalDate;
import java.util.List;

public interface TitleDao {

    int create(Title title);

    List<Title> findByEmpNo(int empNo);

    List<Title> findByTitle(String title);

    List<Title> findByFromDate(LocalDate fromDate);

    List<Title> findByToDate(LocalDate toDate);

    int update(Title title);

    int deleteByEmpNo(int empNo);
}
