package webapp.dao;

import webapp.entity.Department;

import java.util.List;
import java.util.Optional;

public interface DepartmentDao {

    Optional<Department> create(Department department);

    List<Department> findAll();

    Optional<Department> findByDeptNo(String deptNo);

    Optional<Department> findByDeptName(String deptName);

    Optional<Department> update(Department department);

    int delete(Department department);
}