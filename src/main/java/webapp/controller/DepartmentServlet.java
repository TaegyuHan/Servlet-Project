package webapp.controller;

import webapp.dto.DepartmentDto;
import webapp.service.DepartmentService;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Optional;


@WebServlet("/v1/department")
public class DepartmentServlet extends HttpServlet {

    private final DepartmentService service = new DepartmentService();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {

        DepartmentDto dto = (DepartmentDto) request.getAttribute("dto");

        Optional<DepartmentDto> optDto = service.create(dto);

        if (optDto.isEmpty()) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            return;
        }

        response.setStatus(HttpServletResponse.SC_OK);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {

        Optional<String> optDeptNo = Optional.ofNullable(request.getParameter("dept_no"));
        Optional<String> optDeptName = Optional.ofNullable(request.getParameter("dept_name"));

        if (optDeptNo.isEmpty() && optDeptName.isEmpty()) {
            List<DepartmentDto> dtos = service.searchAll();

        } else if (optDeptNo.isPresent()) {
            Optional<DepartmentDto> dto = service.searchByDeptNo(optDeptNo.get());

        } else if (optDeptName.isPresent()) {
            Optional<DepartmentDto> dto = service.searchByDeptName(optDeptName.get());
        }

        response.setStatus(HttpServletResponse.SC_OK);
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) {

        DepartmentDto dto = (DepartmentDto) request.getAttribute("dto");

        Optional<DepartmentDto> optDto = service.update(dto);

        if (optDto.isEmpty()) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            return;
        }

        response.setStatus(HttpServletResponse.SC_OK);
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) {

        DepartmentDto dto = (DepartmentDto) request.getAttribute("dto");

        int updateCount = service.delete(dto);

        if (updateCount == 0) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            return;
        }

        response.setStatus(HttpServletResponse.SC_OK);
    }
}