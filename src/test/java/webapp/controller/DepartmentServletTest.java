package webapp.controller;

import org.junit.jupiter.api.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import webapp.dao.DepartmentDao;
import webapp.dto.DepartmentDto;
import webapp.service.DepartmentService;
import webapp.util.DelegatingServletInputStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;


@TestMethodOrder(MethodOrderer.OrderAnnotation.class) // 순서 대로 코딩
class DepartmentServletTest {

    private final String deptNo = "d010";

    private final String deptName = "Test1";

    private final String json = "{\"dept_no\": \"" + deptNo + "\", \"dept_name\": \"" + deptName + "\"}";

    @Mock
    private DepartmentDao dao;

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
        MockitoAnnotations.openMocks(this); // 가짜(Mock) 객체들을 초기화합니다.
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    @Order(1)
    @DisplayName("Department 생성")
    void doCreateAndDelete() throws IOException, ServletException {

        // given
        ByteArrayInputStream inputStream = new ByteArrayInputStream(json.getBytes());
        DelegatingServletInputStream servletInputStream = new DelegatingServletInputStream(inputStream);

        DepartmentDto dto = new DepartmentDto.Builder()
                .deptNo(deptNo)
                .deptName(deptName)
                .build();

        // when
        when(request.getInputStream()).thenReturn(servletInputStream);

        when(service.create(dto)).thenReturn(1);

        servlet.doPost(request, response);

        // then
        verify(response).setStatus(HttpServletResponse.SC_OK);
    }

    @Test
    @Order(2)
    @DisplayName("Department 전체 검색")
    void doGet() throws ServletException, IOException {
        // 데이터 가져오기
        List<DepartmentDto> departmentDtoList = DepartmentDto.entitiesToDtos(dao.findAll());

        when(service.searchAll()).thenReturn(departmentDtoList);

        // when
        servlet.doGet(request, response);

        // then
        verify(response).setStatus(HttpServletResponse.SC_OK);
    }

    @Test
    @Order(3)
    @DisplayName("Department dept_no로 검색")
    void testSearchByDeptNo() throws IOException, ServletException {

        // given
        DepartmentDto dto = new DepartmentDto.Builder()
                .deptNo(deptNo)
                .deptName(deptName)
                .build();

        when(service.searchByDeptNo(deptNo)).thenReturn(Optional.of(dto));

        when(request.getParameter("dept_no")).thenReturn(deptNo);

        // when
        servlet.doGet(request, response);

        // then
        verify(response).setStatus(HttpServletResponse.SC_OK);
    }

    @Test
    @Order(4)
    @DisplayName("Department dept_name으로 검색")
    void testSearchByDeptName() throws IOException, ServletException {

        // given
        DepartmentDto dto =
                new DepartmentDto.Builder()
                        .deptNo(deptNo)
                        .deptName(deptName)
                        .build();

        when(service.searchByDeptName(deptName)).thenReturn(Optional.of(dto));

        when(request.getParameter("dept_name")).thenReturn(deptName);

        // when
        servlet.doGet(request, response);

        // then
        verify(response).setStatus(HttpServletResponse.SC_OK);
    }

    @Test
    @Order(5)
    @DisplayName("Department 삭제")
    void doDelete() throws ServletException, IOException {

// given
        ByteArrayInputStream inputStream = new ByteArrayInputStream(json.getBytes());
        DelegatingServletInputStream servletInputStream = new DelegatingServletInputStream(inputStream);

        DepartmentDto dto = new DepartmentDto.Builder()
                .deptNo(deptNo)
                .deptName(deptName)
                .build();

        // when
        when(request.getInputStream()).thenReturn(servletInputStream);

        when(service.delete(dto)).thenReturn(1);

        servlet.doDelete(request, response);

        // then
        verify(response).setStatus(HttpServletResponse.SC_OK);
    }
}