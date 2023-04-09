package webapp.dao;

import webapp.entity.Employee;

import java.util.List;
import java.util.Optional;

public interface EmployeeDao {

    int create(Employee employee);

    List<Employee> findAll();

    Optional<Employee> findByNo(int id);

    int update(Employee employee);

    int delete(Employee employee);
}
