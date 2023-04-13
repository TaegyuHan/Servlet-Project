
package webapp.controller;

import webapp.dto.SalaryDto;
import webapp.service.SalaryService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;


@WebServlet("/v1/salary")
public class SalaryServlet extends HttpServlet {

    private final SalaryService service = new SalaryService();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {

        SalaryDto dto = (SalaryDto) request.getAttribute("dto");

        int updateCount = service.create(dto);

        if (updateCount != 1) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            return;
        }

        response.setStatus(HttpServletResponse.SC_OK);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {

        Optional<String> optEmpNo = Optional.ofNullable(request.getParameter("dept_no"));
        Optional<String> optFromDate = Optional.ofNullable(request.getParameter("from_date"));

        if (optEmpNo.isEmpty() && optFromDate.isEmpty()) {

            List<SalaryDto> dtos = service.searchAll();

            System.out.println(Arrays.toString(dtos.toArray()));

        } else if (optEmpNo.isPresent() && optFromDate.isPresent()) {
            int empNo = Integer.parseInt(optEmpNo.get());
            Date fromDate = Date.valueOf(optFromDate.get());
            Optional<SalaryDto> dtos = service.searchByEmpNoAndFromDate(empNo, fromDate);

        } else if (optEmpNo.isPresent()) {
            int empNo = Integer.parseInt(optEmpNo.get());
            List<SalaryDto> dto = service.searchByEmpNo(empNo);

            System.out.println(dto.toString());

        } else if (optFromDate.isPresent()) {
            Date fromDate = Date.valueOf(optFromDate.get());
            List<SalaryDto> dto = service.searchByFromDate(fromDate);

            System.out.println(dto.toString());
        }
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) {

        SalaryDto dto = (SalaryDto) request.getAttribute("dto");

        int updateCount = service.update(dto);

        response.setStatus(HttpServletResponse.SC_OK);
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) {

        SalaryDto dto = (SalaryDto) request.getAttribute("dto");

        int updateCount = service.delete(dto);

        response.setStatus(HttpServletResponse.SC_OK);
    }
}