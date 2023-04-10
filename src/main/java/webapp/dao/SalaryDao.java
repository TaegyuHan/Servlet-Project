package webapp.dao;

import webapp.entity.Salary;

import java.sql.Date;
import java.util.List;
import java.util.Optional;


public interface SalaryDao {

    int create(Salary entity);

    List<Salary> findAll();

    Optional<Salary> findByEmpNoAndFromDate(int empNo, Date fromDate);

    List<Salary> findByEmpNo(int empNo);

    List<Salary> findByFromDate(Date fromDate);

    int update(Salary entity);

    int delete(Salary entity);
}