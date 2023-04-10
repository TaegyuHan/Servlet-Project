package webapp.dao;

import webapp.entity.DeptEmp;

import java.util.List;
import java.util.Optional;

public interface DeptEmpDao {

    int create(DeptEmp deptEmp);

    List<DeptEmp> findAll();

    List<DeptEmp> findByEmpNo(int empNo);

    List<DeptEmp> findByDeptNo(String deptNo);

    Optional<DeptEmp> findByEmpNoAndDeptNo(int empNo, String deptNo);

    int update(DeptEmp deptEmp);

    int delete(DeptEmp deptEmp);
}