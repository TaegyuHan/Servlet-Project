package webapp.service;

import webapp.dao.DeptManagerDao;
import webapp.dao.DeptManagerDaoImpl;
import webapp.dto.DeptManagerDto;
import webapp.entity.DeptManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


public class DeptManagerService {

    private final DeptManagerDao dao = new DeptManagerDaoImpl();

    public int create(DeptManagerDto dto) {
        return dao.create(dtoToEntity(dto));
    }

    public List<DeptManagerDto> searchAll() {
        return entitiesToDtos(dao.findAll());
    }

    public Optional<DeptManagerDto> searchByEmpNoAndDeptNo(int empNo, String deptNo) {
        return entityToDto(dao.findByEmpNoAndDeptNo(empNo, deptNo));
    }

    public List<DeptManagerDto> searchByEmpNo(int empNo) {
        return entitiesToDtos(dao.findByEmpNo(empNo));
    }

    public List<DeptManagerDto> searchByDeptNo(String deptNo) {
        return entitiesToDtos(dao.findByDeptNo(deptNo));
    }

    public int update(DeptManagerDto dto) {
        return dao.update(dtoToEntity(dto));
    }

    public int delete(DeptManagerDto dto) {
        return dao.delete(dtoToEntity(dto));
    }

    private DeptManager dtoToEntity(DeptManagerDto dto) {
        return new DeptManager.Builder()
                .empNo(dto.getEmpNo())
                .deptNo(dto.getDeptNo())
                .fromDate(dto.getFromDate())
                .toDate(dto.getToDate())
                .build();
    }

    private List<DeptManagerDto> entitiesToDtos(List<DeptManager> entities) {

        List<DeptManagerDto> dtos = new ArrayList<>();

        for (DeptManager entity : entities) {
            dtos.add(entityToDto(entity));
        }

        return dtos;
    }

    private DeptManagerDto entityToDto(DeptManager entity) {
        return new DeptManagerDto.Builder()
                .empNo(entity.getEmpNo())
                .empNo(entity.getEmpNo())
                .deptNo(entity.getDeptNo())
                .fromDate(entity.getFromDate())
                .toDate(entity.getToDate())
                .build();
    }

    private Optional<DeptManagerDto> entityToDto(Optional<DeptManager> optEntity) {
        return Optional.ofNullable(
                entityToDto(optEntity.get())
        );
    }
}