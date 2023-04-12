package webapp.service;

import webapp.dao.EmployeeDao;
import webapp.dao.EmployeeDaoImpl;
import webapp.dto.EmployeeDto;
import webapp.entity.Employee;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


public class EmployeeService {

    private final EmployeeDao dao = new EmployeeDaoImpl();

    public int create(EmployeeDto dto) {
        return dao.create(dtoToEntity(dto));
    }

    public List<EmployeeDto> searchAll() {
        return entitiesToDtos(dao.findAll());
    }

    public Optional<EmployeeDto> searchByEmpNo(int empNo) {
        return entityToDto(dao.findByEmpNo(empNo));
    }

    public int update(EmployeeDto dto) {
        return dao.update(dtoToEntity(dto));
    }

    public int delete(EmployeeDto dto) {
        return dao.delete(dtoToEntity(dto));
    }

    public Employee dtoToEntity(EmployeeDto dto) {
        return new Employee.Builder()
                .empNo(dto.getEmpNo())
                .birthDate(dto.getBirthDate())
                .firstName(dto.getFirstName())
                .lastName(dto.getLastName())
                .gender(dto.getGender())
                .hireDate(dto.getHireDate())
                .build();
    }

    private List<EmployeeDto> entitiesToDtos(List<Employee> entities) {

        List<EmployeeDto> dtos = new ArrayList<>();

        for (Employee entity : entities) {
            dtos.add(entityToDto(entity));
        }

        return dtos;
    }

    private EmployeeDto entityToDto(Employee entity) {
        return new EmployeeDto.Builder()
                .empNo(entity.getEmpNo())
                .birthDate(entity.getBirthDate())
                .firstName(entity.getFirstName())
                .lastName(entity.getLastName())
                .gender(entity.getGender())
                .hireDate(entity.getHireDate())
                .build();
    }

    private Optional<EmployeeDto> entityToDto(Optional<Employee> optEntity) {
        return Optional.ofNullable(
                entityToDto(optEntity.get())
        );
    }
}