package webapp.dao;

import webapp.entity.Salary;

import java.util.List;

public interface SalariesDao {

    int create(Salary salaries);

    List<Salary> findAll();

    Salary findById(int empNo);

    int update(Salary salaries);

    int delete(int empNo);
}