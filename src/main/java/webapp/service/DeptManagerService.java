package webapp.service;

import webapp.dao.DeptManagerDao;
import webapp.dao.DeptManagerDaoImpl;
import webapp.dto.DeptManagerDto;

import java.util.List;
import java.util.Optional;


public class DeptManagerService {

    private final DeptManagerDao dao = new DeptManagerDaoImpl();

    public int create(DeptManagerDto DeptManagerDto) {
        return dao.create(DeptManagerDto.toEntity());
    }

    public List<DeptManagerDto> searchAll() {
        return DeptManagerDto.entitiesToDtos(dao.findAll());
    }

    public Optional<DeptManagerDto> searchByEmpNoAndDeptNo(int empNo, String deptNo) {
        return DeptManagerDto.entityToDto(dao.findByEmpNoAndDeptNo(empNo, deptNo));
    }

    public List<DeptManagerDto> searchByEmpNo(int empNo) {
        return DeptManagerDto.entitiesToDtos(dao.findByEmpNo(empNo));
    }

    public List<DeptManagerDto> searchByDeptNo(String deptNo) {
        return DeptManagerDto.entitiesToDtos(dao.findByDeptNo(deptNo));
    }

    public int update(DeptManagerDto dto) {
        return dao.update(dto.toEntity());
    }

    public int delete(DeptManagerDto dto) {
        return dao.delete(dto.toEntity());
    }
}