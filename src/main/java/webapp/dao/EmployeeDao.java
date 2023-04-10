package webapp.dao;

import webapp.entity.Employee;

import java.util.List;
import java.util.Optional;


public interface EmployeeDao {

    int create(Employee entity);

    List<Employee> findAll();

    Optional<Employee> findByEmpNo(int id);

    int update(Employee entity);

    int delete(Employee entity);
}
