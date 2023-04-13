package webapp.dao;

import webapp.entity.Title;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

public interface TitleDao {

    Optional<Title> create(Title title);

    List<Title> findAll();

    List<Title> findByEmpNo(Date fromDate);

    List<Title> findByTitle(String title);

    List<Title> findByFromDate(Date toDate);

    Optional<Title> update(Title title);

    int delete(Title title);
}