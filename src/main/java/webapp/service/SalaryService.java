package webapp.service;

import webapp.dao.SalaryDao;
import webapp.dao.SalaryDaoImpl;
import webapp.dto.SalaryDto;
import webapp.entity.Salary;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


public class SalaryService {

    private final SalaryDao dao = new SalaryDaoImpl();

    public int create(SalaryDto dto) {
        return dao.create(dtoToEntity(dto));
    }

    public List<SalaryDto> searchAll() {
        return entitiesToDtos(dao.findAll());
    }

    public Optional<SalaryDto> searchByEmpNoAndFromDate(int empNo, Date fromDate) {
        return entityToDto(dao.findByEmpNoAndFromDate(empNo, fromDate));
    }

    public List<SalaryDto> searchByEmpNo(int empNo) {
        return entitiesToDtos(dao.findByEmpNo(empNo));
    }

    public List<SalaryDto> searchByFromDate(Date fromDate) {
        return entitiesToDtos(dao.findByFromDate(fromDate));
    }

    public int update(SalaryDto dto) {
        return dao.update(dtoToEntity(dto));
    }

    public int delete(SalaryDto dto) {
        return dao.delete(dtoToEntity(dto));
    }

    private Salary dtoToEntity(SalaryDto dto) {
        return new Salary.Builder()
                .empNo(dto.getEmpNo())
                .salary(dto.getSalary())
                .fromDate(dto.getFromDate())
                .toDate(dto.getToDate())
                .build();
    }

    private List<SalaryDto> entitiesToDtos(List<Salary> entities) {

        List<SalaryDto> dtos = new ArrayList<>();

        for (Salary entity : entities) {
            dtos.add(entityToDto(entity));
        }

        return dtos;
    }

    private SalaryDto entityToDto(Salary entity) {
        return new SalaryDto.Builder()
                .empNo(entity.getEmpNo())
                .salary(entity.getSalary())
                .fromDate(entity.getFromDate())
                .toDate(entity.getToDate())
                .build();
    }

    private Optional<SalaryDto> entityToDto(Optional<Salary> optEntity) {
        return Optional.ofNullable(
                entityToDto(optEntity.get())
        );
    }
}