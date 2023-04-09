package webapp.dao;

import webapp.entity.Salary;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

public interface SalariesDao {

    int create(Salary salaries);

    List<Salary> findAll();

    Optional<Salary> findByEmpNoAndFromDate(int empNo, Date fromDate);

    int update(Salary salaries);

    int delete(int empNo, Date fromDate);
}