package webapp.dao;

import webapp.entity.Department;

import java.util.List;

public interface DepartmentDao {

    int create(Department department);

    List<Department> findAll();

    Department findByDeptNo(String deptNo);

    int update(Department department);

    int delete(String deptNo);
}