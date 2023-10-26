package org.dew.test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintWriter;

import java.util.Enumeration;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletOutputStream;
import javax.servlet.WriteListener;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.dew.wdemo.bl.Contact;
import org.dew.wdemo.web.WebServices;
import org.dew.wdemo.ws.WSContacts;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import static org.mockito.Mockito.*;

public class TestWDemoBE extends TestCase {
  
  public TestWDemoBE(String testName) {
    super(testName);
  }
  
  public static Test suite() {
    return new TestSuite(TestWDemoBE.class);
  }
  
  public void testWeb() throws Exception {
    System.out.println("testWeb()...");
    
    MockWebOutput mockWebOutput = new MockWebOutput();
    
    HttpServletRequest request   = mock(HttpServletRequest.class);
    when(request.getMethod()).thenReturn("GET");
    when(request.getPathInfo()).thenReturn("/CONTACTS.list");
    when(request.getHeaderNames()).thenReturn(new MockEnumeration());
    when(request.getParameterNames()).thenReturn(new MockEnumeration());
    
    HttpServletResponse response = mock(HttpServletResponse.class);
    when(response.getCharacterEncoding()).thenReturn("UTF-8");
    when(response.getWriter()).thenReturn(mockWebOutput.getPrintWriter());
    when(response.getOutputStream()).thenReturn(mockWebOutput.getServletOutputStream());
    
    HttpServlet httpServlet = new WebServices();
    httpServlet.init(mock(ServletConfig.class));
    httpServlet.service(request, response);
    
    System.out.println(mockWebOutput.getContentAsString());
  }
    
  public void testApp() throws Exception {
    System.out.println("testApp()...");

    Contact contact = new Contact("test", "test@dew.org");
    
    System.out.println("Test WSContacts.clear...");
    WSContacts.clear();
    
    System.out.println("Test WSContacts.add...");
    WSContacts.add(contact);
    
    System.out.println("Test WSContacts.list...");
    List<Contact> listContact = WSContacts.list();
    assertNotNull(listContact);
    
    assertEquals(1, listContact.size());
    
    System.out.println("Test WSContacts.remove...");
    int removeResult = WSContacts.remove(contact);
    assertEquals(1, removeResult);
    
    System.out.println("Test WSContacts.init...");
    WSContacts.init();
    
    System.out.println("Test WSContacts.find...");
    List<Contact> listFindContact = WSContacts.find(new Contact("Peter"));
    assertNotNull(listFindContact);
    
    assertTrue(listFindContact.size() > 0);
    
    listFindContact = WSContacts.find(new Contact("Peter", "test"));
    assertNotNull(listFindContact);
    assertTrue(listFindContact.size() == 0);
  }
  
  class MockEnumeration implements Enumeration<String> 
  {
    public MockEnumeration() {
    }
    
    @Override 
    public String  nextElement() {
      return null;
    }
    
    @Override 
    public boolean hasMoreElements() { 
      return false; 
    }
  }
  
  class MockWebOutput
  {
    protected ByteArrayOutputStream baos;
    protected PrintWriter printWriter;
    
    public MockWebOutput()
    {
      this.init();
    }
    
    public void init()
    {
      baos = new ByteArrayOutputStream();
    }
    
    public byte[] getContent() {
      if(this.printWriter != null) {
        this.printWriter.flush();
      }
      return baos.toByteArray();
    }
    
    public String getContentAsString() {
      if(this.printWriter != null) {
        this.printWriter.flush();
      }
      return new String(baos.toByteArray());
    }
    
    public PrintWriter getPrintWriter() {
      this.printWriter = new PrintWriter(baos);
      return this.printWriter;
    }
    
    public ServletOutputStream getServletOutputStream() {
      return new ServletOutputStream() {
        @Override
        public boolean isReady() {
          return true;
        }
        @Override
        public void setWriteListener(WriteListener writeListener) {
        }
        @Override
        public void write(int b) throws IOException {
          baos.write(b);
        }
      };
    }
  }
}
