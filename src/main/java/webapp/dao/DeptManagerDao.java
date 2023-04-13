package webapp.dao;

import webapp.entity.DeptManager;

import java.util.List;
import java.util.Optional;


public interface DeptManagerDao {

    Optional<DeptManager> create(DeptManager entity);

    List<DeptManager> findAll();

    Optional<DeptManager> findByEmpNoAndDeptNo(int empNo, String deptNo);

    List<DeptManager> findByEmpNo(int empNo);

    List<DeptManager> findByDeptNo(String deptNo);

    Optional<DeptManager> update(DeptManager entity);

    int delete(DeptManager entity);
}

