package webapp.dao;

import webapp.entity.Employee;

import java.util.List;

public interface EmployeeDao {

    int create(Employee employee);

    List<Employee> findAll();

    Employee findByNo(int id);

    int update(Employee employee);

    int delete(int id);
}
