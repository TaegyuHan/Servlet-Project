package webapp.dao;

import webapp.entity.DeptEmp;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface DeptEmpDao {

    int create(DeptEmp deptEmp);

    List<DeptEmp> findAll();

    Optional<DeptEmp> findDeptEmpByNo(int empNo, String deptNo, LocalDate fromDate);

    int update(DeptEmp deptEmp);

    int delete(int empNo, String deptNo, LocalDate fromDate);
}

