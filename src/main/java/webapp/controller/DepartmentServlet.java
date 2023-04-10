package webapp.controller;

import webapp.dto.DepartmentDto;
import webapp.service.DepartmentService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;


@WebServlet("/v1/department")
public class DepartmentServlet extends HttpServlet {

    private final DepartmentService service = new DepartmentService();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Optional<DepartmentDto> optDto = DepartmentDto.reqeustToDto(request);

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

        Optional<String> optDeptNo = Optional.ofNullable(request.getParameter("dept_no"));
        Optional<String> optDeptName = Optional.ofNullable(request.getParameter("dept_name"));

        if (optDeptNo.isEmpty() && optDeptName.isEmpty()) {

            List<DepartmentDto> dtos = service.searchAll();

            System.out.println(Arrays.toString(dtos.toArray()));

        } else if (optDeptNo.isPresent()) {
            Optional<DepartmentDto> dto = service.searchByDeptNo(optDeptNo.get());

            System.out.println(dto.get());

        } else if (optDeptName.isPresent()) {
            Optional<DepartmentDto> dto = service.searchByDeptName(optDeptName.get());

            System.out.println(dto.get());
        }
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Optional<DepartmentDto> optDto = DepartmentDto.reqeustToDto(request);

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

        Optional<DepartmentDto> optDto = DepartmentDto.reqeustToDto(request);

        if (optDto.isEmpty()) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        int updateCount = service.delete(optDto.get());

        response.setStatus(HttpServletResponse.SC_OK);
    }
}