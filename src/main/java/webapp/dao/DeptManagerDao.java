package webapp.dao;

import webapp.entity.DeptManager;

import java.util.List;

public interface DeptManagerDao {

    int create(DeptManager deptManager);

    List<DeptManager> findAll();

    DeptManager findByEmpNoAndDeptNo(int empNo, String deptNo);

    int update(DeptManager deptManager);

    int delete(int empNo, String deptNo);
}

