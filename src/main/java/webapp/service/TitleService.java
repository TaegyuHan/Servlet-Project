package webapp.service;

import webapp.dao.TitleDao;
import webapp.dao.TitleDaoImpl;
import webapp.dto.TitleDto;
import webapp.entity.Title;

import java.util.ArrayList;
import java.util.List;


public class TitleService {

    private final TitleDao dao = new TitleDaoImpl();

    public int create(TitleDto dto) {
        return dao.create(dtoToEntity(dto));
    }

    public List<TitleDto> searchAll() {
        return entitiesToDtos(dao.findAll());
    }

    public int update(TitleDto dto) {
        return dao.update(dtoToEntity(dto));
    }

    public int delete(TitleDto dto) {
        return dao.delete(dtoToEntity(dto));
    }

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
}