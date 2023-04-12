package webapp.service;

import webapp.dao.DepartmentDao;
import webapp.dao.DepartmentDaoImpl;
import webapp.dto.DepartmentDto;
import webapp.entity.Department;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


public class DepartmentService {

    private final DepartmentDao dao = new DepartmentDaoImpl();

    public int create(DepartmentDto dto) {
        return dao.create(dtoToEntity(dto));
    }

    public List<DepartmentDto> searchAll() {
        return entitiesToDtos(dao.findAll());
    }

    public Optional<DepartmentDto> searchByDeptNo(String deptNo) {
        return entityToDto(dao.findByDeptNo(deptNo));
    }

    public Optional<DepartmentDto> searchByDeptName(String deptName) {
        return entityToDto(dao.findByDeptName(deptName));
    }

    public int update(DepartmentDto dto) {
        return dao.update(dtoToEntity(dto));
    }

    public int delete(DepartmentDto dto) {
        return dao.delete(dtoToEntity(dto));
    }

    private Department dtoToEntity(DepartmentDto dto) {
        return new Department.Builder()
                .deptNo(dto.getDeptNo())
                .deptName(dto.getDeptName())
                .build();
    }

    private List<DepartmentDto> entitiesToDtos(List<Department> entities) {

        List<DepartmentDto> dtos = new ArrayList<>();

        for (Department entity : entities) {
            dtos.add(entityToDto(entity));
        }

        return dtos;
    }

    private DepartmentDto entityToDto(Department entity) {
        return new DepartmentDto.Builder()
                .deptNo(entity.getDeptNo())
                .deptName(entity.getDeptName())
                .build();
    }

    private Optional<DepartmentDto> entityToDto(Optional<Department> optEntity) {
        return Optional.ofNullable(
                entityToDto(optEntity.get())
        );
    }
}