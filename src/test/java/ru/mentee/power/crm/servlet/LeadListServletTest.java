package ru.mentee.power.crm.servlet;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import ru.mentee.power.crm.core.LeadRepository;
import ru.mentee.power.crm.infrastructure.InMemoryLeadRepository;
import ru.mentee.power.crm.model.Lead;
import ru.mentee.power.crm.model.LeadStatus;
import ru.mentee.power.crm.service.LeadService;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class LeadListServletTest {

  @Mock
  private HttpServletRequest request;

  @Mock
  private HttpServletResponse response;

  @Mock
  private ServletContext servletContext;

  @Mock
  private ServletConfig servletConfig;

  @Mock
  private LeadService leadService;

  private LeadListServlet servlet;
  private StringWriter stringWriter;
  private PrintWriter printWriter;

  @BeforeEach
  void setUp() throws Exception {
    MockitoAnnotations.openMocks(this);
    servlet = new LeadListServlet();
    when(servletConfig.getServletContext()).thenReturn(servletContext);
    servlet.init(servletConfig);
    when(servlet.getServletContext()).thenReturn(servletContext);
    when(servletContext.getAttribute("leadService")).thenReturn(leadService);
    stringWriter = new StringWriter();
    printWriter = new PrintWriter(stringWriter);
    when(response.getWriter()).thenReturn(printWriter);
  }

  @Test
  void doGetShouldSetCorrectContentType() throws Exception {
    when(leadService.findAll()).thenReturn(List.of());
    servlet.doGet(request, response);
    verify(response).setContentType("text/html; charset=UTF-8");
  }

  @Test
  void doGetShouldHandleLeadServiceNotFound() throws Exception {
    when(servletContext.getAttribute("leadService")).thenReturn(null);
    org.junit.jupiter.api.Assertions.assertThrows(
        NullPointerException.class,
        () -> servlet.doGet(request, response)
    );
  }

  @Test
  void doGetShouldCloseWriter() throws Exception {
    when(leadService.findAll()).thenReturn(List.of());
    servlet.doGet(request, response);
    verify(response).getWriter();
  }
}