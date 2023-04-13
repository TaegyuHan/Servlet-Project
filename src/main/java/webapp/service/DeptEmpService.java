package webapp.service;

import webapp.dao.DeptEmpDao;
import webapp.dao.DeptEmpDaoImpl;
import webapp.dto.DeptEmpDto;
import webapp.entity.DeptEmp;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


public class DeptEmpService {

    private final DeptEmpDao dao = new DeptEmpDaoImpl();

    /*  =====================  Create Start  =====================  */

    public Optional<DeptEmpDto> create(DeptEmpDto dto) {
        return entityToDto(dao.create(dtoToEntity(dto)));
    }

    /*  =====================  Create End  =====================  */

    /*  =====================  Read Start  =====================  */

    public List<DeptEmpDto> searchAll() {
        return entitiesToDtos(dao.findAll());
    }

    public Optional<DeptEmpDto> searchByEmpNoAndDeptNo(int empNo, String deptNo) {
        return entityToDto(dao.findByEmpNoAndDeptNo(empNo, deptNo));
    }

    public List<DeptEmpDto> searchByEmpNo(int empNo) {
        return entitiesToDtos(dao.findByEmpNo(empNo));
    }

    public List<DeptEmpDto> searchByDeptNo(String deptNo) {
        return entitiesToDtos(dao.findByDeptNo(deptNo));
    }

    /*  =====================  Read End  =====================  */

    /*  =====================  Update Start  =====================  */

    public Optional<DeptEmpDto> update(DeptEmpDto dto) {
        return entityToDto(dao.update(dtoToEntity(dto)));
    }

    /*  =====================  Update End  =====================  */

    /*  =====================  Delete Start  =====================  */

    public int delete(DeptEmpDto dto) {
        return dao.delete(dtoToEntity(dto));
    }

    /*  =====================  Delete End  =====================  */

    private DeptEmp dtoToEntity(DeptEmpDto dto) {
        return new DeptEmp.Builder()
                .empNo(dto.getEmpNo())
                .deptNo(dto.getDeptNo())
                .fromDate(dto.getFromDate())
                .toDate(dto.getToDate())
                .build();
    }

    private List<DeptEmpDto> entitiesToDtos(List<DeptEmp> entities) {

        List<DeptEmpDto> dtos = new ArrayList<>();

        for (DeptEmp entity : entities) {
            dtos.add(entityToDto(entity));
        }

        return dtos;
    }

    private DeptEmpDto entityToDto(DeptEmp entity) {
        return new DeptEmpDto.Builder()
                .empNo(entity.getEmpNo())
                .empNo(entity.getEmpNo())
                .deptNo(entity.getDeptNo())
                .fromDate(entity.getFromDate())
                .toDate(entity.getToDate())
                .build();
    }

    private Optional<DeptEmpDto> entityToDto(Optional<DeptEmp> optEntity) {
        return Optional.ofNullable(
                entityToDto(optEntity.get())
        );
    }
}