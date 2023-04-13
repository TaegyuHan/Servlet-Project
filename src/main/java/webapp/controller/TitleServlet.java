
package webapp.controller;

import webapp.dto.TitleDto;
import webapp.service.TitleService;


import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Optional;


@WebServlet("/v1/title")
public class TitleServlet extends HttpServlet {

    private final TitleService service = new TitleService();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {

        TitleDto dto = (TitleDto) request.getAttribute("dto");

        Optional<TitleDto> optDto = service.create(dto);

        if (optDto.isEmpty()) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            return;
        }

        response.setStatus(HttpServletResponse.SC_OK);

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {

        List<TitleDto> dtos = service.searchAll();

        response.setStatus(HttpServletResponse.SC_OK);
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) {

        TitleDto dto = (TitleDto) request.getAttribute("dto");

        Optional<TitleDto> optDto = service.update(dto);

        if (optDto.isEmpty()) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            return;
        }

        response.setStatus(HttpServletResponse.SC_OK);
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) {

        TitleDto dto = (TitleDto) request.getAttribute("dto");

        int updateCount = service.delete(dto);

        if (updateCount == 0) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            return;
        }

        response.setStatus(HttpServletResponse.SC_OK);
    }
}