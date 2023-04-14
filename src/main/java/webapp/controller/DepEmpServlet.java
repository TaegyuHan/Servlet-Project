package webapp.controller;

import webapp.dto.DeptEmpDto;
import webapp.service.DeptEmpService;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Optional;


@WebServlet("/v1/dept-emp")
public class DepEmpServlet extends HttpServlet {

    private final DeptEmpService service = new DeptEmpService();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {

        DeptEmpDto dto = (DeptEmpDto) request.getAttribute("dto");

        Optional<DeptEmpDto> optDto = service.create(dto);

        if (optDto.isEmpty()) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            return;
        }

        response.setStatus(HttpServletResponse.SC_OK);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {

        Optional<String> optEmpNo = Optional.ofNullable(request.getParameter("emp_no"));
        Optional<String> optDeptNo = Optional.ofNullable(request.getParameter("dept_no"));

        if (optEmpNo.isEmpty() && optDeptNo.isEmpty()) {
            List<DeptEmpDto> dtos = service.searchAll();

        } else if (optEmpNo.isPresent() && optDeptNo.isPresent()) {
            int empNo = Integer.parseInt(optEmpNo.get());
            Optional<DeptEmpDto> dto = service.searchByEmpNoAndDeptNo(empNo, optDeptNo.get());

        } else if (optEmpNo.isPresent()) {
            int empNo = Integer.parseInt(optEmpNo.get());
            List<DeptEmpDto> dtos = service.searchByEmpNo(empNo);

        } else if (optDeptNo.isPresent()) {
            List<DeptEmpDto> dtos = service.searchByDeptNo(optDeptNo.get());
        }

        response.setStatus(HttpServletResponse.SC_OK);
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) {

        DeptEmpDto dto = (DeptEmpDto) request.getAttribute("dto");

        Optional<DeptEmpDto> optDto = service.update(dto);

        if (optDto.isEmpty()) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            return;
        }

        response.setStatus(HttpServletResponse.SC_OK);
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) {

        DeptEmpDto dto = (DeptEmpDto) request.getAttribute("dto");

        int updateCount = service.delete(dto);

        if (updateCount == 0) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            return;
        }

        response.setStatus(HttpServletResponse.SC_OK);
    }
}