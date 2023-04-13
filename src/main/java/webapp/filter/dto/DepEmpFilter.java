package webapp.filter.dto;

import org.json.JSONObject;
import webapp.dto.DeptEmpDto;
import webapp.util.InputStream;

import javax.servlet.*;
import java.io.IOException;


public class DepEmpFilter extends CoreFilter implements Filter {

    @Override
    protected void createJsonDataCheck(ServletRequest request) throws IOException {

        deleteJsonDataCheck(request);

        JSONObject json = InputStream.getJsonObject(request);

        DeptEmpDto dto = (DeptEmpDto) request.getAttribute("dto");
        dto.setFromDate(json.optString("from_date"));
        dto.setToDate(json.optString("to_date"));
    }

    @Override
    protected void updateJsonDataCheck(ServletRequest request) throws IOException {
        createJsonDataCheck(request);
    }

    @Override
    protected void deleteJsonDataCheck(ServletRequest request) throws IOException {

        JSONObject json = InputStream.getJsonObject(request);

        DeptEmpDto dto = new DeptEmpDto.Builder()
                .empNo(json.optInt("emp_no"))
                .deptNo(json.optString("dept_no"))
                .build();

        request.setAttribute("dto", dto);
    }

}