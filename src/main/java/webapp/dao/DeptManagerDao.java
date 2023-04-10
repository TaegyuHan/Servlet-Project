package webapp.dao;

import webapp.entity.DeptManager;

import java.util.List;
import java.util.Optional;


public interface DeptManagerDao {

    int create(DeptManager entity);

    List<DeptManager> findAll();

    Optional<DeptManager> findByEmpNoAndDeptNo(int empNo, String deptNo);

    List<DeptManager> findByEmpNo(int empNo);

    List<DeptManager> findByDeptNo(String deptNo);

    int update(DeptManager entity);

    int delete(DeptManager entity);
}

