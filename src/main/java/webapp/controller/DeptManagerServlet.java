package webapp.controller;


import webapp.dto.DeptManagerDto;
import webapp.service.DeptManagerService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Optional;


@WebServlet("/v1/dept-manager")
public class DeptManagerServlet extends HttpServlet {

    private final DeptManagerService service = new DeptManagerService();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Optional<DeptManagerDto> optDto = DeptManagerDto.reqeustToDto(request);

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

        Optional<String> optEmpNo = Optional.ofNullable(request.getParameter("emp_no"));
        Optional<String> optDeptNo = Optional.ofNullable(request.getParameter("dept_no"));

        if (optEmpNo.isEmpty() && optDeptNo.isEmpty()) {

            List<DeptManagerDto> dtos = service.searchAll();

            System.out.println(dtos.toString());

        } else if (optEmpNo.isPresent() && optDeptNo.isPresent()) {
            int empNo = Integer.parseInt(optEmpNo.get());
            Optional<DeptManagerDto> dto = service.searchByEmpNoAndDeptNo(empNo, optDeptNo.get());

            System.out.println(dto.get());

        } else if (optEmpNo.isPresent()) {
            int empNo = Integer.parseInt(optEmpNo.get());
            List<DeptManagerDto> dtos = service.searchByEmpNo(empNo);

            System.out.println(dtos.toString());

        } else if (optDeptNo.isPresent()) {
            List<DeptManagerDto> dtos = service.searchByDeptNo(optDeptNo.get());

            System.out.println(dtos.toString());
        }
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Optional<DeptManagerDto> optDto = DeptManagerDto.reqeustToDto(request);

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

        Optional<DeptManagerDto> optDto = DeptManagerDto.reqeustToDto(request);

        if (optDto.isEmpty()) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        int updateCount = service.delete(optDto.get());

        response.setStatus(HttpServletResponse.SC_OK);
    }
}
