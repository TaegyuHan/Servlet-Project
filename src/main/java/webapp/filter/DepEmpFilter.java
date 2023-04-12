package webapp.filter;

import org.json.JSONException;
import org.json.JSONObject;
import webapp.dto.DeptEmpDto;
import webapp.util.HttpMethod;
import webapp.util.InputStream;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.sql.Date;


public class DepEmpFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {

        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        String httpMethod = httpServletRequest.getMethod();

        switch (httpMethod) {
            case HttpMethod.GET:
                break;
            case HttpMethod.POST:
                createJsonDataCheck(servletRequest);
                break;
            case HttpMethod.PUT:
                updateJsonDataCheck(servletRequest);
                break;
            case HttpMethod.DELETE:
                deleteJsonDataCheck(servletRequest);
                break;
        }

        filterChain.doFilter(servletRequest, servletResponse);
    }

    private void createJsonDataCheck(ServletRequest request) throws IOException {

        deleteJsonDataCheck(request);

        String jsonData = InputStream.getJsonToString(request);
        JSONObject json = new JSONObject(jsonData);

        /*
         * 컬럼 이름 : from_date
         * */
        String fromDateStr = json.optString("from_date");
        if (fromDateStr == null || fromDateStr.isEmpty()) {
            throw new JSONException("\"from_date\" key not found in JSON object or is empty");
        }

        Date fromDate;
        try {
            fromDate = Date.valueOf(fromDateStr);
        } catch (IllegalArgumentException e) {
            throw new JSONException("\"from_date\" value cannot be converted to Date");
        }

        /*
         * 컬럼 이름 : to_date
         * */
        String toDateStr = json.optString("to_date");
        if (toDateStr == null || toDateStr.isEmpty()) {
            throw new JSONException("\"to_date\" key not found in JSON object or is empty");
        }

        Date toDate;
        try {
            toDate = Date.valueOf(toDateStr);
        } catch (IllegalArgumentException e) {
            throw new JSONException("\"to_date\" value cannot be converted to Date");
        }

        DeptEmpDto dto = (DeptEmpDto) request.getAttribute("dto");
        dto.setFromDate(fromDate);
        dto.setToDate(toDate);
    }

    private void updateJsonDataCheck(ServletRequest request) throws IOException {
        createJsonDataCheck(request);
    }

    private void deleteJsonDataCheck(ServletRequest request) throws IOException {

        String jsonData = InputStream.getJsonToString(request);
        JSONObject json = new JSONObject(jsonData);

        /*
         * 컬럼 이름 : emp_no
         * */
        int empNo = json.optInt("emp_no");
        if (empNo == 0) {
            throw new JSONException("\"emp_no\" key not found in JSON object or is empty");
        }

        /*
         * 컬럼 이름 : dept_no
         * */
        String deptNo = json.optString("dept_no");
        if (deptNo == null || deptNo.isEmpty()) {
            throw new JSONException("\"dept_no\" key not found in JSON object or is empty");
        }

        DeptEmpDto dto = new DeptEmpDto.Builder()
                .empNo(empNo)
                .deptNo(deptNo)
                .build();

        request.setAttribute("dto", dto);
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}