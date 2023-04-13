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

    /*  =====================  Create Start  =====================  */

    public Optional<EmployeeDto> create(EmployeeDto dto) {
        return entityToDto(dao.create(dtoToEntity(dto)));
    }

    /*  =====================  Create End  =====================  */

    /*  =====================  Read Start  =====================  */

    public List<EmployeeDto> searchAll() {
        return entitiesToDtos(dao.findAll());
    }

    public Optional<EmployeeDto> searchByEmpNo(int empNo) {
        return entityToDto(dao.findByEmpNo(empNo));
    }

    /*  =====================  Read End  =====================  */

    /*  =====================  Update Start  =====================  */

    public Optional<EmployeeDto> update(EmployeeDto dto) {
        return entityToDto(dao.update(dtoToEntity(dto)));
    }

    /*  =====================  Update End  =====================  */

    /*  =====================  Delete Start  =====================  */

    public int delete(EmployeeDto dto) {
        return dao.delete(dtoToEntity(dto));
    }

    /*  =====================  Delete End  =====================  */

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