package webapp.filter.dto;

import org.json.JSONObject;
import webapp.dto.SalaryDto;
import webapp.util.InputStream;

import javax.servlet.*;
import java.io.IOException;


public class SalaryFilter extends CoreFilter implements Filter {

    @Override
    protected void deleteJsonDataCheck(ServletRequest request) throws IOException {

        JSONObject json = InputStream.getJsonObject(request);

        SalaryDto dto = new SalaryDto.Builder()
                .empNo(json.optString("emp_no"))
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

        SalaryDto dto = (SalaryDto) request.getAttribute("dto");
        dto.setSalary(json.optInt("salary"));
        dto.setToDate(json.optString("to_date"));
        request.setAttribute("dto", dto);
    }
}