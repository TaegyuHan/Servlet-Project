package webapp.dao;

import webapp.entity.DeptEmp;

import java.util.List;
import java.util.Optional;

public interface DeptEmpDao {

    Optional<DeptEmp> create(DeptEmp entity);

    List<DeptEmp> findAll();

    List<DeptEmp> findByEmpNo(int empNo);

    List<DeptEmp> findByDeptNo(String deptNo);

    Optional<DeptEmp> findByEmpNoAndDeptNo(int empNo, String deptNo);

    Optional<DeptEmp> update(DeptEmp entity);

    int delete(DeptEmp entity);
}