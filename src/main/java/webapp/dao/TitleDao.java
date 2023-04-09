package webapp.dao;

import webapp.entity.Title;

import java.sql.Date;
import java.util.List;

public interface TitleDao {

    int create(Title title);

    List<Title> findAll();

    List<Title> findByEmpNo(Date fromDate);

    List<Title> findByTitle(String title);

    List<Title> findByFromDate(Date toDate);

    int update(Title title);

    int delete(Title title);
}
