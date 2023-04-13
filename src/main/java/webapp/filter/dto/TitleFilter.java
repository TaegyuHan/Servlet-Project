package webapp.filter.dto;

import org.json.JSONObject;
import webapp.dto.DeptEmpDto;
import webapp.dto.TitleDto;
import webapp.util.InputStream;

import javax.servlet.*;
import java.io.IOException;


public class TitleFilter extends CoreFilter implements Filter {

    @Override
    protected void deleteJsonDataCheck(ServletRequest request) throws IOException {

        JSONObject json = InputStream.getJsonObject(request);

        TitleDto dto = new TitleDto.Builder()
                .empNo(json.optInt("emp_no"))
                .title(json.optString("title"))
                .fromDate(json.optString("from_date"))
                .build();

        request.setAttribute("dto", dto);
    }

    @Override
    protected void updateJsonDataCheck(ServletRequest request) throws IOException {
        createJsonDataCheck(request);
    }

    @Override
    protected void createJsonDataCheck(ServletRequest request) throws IOException {

        deleteJsonDataCheck(request);

        JSONObject json = InputStream.getJsonObject(request);

        DeptEmpDto dto = (DeptEmpDto) request.getAttribute("dto");
        dto.setToDate(json.optString("to_date"));
        request.setAttribute("dto", dto);
    }
}