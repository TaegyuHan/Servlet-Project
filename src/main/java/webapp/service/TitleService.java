package webapp.service;

import webapp.dao.TitleDao;
import webapp.dao.TitleDaoImpl;
import webapp.dto.TitleDto;
import webapp.entity.Title;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


public class TitleService {

    private final TitleDao dao = new TitleDaoImpl();

    /*  =====================  Create Start  =====================  */

    public Optional<TitleDto> create(TitleDto dto) {
        return entityToDto(dao.create(dtoToEntity(dto)));
    }

    /*  =====================  Create End  =====================  */

    /*  =====================  Read Start  =====================  */

    public List<TitleDto> searchAll() {
        return entitiesToDtos(dao.findAll());
    }

    public List<TitleDto> searchByEmpNo(int empNo) {
        return entitiesToDtos(dao.findByEmpNo(empNo));
    }

    public List<TitleDto> searchTitle(String title) {
        return entitiesToDtos(dao.findByTitle(title));
    }

    public List<TitleDto> searchByToDate(Date toDate) {
        return entitiesToDtos(dao.findByToDate(toDate));
    }

    public List<TitleDto> searchByFromDate(Date fromDate) {
        return entitiesToDtos(dao.findByFromDate(fromDate));
    }

    /*  =====================  Read End  =====================  */

    /*  =====================  Update Start  =====================  */

    public Optional<TitleDto> update(TitleDto dto) {
        return entityToDto(dao.update(dtoToEntity(dto)));
    }

    /*  =====================  Update End  =====================  */

    /*  =====================  Delete Start  =====================  */

    public int delete(TitleDto dto) {
        return dao.delete(dtoToEntity(dto));
    }

    /*  =====================  Delete End  =====================  */

    private Title dtoToEntity(TitleDto dto) {
        return new Title.Builder()
                .empNo(dto.getEmpNo())
                .title(dto.getTitle())
                .fromDate(dto.getFromDate())
                .toDate(dto.getToDate())
                .build();
    }

    private List<TitleDto> entitiesToDtos(List<Title> entities) {

        List<TitleDto> dtos = new ArrayList<>();

        for (Title entity : entities) {
            dtos.add(entityToDto(entity));
        }

        return dtos;
    }

    private TitleDto entityToDto(Title entity) {
        return new TitleDto.Builder()
                .empNo(entity.getEmpNo())
                .title(entity.getTitle())
                .fromDate(entity.getFromDate())
                .toDate(entity.getToDate())
                .build();
    }

    private Optional<TitleDto> entityToDto(Optional<Title> optEntity) {
        return Optional.ofNullable(
                entityToDto(optEntity.get())
        );
    }
}