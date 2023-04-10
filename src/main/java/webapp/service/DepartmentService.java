package webapp.service;

import webapp.dao.DepartmentDao;
import webapp.dao.DepartmentDaoImpl;
import webapp.dto.DepartmentDto;

import java.util.List;
import java.util.Optional;


public class DepartmentService {

    private final DepartmentDao dao = new DepartmentDaoImpl();

    public int create(DepartmentDto dto) {
        return dao.create(dto.toEntity());
    }

    public List<DepartmentDto> searchAll() {
        return DepartmentDto.entitiesToDtos(dao.findAll());
    }

    public Optional<DepartmentDto> searchByDeptNo(String deptNo) {
        return DepartmentDto.entityToDto(dao.findByDeptNo(deptNo));
    }

    public Optional<DepartmentDto> searchByDeptName(String deptName) {
        return DepartmentDto.entityToDto(dao.findByDeptName(deptName));
    }

    public int update(DepartmentDto dto) {
        return dao.update(dto.toEntity());
    }

    public int delete(DepartmentDto dto) {
        return dao.delete(dto.toEntity());
    }
}