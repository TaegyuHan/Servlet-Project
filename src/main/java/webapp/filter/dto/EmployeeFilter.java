package webapp.filter.dto;

import org.json.JSONObject;
import webapp.dto.EmployeeDto;
import webapp.util.InputStream;

import javax.servlet.*;
import java.io.IOException;


public class EmployeeFilter extends CoreFilter implements Filter {

    @Override
    protected void deleteJsonDataCheck(ServletRequest request) throws IOException {

        JSONObject json = InputStream.getJsonObject(request);

        EmployeeDto dto = new EmployeeDto.Builder()
                .empNo(json.optString("emp_no"))
                .build();

        request.setAttribute("dto", dto);
    }

    @Override
    protected void updateJsonDataCheck(ServletRequest request) throws IOException {

        createJsonDataCheck(request);

        JSONObject json = InputStream.getJsonObject(request);

        EmployeeDto dto = (EmployeeDto) request.getAttribute("dto");

        dto.setEmpNo(json.optString("emp_no"));
        request.setAttribute("dto", dto);
    }

    @Override
    protected void createJsonDataCheck(ServletRequest request) throws IOException {

        JSONObject json = InputStream.getJsonObject(request);

        int CREATE_NUMBER = -1;
        EmployeeDto dto = new EmployeeDto.Builder()
                .empNo(CREATE_NUMBER)
                .birthDate(json.optString("birth_date"))
                .firstName(json.optString("first_name"))
                .lastName(json.optString("last_name"))
                .gender(json.optString("gender"))
                .hireDate(json.optString("hire_date"))
                .build();

        request.setAttribute("dto", dto);
    }
}