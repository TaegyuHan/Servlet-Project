package webapp.service;

import webapp.dao.DeptEmpDao;
import webapp.dao.DeptEmpDaoImpl;
import webapp.dto.DeptEmpDto;

import java.util.List;
import java.util.Optional;


public class DeptEmpService {

    private final DeptEmpDao dao = new DeptEmpDaoImpl();

    public int create(DeptEmpDto dto) {
        return dao.create(dto.toEntity());
    }

    public List<DeptEmpDto> searchAll() {
        return DeptEmpDto.entitiesToDtos(dao.findAll());
    }

    public Optional<DeptEmpDto> searchByEmpNoAndDeptNo(int empNo, String deptNo) {
        return DeptEmpDto.entityToDto(dao.findByEmpNoAndDeptNo(empNo, deptNo));
    }

    public List<DeptEmpDto> searchByEmpNo(int empNo) {
        return DeptEmpDto.entitiesToDtos(dao.findByEmpNo(empNo));
    }

    public List<DeptEmpDto> searchByDeptNo(String deptNo) {
        return DeptEmpDto.entitiesToDtos(dao.findByDeptNo(deptNo));
    }

    public int update(DeptEmpDto dto) {
        return dao.update(dto.toEntity());
    }

    public int delete(DeptEmpDto dto) {
        return dao.delete(dto.toEntity());
    }
}