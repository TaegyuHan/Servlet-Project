package webapp.filter;

import org.json.JSONObject;
import webapp.dto.DeptEmpDto;
import webapp.dto.TitleDto;
import webapp.util.HttpMethod;
import webapp.util.InputStream;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;


public class TitleFilter implements Filter {

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

        TitleDto dto = new TitleDto.Builder()
                .empNo(json.optInt("emp_no"))
                .title(json.optString("title"))
                .fromDate(json.optString("from_date"))
                .build();

        request.setAttribute("dto", dto);
    }

    private void updateJsonDataCheck(ServletRequest request) throws IOException {
        createJsonDataCheck(request);
    }

    private void createJsonDataCheck(ServletRequest request) throws IOException {

        deleteJsonDataCheck(request);

        JSONObject json = InputStream.getJsonObject(request);

        DeptEmpDto dto = (DeptEmpDto) request.getAttribute("dto");
        dto.setToDate(json.optString("to_date"));
        request.setAttribute("dto", dto);
    }
}