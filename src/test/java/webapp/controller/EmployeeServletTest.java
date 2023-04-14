package webapp.controller;

import org.junit.jupiter.api.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import webapp.dto.EmployeeDto;
import webapp.service.EmployeeService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.sql.Date;
import java.util.Optional;

import static org.mockito.Mockito.*;


@TestMethodOrder(MethodOrderer.OrderAnnotation.class) // 순서 대로 코
@TestInstance(TestInstance.Lifecycle.PER_CLASS) // 기존에 실행한 Test의 저장 변수 다른 곳에서도 실행// 딩
class EmployeeServletTest {

    @Mock
    private EmployeeService service;

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    @InjectMocks
    private EmployeeServlet servlet;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @Order(1)
    void doPost() {
        request = mock(HttpServletRequest.class);
        response = mock(HttpServletResponse.class);

        EmployeeDto dto = new EmployeeDto.Builder()
                .birthDate("2023-04-15")
                .firstName("Alice")
                .lastName("Lee")
                .gender("F")
                .hireDate("2023-04-15")
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
    void doGet_searchByEmpNo() {
        request = mock(HttpServletRequest.class);
        response = mock(HttpServletResponse.class);

        int empNo = 10_002;

        EmployeeDto dto = new EmployeeDto.Builder()
                .empNo(empNo)
                .birthDate(Date.valueOf("1952-09-02"))
                .firstName("Georgi")
                .lastName("Facello")
                .gender("M")
                .hireDate(Date.valueOf("1986-06-26"))
                .build();

        when(request.getParameter("emp_no")).thenReturn(String.valueOf(empNo));
        when(service.searchByEmpNo(empNo)).thenReturn(Optional.of(dto));

        servlet.doGet(request, response);

        verify(response).setStatus(HttpServletResponse.SC_OK);
    }

    @Test
    @Order(4)
    void doPut() throws Exception {
        request = mock(HttpServletRequest.class);
        response = mock(HttpServletResponse.class);

        int empNo = 10_002;

        EmployeeDto dto = new EmployeeDto.Builder()
                .empNo(empNo)
                .birthDate(Date.valueOf("1952-09-02"))
                .firstName("Alice")
                .lastName("Lee")
                .gender("F")
                .hireDate("2023-04-15")
                .build();

        when(request.getAttribute("dto")).thenReturn(dto);
        when(service.update(dto)).thenReturn(Optional.of(dto));

        servlet.doPut(request, response);

        verify(response).setStatus(HttpServletResponse.SC_OK);
    }

    @Test
    @Order(5)
    void doDelete() throws Exception {
        request = mock(HttpServletRequest.class);
        response = mock(HttpServletResponse.class);

        int empNo = 10_002;

        EmployeeDto dto = new EmployeeDto.Builder()
                .empNo(empNo)
                .firstName("Alice")
                .lastName("Lee")
                .gender("F")
                .hireDate("2023-04-15")
                .build();

        when(request.getAttribute("dto")).thenReturn(dto);
        when(service.delete(dto)).thenReturn(empNo);

        servlet.doDelete(request, response);

        service.create(dto);

        verify(response).setStatus(HttpServletResponse.SC_OK);
    }
}