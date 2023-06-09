package webapp.controller;

import org.junit.jupiter.api.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import webapp.dto.DepartmentDto;
import webapp.service.DepartmentService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.Optional;

import static org.mockito.Mockito.*;


@TestMethodOrder(MethodOrderer.OrderAnnotation.class) // 순서 대로 코딩
class DepartmentServletTest {

    @Mock
    private DepartmentService service;

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    @InjectMocks
    private DepartmentServlet servlet;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @Order(1)
    void doPost() {
        request = mock(HttpServletRequest.class);
        response = mock(HttpServletResponse.class);

        DepartmentDto dto = new DepartmentDto.Builder()
                .deptNo("d010")
                .deptName("new Marketing")
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
    void doGet_searchByDeptNo() {
        request = mock(HttpServletRequest.class);
        response = mock(HttpServletResponse.class);

        when(request.getParameter("dept_no")).thenReturn("d010");

        DepartmentDto dto = new DepartmentDto.Builder()
                .deptNo("d010")
                .deptName("new Marketing")
                .build();

        when(service.searchByDeptNo("d010")).thenReturn(Optional.of(dto));

        servlet.doGet(request, response);

        verify(response).setStatus(HttpServletResponse.SC_OK);
    }

    @Test
    @Order(4)
    void doGet_searchByDeptName() {
        request = mock(HttpServletRequest.class);
        response = mock(HttpServletResponse.class);

        when(request.getParameter("dept_name")).thenReturn("Marketing");

        DepartmentDto dto = new DepartmentDto.Builder()
                .deptNo("d010")
                .deptName("new Marketing")
                .build();

        when(service.searchByDeptName("new Marketing")).thenReturn(Optional.of(dto));

        servlet.doGet(request, response);

        verify(response).setStatus(HttpServletResponse.SC_OK);
    }

    @Test
    @Order(5)
    void testDoPut() {
        request = mock(HttpServletRequest.class);
        response = mock(HttpServletResponse.class);

        DepartmentDto dto = new DepartmentDto.Builder()
                .deptNo("d010")
                .deptName("update Marketing")
                .build();

        when(request.getAttribute("dto")).thenReturn(dto);
        when(service.update(dto)).thenReturn(Optional.of(dto));

        servlet.doPut(request, response);

        verify(response).setStatus(HttpServletResponse.SC_OK);
    }

    @Test
    @Order(6)
    void testDoDelete() {
        request = mock(HttpServletRequest.class);
        response = mock(HttpServletResponse.class);

        DepartmentDto dto = new DepartmentDto.Builder()
                .deptNo("d010")
                .deptName("update Marketing")
                .build();

        when(request.getAttribute("dto")).thenReturn(dto);
        when(service.delete(dto)).thenReturn(1);

        servlet.doDelete(request, response);

        verify(response).setStatus(HttpServletResponse.SC_OK);
    }
}