package webapp.service;

import webapp.dao.EmployeeDao;
import webapp.dao.EmployeeDaoImpl;
import webapp.dto.EmployeeDto;

import java.util.List;
import java.util.Optional;


public class EmployeeService {

    private final EmployeeDao dao = new EmployeeDaoImpl();

    public int create(EmployeeDto dto) {
        return dao.create(dto.toEntity());
    }

    public List<EmployeeDto> searchAll() {
        return EmployeeDto.entitiesToDtos(dao.findAll());
    }

    public Optional<EmployeeDto> searchByEmpNo(int empNo) {
        return EmployeeDto.entityToDto(dao.findByEmpNo(empNo));
    }

    public int update(EmployeeDto dto) {
        return dao.update(dto.toEntity());
    }

    public int delete(EmployeeDto dto) {
        return dao.delete(dto.toEntity());
    }
}