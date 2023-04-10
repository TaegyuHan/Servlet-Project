package webapp.service;

import webapp.dao.TitleDao;
import webapp.dao.TitleDaoImpl;
import webapp.dto.TitleDto;

import java.util.List;


public class TitleService {

    private final TitleDao dao = new TitleDaoImpl();

    public int create(TitleDto titleDto) {
        return dao.create(titleDto.toEntity());
    }

    public List<TitleDto> searchAll() {
        return TitleDto.entitiesToDtos(dao.findAll());
    }

    public int update(TitleDto titleDto) {
        return dao.update(titleDto.toEntity());
    }

    public int delete(TitleDto titleDto) {
        return dao.delete(titleDto.toEntity());
    }
}