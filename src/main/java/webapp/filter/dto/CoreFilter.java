package webapp.filter.dto;

import webapp.util.HttpMethod;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;


public abstract class CoreFilter implements Filter {

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

    protected abstract void deleteJsonDataCheck(ServletRequest request) throws IOException;

    protected abstract void updateJsonDataCheck(ServletRequest request) throws IOException;

    protected abstract void createJsonDataCheck(ServletRequest request) throws IOException;
}
