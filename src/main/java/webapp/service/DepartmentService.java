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

    /*  =====================  Create Start  =====================  */

    public Optional<DepartmentDto> create(DepartmentDto dto) {
        return entityToDto(dao.create(dtoToEntity(dto)));
    }

    /*  =====================  Create End  =====================  */

    /*  =====================  Read Start  =====================  */

    public List<DepartmentDto> searchAll() {
        return entitiesToDtos(dao.findAll());
    }

    public Optional<DepartmentDto> searchByDeptNo(String deptNo) {
        return entityToDto(dao.findByDeptNo(deptNo));
    }

    public Optional<DepartmentDto> searchByDeptName(String deptName) {
        return entityToDto(dao.findByDeptName(deptName));
    }

    /*  =====================  Read End  =====================  */

    /*  =====================  Update Start  =====================  */

    public Optional<DepartmentDto> update(DepartmentDto dto) {

        Optional<Department> Optdto = dao.update(dtoToEntity(dto));

        return entityToDto(Optdto);
    }

    /*  =====================  Update End  =====================  */

    /*  =====================  Delete Start  =====================  */

    public int delete(DepartmentDto dto) {
        return dao.delete(dtoToEntity(dto));
    }

    /*  =====================  Delete End  =====================  */

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