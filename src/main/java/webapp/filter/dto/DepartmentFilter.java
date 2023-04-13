package webapp.filter.dto;

import org.json.JSONObject;
import webapp.dto.DepartmentDto;
import webapp.util.InputStream;

import javax.servlet.*;
import java.io.IOException;


public class DepartmentFilter extends CoreFilter implements Filter {

    @Override
    protected void deleteJsonDataCheck(ServletRequest request) throws IOException {
        createJsonDataCheck(request);
    }

    @Override
    protected void updateJsonDataCheck(ServletRequest request) throws IOException {
        createJsonDataCheck(request);
    }
    @Override
    protected void createJsonDataCheck(ServletRequest request) throws IOException {

        JSONObject json = InputStream.getJsonObject(request);

        DepartmentDto dto = new DepartmentDto.Builder()
                .deptNo(json.optString("dept_no"))
                .deptName(json.optString("dept_name"))
                .build();

        request.setAttribute("dto", dto);
    }
}