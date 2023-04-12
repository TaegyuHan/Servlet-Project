package webapp.filter;

import org.json.JSONException;
import org.json.JSONObject;
import webapp.dto.DepartmentDto;
import webapp.util.HttpMethod;
import webapp.util.InputStream;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;


public class DepartmentFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        String httpMethod = request.getMethod();

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
        createJsonDataCheck(request);
    }

    private void updateJsonDataCheck(ServletRequest request) throws IOException {
        createJsonDataCheck(request);
    }

    private void createJsonDataCheck(ServletRequest request) throws IOException {

        String jsonData = InputStream.getJsonToString(request);
        JSONObject json = new JSONObject(jsonData);

        String deptNo = json.optString("dept_no");
        if (deptNo == null || deptNo.isEmpty()) {
            throw new JSONException("\"dept_no\" key not found in JSON object or is empty");
        }

        String deptName = json.optString("dept_name");
        if (deptName == null || deptName.isEmpty()) {
            throw new JSONException("\"dept_name\" key not found in JSON object or is empty");
        }

        DepartmentDto dto = new DepartmentDto.Builder()
                .deptNo(deptNo)
                .deptName(deptName)
                .build();

        request.setAttribute("dto", dto);
    }
}