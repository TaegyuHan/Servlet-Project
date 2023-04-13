package webapp.dao;

import webapp.entity.Employee;

import java.util.List;
import java.util.Optional;


public interface EmployeeDao {

    Optional<Employee> create(Employee entity);

    List<Employee> findAll();

    Optional<Employee> findByEmpNo(int id);

    Optional<Employee> update(Employee entity);

    int delete(Employee entity);
}
