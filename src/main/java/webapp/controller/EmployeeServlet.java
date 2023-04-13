package webapp.controller;

import webapp.dto.EmployeeDto;
import webapp.service.EmployeeService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Optional;


@WebServlet("/v1/employees")
public class EmployeeServlet extends HttpServlet {

    private final EmployeeService service = new EmployeeService();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {

        EmployeeDto dto = (EmployeeDto) request.getAttribute("dto");

        dto = service.create(dto);

        response.setStatus(HttpServletResponse.SC_OK);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {

        Optional<String> params = Optional.ofNullable(request.getParameter("emp_no"));

        if (params.isEmpty()) { // 전부 검색
            List<EmployeeDto> dtos = service.searchAll(); // 1825 ms

        } else { // 1명 검색
            int empNo = Integer.parseInt(params.get());
            Optional<EmployeeDto> dto = service.searchByEmpNo(empNo);
        }

        response.setStatus(HttpServletResponse.SC_OK);
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws IOException {

        EmployeeDto dto = (EmployeeDto) request.getAttribute("dto");

        int updateCount = service.update(dto);

        response.setStatus(HttpServletResponse.SC_OK);
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws IOException {

        EmployeeDto dto = (EmployeeDto) request.getAttribute("dto");

        int updateCount = service.delete(dto);

        response.setStatus(HttpServletResponse.SC_OK);
    }
}