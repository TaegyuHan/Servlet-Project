package webapp.filter;

import org.json.JSONException;
import org.json.JSONObject;
import webapp.entity.Salary;
import webapp.util.HttpMethod;
import webapp.util.InputStream;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.sql.Date;


public class SalaryFilter implements Filter {

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

    private void deleteJsonDataCheck(ServletRequest request) throws IOException {

        String jsonData = InputStream.getJsonToString(request);
        JSONObject json = new JSONObject(jsonData);

        /*
         * Column Name : emp_no
         * */
        String empNoStr = json.optString("emp_no");
        if (empNoStr == null || empNoStr.isEmpty()) {
            throw new JSONException("\"emp_no\" key not found in JSON object or is empty");
        }

        int empNo;
        try {
            empNo = Integer.parseInt(empNoStr);
        } catch (NumberFormatException e) {
            throw new JSONException("\"emp_no\" value is not valid");
        }

        /*
         * 컬럼 이름 : from_date
         */
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

        Salary dto = new Salary.Builder()
                .empNo(empNo)
                .fromDate(fromDate)
                .build();

        request.setAttribute("dto", dto);
    }

    private void updateJsonDataCheck(ServletRequest request) throws IOException {
        createJsonDataCheck(request);
    }

    private void createJsonDataCheck(ServletRequest request) throws IOException {

        String jsonData = InputStream.getJsonToString(request);
        JSONObject json = new JSONObject(jsonData);

        /*
         * 컬럼 이름 : salary
         * */
        int salary = json.optInt("salary");

        /*
         * 컬럼 이름 : from_date
         */
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

        Salary dto = (Salary) request.getAttribute("dto");
        dto.setSalary(salary);
        dto.setToDate(toDate);
        request.setAttribute("dto", dto);
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}