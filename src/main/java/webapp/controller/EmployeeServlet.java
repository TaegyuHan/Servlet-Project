package webapp.controller;

import webapp.dto.EmployeeDto;
import webapp.service.EmployeeService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;


@WebServlet("/v1/employees")
public class EmployeeServlet extends HttpServlet {

    private final EmployeeService service = new EmployeeService();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Optional<EmployeeDto> optDto = EmployeeDto.reqeustToDto(request);

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

        Optional<String> params = Optional.ofNullable(request.getParameter("emp_no"));

        if (params.isEmpty()) { // 전부 검색
            List<EmployeeDto> dtos = service.searchAll(); // 1825 ms

            System.out.println(Arrays.toString(dtos.toArray()));

        } else { // 1명 검색
            int empNo = Integer.parseInt(params.get());
            Optional<EmployeeDto> dto = service.searchByEmpNo(empNo);

            System.out.println(dto.toString());
        }

        response.setStatus(HttpServletResponse.SC_OK);
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws IOException {

        Optional<EmployeeDto> optDto = EmployeeDto.reqeustToDto(request);

        if (optDto.isEmpty()) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        int updateCount = service.update(optDto.get());

        response.setStatus(HttpServletResponse.SC_OK);
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws IOException {

        Optional<EmployeeDto> optDto = EmployeeDto.reqeustToDto(request);

        if (optDto.isEmpty()) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        int updateCount = service.delete(optDto.get());

        response.setStatus(HttpServletResponse.SC_OK);
    }
}