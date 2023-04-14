package webapp.controller;

import org.junit.jupiter.api.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import webapp.dto.DeptEmpDto;
import webapp.service.DeptEmpService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import java.util.Optional;

import static org.mockito.Mockito.*;


@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class DepEmpServletTest {

    @Mock
    private DeptEmpService service;

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    @InjectMocks
    private DepEmpServlet servlet;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @Order(1)
    void doPost() {
        request = mock(HttpServletRequest.class);
        response = mock(HttpServletResponse.class);

        DeptEmpDto dto = new DeptEmpDto.Builder()
                .empNo(10003)
                .deptNo("d001")
                .fromDate("2023-04-15")
                .toDate("2023-04-20")
                .build();

        when(request.getAttribute("dto")).thenReturn(dto);
        when(service.create(dto)).thenReturn(Optional.of(dto));

        servlet.doPost(request, response);

        verify(response).setStatus(HttpServletResponse.SC_OK);
    }

    @Test
    @Order(2)
    void doGet_searchAll() {
        request = mock(HttpServletRequest.class);
        response = mock(HttpServletResponse.class);

        servlet.doGet(request, response);

        verify(response).setStatus(HttpServletResponse.SC_OK);
    }

    @Test
    @Order(3)
    void doGet_searchByEmpNoAndDeptNo() {
        request = mock(HttpServletRequest.class);
        response = mock(HttpServletResponse.class);

        when(request.getParameter("emp_no")).thenReturn("10003");
        when(request.getParameter("dept_no")).thenReturn("d001");

        DeptEmpDto dto = new DeptEmpDto.Builder()
                .empNo(10003)
                .deptNo("d001")
                .fromDate("2023-04-15")
                .toDate("2023-04-20")
                .build();

        when(service.searchByEmpNoAndDeptNo(10003, "d001")).thenReturn(Optional.of(dto));

        servlet.doGet(request, response);

        verify(response).setStatus(HttpServletResponse.SC_OK);
    }

    @Test
    @Order(4)
    void doGet_searchByEmpNo() {
        request = mock(HttpServletRequest.class);
        response = mock(HttpServletResponse.class);

        when(request.getParameter("emp_no")).thenReturn("10003");

        servlet.doGet(request, response);

        verify(response).setStatus(HttpServletResponse.SC_OK);
    }

    @Test
    @Order(5)
    void doGet_searchByDeptNo() {
        request = mock(HttpServletRequest.class);
        response = mock(HttpServletResponse.class);

        when(request.getParameter("dept_no")).thenReturn("d001");

        servlet.doGet(request, response);

        verify(response).setStatus(HttpServletResponse.SC_OK);
    }

    @Test
    @Order(6)
    void testDoPut() {
        request = mock(HttpServletRequest.class);
        response = mock(HttpServletResponse.class);

        DeptEmpDto dto = new DeptEmpDto.Builder()
                .empNo(10003)
                .deptNo("d001")
                .fromDate("2023-04-15")
                .toDate("2023-12-20")
                .build();

        when(request.getAttribute("dto")).thenReturn(dto);
        when(service.update(dto)).thenReturn(Optional.of(dto));

        servlet.doPut(request, response);

        verify(response).setStatus(HttpServletResponse.SC_OK);
    }

    @Test
    @Order(7)
    void testDoDelete() {
        request = mock(HttpServletRequest.class);
        response = mock(HttpServletResponse.class);

        DeptEmpDto dto = new DeptEmpDto.Builder()
                .empNo(10003)
                .deptNo("d001")
                .fromDate("2023-04-15")
                .toDate("2023-12-20")
                .build();

        when(request.getAttribute("dto")).thenReturn(dto);
        when(service.delete(dto)).thenReturn(1);

        servlet.doDelete(request, response);

        verify(response).setStatus(HttpServletResponse.SC_OK);
    }
}