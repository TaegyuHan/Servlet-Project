package webapp.filter;

import org.json.JSONException;
import org.json.JSONObject;
import webapp.dto.EmployeeDto;
import webapp.entity.Gender;
import webapp.util.HttpMethod;
import webapp.util.InputStream;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.sql.Date;


public class EmployeeFilter implements Filter {

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

    @Override
    public void destroy() {
        Filter.super.destroy();
    }

    private void deleteJsonDataCheck(ServletRequest request) throws IOException {
        updateJsonDataCheck(request);
    }

    private void updateJsonDataCheck(ServletRequest request) throws IOException {

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

        createJsonDataCheck(request);

        EmployeeDto dto = (EmployeeDto) request.getAttribute("dto");

        dto.setEmpNo(empNo);
        request.setAttribute("dto", dto);
    }

    private void createJsonDataCheck(ServletRequest request) throws IOException {

        String jsonData = InputStream.getJsonToString(request);
        JSONObject json = new JSONObject(jsonData);

        /*
         * Column Name : birth_date
         * */
        String birthDateStr = json.optString("birth_date");
        if (birthDateStr == null || birthDateStr.isEmpty()) {
            throw new JSONException("\"birth_date\" key not found in JSON object or is empty");
        }

        Date birthDate;
        try {
            birthDate = Date.valueOf(birthDateStr);
        } catch (IllegalArgumentException e) {
            throw new JSONException("\"birth_date\" value cannot be converted to Date");
        }

        /*
         * 컬럼 이름 : first_name
         * */
        String firstName = json.optString("first_name");
        if (firstName == null || firstName.isEmpty()) {
            throw new JSONException("\"first_name\" key not found in JSON object or is empty");
        }

        /*
         * 컬럼 이름 : last_name
         * */
        String lastName = json.optString("last_name");
        if (lastName == null || lastName.isEmpty()) {
            throw new JSONException("\"last_name\" key not found in JSON object or is empty");
        }

        /*
         * 컬럼 이름 : last_name
         * */
        String genderStr = json.optString("gender");
        if (genderStr == null || genderStr.isEmpty()) {
            throw new JSONException("\"gender\" key not found in JSON object or is empty");
        }

        Gender gender;
        try {
            gender = Gender.valueOf(genderStr);
        } catch (IllegalArgumentException e) {
            throw new JSONException("\"gender\" value is not valid. Please enter M or F.");
        }

        /*
         * Column Name : hire_date
         * */
        String hireDateStr = json.optString("hire_date");
        Date hireDate = null;
        if (hireDateStr != null && !hireDateStr.isEmpty()) {
            hireDate = Date.valueOf(hireDateStr);
        }

        int CREATE_NUMBER = -1;
        EmployeeDto dto = new EmployeeDto.Builder()
                .empNo(CREATE_NUMBER)
                .birthDate(birthDate)
                .firstName(firstName)
                .lastName(lastName)
                .gender(gender)
                .hireDate(hireDate)
                .build();

        request.setAttribute("dto", dto);
    }
}