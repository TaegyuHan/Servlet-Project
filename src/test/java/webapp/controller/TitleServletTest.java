package webapp.controller;

import org.junit.jupiter.api.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import webapp.dto.TitleDto;
import webapp.service.TitleService;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;


import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class TitleServletTest {

    @Mock
    private TitleService service;

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    @InjectMocks
    private TitleServlet servlet;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this); // 가짜(Mock) 객체들을 초기화합니다.
    }

    @Test
    @Order(1)
    void doPost() {
        request = mock(HttpServletRequest.class);
        response = mock(HttpServletResponse.class);

        TitleDto dto = new TitleDto.Builder()
                .empNo(111_692)
                .title("TEST CODE")
                .fromDate("2023-04-14")
                .toDate("2023-04-14")
                .build();

        when(request.getAttribute("dto")).thenReturn(dto);
        when(service.create(dto)).thenReturn(Optional.of(dto));

        servlet.doPost(request, response);

        verify(response).setStatus(HttpServletResponse.SC_OK);
    }

    @Test
    void doGet() {
        request = mock(HttpServletRequest.class);
        response = mock(HttpServletResponse.class);

        servlet.doGet(request, response);

        verify(response).setStatus(HttpServletResponse.SC_OK);
    }

    @Test
    @Order(2)
    void doPut() {
        request = mock(HttpServletRequest.class);
        response = mock(HttpServletResponse.class);

        TitleDto dto = new TitleDto.Builder()
                .empNo(111_692)
                .title("TEST CODE")
                .fromDate("2023-04-14")
                .toDate("2023-05-14")
                .build();

        when(request.getAttribute("dto")).thenReturn(dto);
        when(service.update(dto)).thenReturn(Optional.of(dto));

        servlet.doPut(request, response);

        verify(response).setStatus(HttpServletResponse.SC_OK);
    }

    @Test
    @Order(3)
    void doDelete() {
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);

        TitleDto dto = new TitleDto.Builder()
                .empNo(111_692)
                .title("TEST CODE")
                .fromDate("2023-04-14")
                .toDate("2023-05-14")
                .build();

        when(request.getAttribute("dto")).thenReturn(dto);
        when(service.delete(dto)).thenReturn(1);

        servlet.doDelete(request, response);

        verify(response).setStatus(HttpServletResponse.SC_OK);
    }
}
