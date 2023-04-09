package webapp.dao;

import webapp.entity.Department;

import java.util.List;
import java.util.Optional;

public interface DepartmentDao {

    int create(Department department);

    List<Department> findAll();

    Optional<Department> findByDeptNo(String deptNo);

    Optional<Department> findByDeptName(String deptName);

    int update(Department department);

    int delete(Department department);
}