package webapp.dao;

import webapp.entity.DeptManager;

import java.util.List;
import java.util.Optional;

public interface DeptManagerDao {

    int create(DeptManager deptManager);

    List<DeptManager> findAll();

    Optional<DeptManager> findByEmpNoAndDeptNo(int empNo, String deptNo);

    int update(DeptManager deptManager);

    int delete(int empNo, String deptNo);
}

