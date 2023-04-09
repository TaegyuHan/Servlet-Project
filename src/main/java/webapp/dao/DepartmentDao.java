package webapp.dao;

import webapp.entity.Department;

import java.util.List;
import java.util.Optional;

public interface DepartmentDao {

    int create(Department department);

    List<Department> findAll();

    Optional<Department> findByDeptNo(String deptNo);

    int update(Department department);

    int delete(String deptNo);
}