
package webapp.controller;

import webapp.dto.TitleDto;
import webapp.service.TitleService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Optional;


@WebServlet("/v1/title")
public class TitleServlet extends HttpServlet {

    private final TitleService service = new TitleService();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Optional<TitleDto> optDto = TitleDto.reqeustToDto(request);

        if (optDto.isEmpty()) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        int updateCount = service.create(optDto.get());

        if (updateCount != 1) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            return;
        }

        response.setStatus(HttpServletResponse.SC_OK);
        response.getWriter().println("Data received successfully.");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        List<TitleDto> dtos = service.searchAll();

        response.setStatus(HttpServletResponse.SC_OK);
        response.getWriter().println("Data received successfully.");
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Optional<TitleDto> optDto = TitleDto.reqeustToDto(request);

        if (optDto.isEmpty()) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        int updateCount = service.update(optDto.get());

        response.setStatus(HttpServletResponse.SC_OK);
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Optional<TitleDto> optDto = TitleDto.reqeustToDto(request);

        if (optDto.isEmpty()) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        int updateCount = service.delete(optDto.get());

        response.setStatus(HttpServletResponse.SC_OK);
    }
}