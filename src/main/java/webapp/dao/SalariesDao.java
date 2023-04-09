package webapp.dao;

import webapp.entity.Salary;

import java.util.List;
import java.util.Optional;

public interface SalariesDao {

    int create(Salary salaries);

    List<Salary> findAll();

    Optional<Salary> findById(int empNo);

    int update(Salary salaries);

    int delete(int empNo);
}