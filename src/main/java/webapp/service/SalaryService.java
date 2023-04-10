package webapp.service;

import webapp.dao.SalaryDao;
import webapp.dao.SalaryDaoImpl;
import webapp.dto.SalaryDto;

import java.sql.Date;
import java.util.List;
import java.util.Optional;


public class SalaryService {

    private final SalaryDao dao = new SalaryDaoImpl();

    public int create(SalaryDto salaryDto) {
        return dao.create(salaryDto.toEntity());
    }

    public List<SalaryDto> searchAll() {
        return SalaryDto.entitiesToDtos(dao.findAll());
    }

    public Optional<SalaryDto> searchByEmpNoAndFromDate(int empNo, Date fromDate) {
        return SalaryDto.entityToDto(dao.findByEmpNoAndFromDate(empNo, fromDate));
    }

    public List<SalaryDto> searchByEmpNo(int empNo) {
        return SalaryDto.entitiesToDtos(dao.findByEmpNo(empNo));
    }

    public List<SalaryDto> searchByFromDate(Date fromDate) {
        return SalaryDto.entitiesToDtos(dao.findByFromDate(fromDate));
    }

    public int update(SalaryDto salaryDto) {
        return dao.update(salaryDto.toEntity());
    }

    public int delete(SalaryDto salaryDto) {
        return dao.delete(salaryDto.toEntity());
    }
}