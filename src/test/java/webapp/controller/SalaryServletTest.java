package webapp.controller;

import org.junit.jupiter.api.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import webapp.dto.SalaryDto;
import webapp.service.SalaryService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.sql.Date;
import java.util.Optional;

import static org.mockito.Mockito.*;


@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class SalaryServletTest {

    @Mock
    private SalaryService service;

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    @InjectMocks
    private SalaryServlet servlet;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this); // 가짜(Mock) 객체들을 초기화합니다.
    }

    @Test
    @Order(1)
    void doPost() {
        request = mock(HttpServletRequest.class);
        response = mock(HttpServletResponse.class);

        SalaryDto dto = new SalaryDto.Builder()
                .empNo(111_692)
                .salary(50_000)
                .fromDate("2023-04-14")
                .toDate("2023-04-14")
                .build();

        when(request.getAttribute("dto")).thenReturn(dto);
        when(service.create(dto)).thenReturn(Optional.of(dto));

        servlet.doPost(request, response);

        verify(response).setStatus(HttpServletResponse.SC_OK);
    }

    @Test
    @Order(2)
    void doGet_searchByEmpNo() {
        request = mock(HttpServletRequest.class);
        response = mock(HttpServletResponse.class);

        int empNo = 111_692;

        when(request.getParameter("dept_no")).thenReturn(String.valueOf(empNo));

        servlet.doGet(request, response);

        verify(response).setStatus(HttpServletResponse.SC_OK);
    }

    @Test
    @Order(3)
    void doGet_searchByFromDate() {
        request = mock(HttpServletRequest.class);
        response = mock(HttpServletResponse.class);

        Date fromDate = Date.valueOf("2023-04-14");

        when(request.getParameter("from_date")).thenReturn(fromDate.toString());

        servlet.doGet(request, response);

        verify(response).setStatus(HttpServletResponse.SC_OK);
    }

    @Test
    @Order(4)
    void doPut() {
        request = mock(HttpServletRequest.class);
        response = mock(HttpServletResponse.class);

        SalaryDto dto = new SalaryDto.Builder()
                .empNo(111_692)
                .salary(60_000) // 10_000 증가
                .fromDate("2023-04-14")
                .toDate("2023-04-15")
                .build();

        when(request.getAttribute("dto")).thenReturn(dto);
        when(service.update(dto)).thenReturn(Optional.of(dto));

        servlet.doPut(request, response);

        verify(response).setStatus(HttpServletResponse.SC_OK);
    }

    @Test
    @Order(5)
    void doDelete() {
        request = mock(HttpServletRequest.class);
        response = mock(HttpServletResponse.class);

        SalaryDto dto = new SalaryDto.Builder()
                .empNo(111_692)
                .salary(60_000)
                .fromDate("2023-04-14")
                .toDate("2023-04-15")
                .build();

        when(request.getAttribute("dto")).thenReturn(dto);
        when(service.delete(dto)).thenReturn(1);

        servlet.doDelete(request, response);

        verify(response).setStatus(HttpServletResponse.SC_OK);
    }
}