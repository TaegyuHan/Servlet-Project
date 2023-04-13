package webapp.filter;

import org.json.JSONObject;
import webapp.dto.EmployeeDto;
import webapp.util.HttpMethod;
import webapp.util.InputStream;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;


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

        JSONObject json = InputStream.getJsonObject(request);

        EmployeeDto dto = new EmployeeDto.Builder()
                .empNo(json.optString("emp_no"))
                .build();

        request.setAttribute("dto", dto);
    }

    private void updateJsonDataCheck(ServletRequest request) throws IOException {

        createJsonDataCheck(request);

        JSONObject json = InputStream.getJsonObject(request);

        EmployeeDto dto = (EmployeeDto) request.getAttribute("dto");

        dto.setEmpNo(json.optString("emp_no"));
        request.setAttribute("dto", dto);
    }

    private void createJsonDataCheck(ServletRequest request) throws IOException {

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