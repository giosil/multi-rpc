package org.dew.wdemo.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;

import javax.servlet.annotation.WebServlet;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "WebInit", loadOnStartup = 1, urlPatterns = { "/init/*" })
public
class WebInit extends HttpServlet
{
  private static final long serialVersionUID = 514164833521567113L;
  
  @Override
  public
  void init()
    throws ServletException
  {
    System.out.println("[wdemo-fe] WebInit.init()...");
  }
  
  @Override
  public
  void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException
  {
    System.out.println("[wdemo-fe] WebInit.doGet(request, response)...");
    
    response.setContentType("text/html");
    PrintWriter out = response.getWriter();
    out.println("<html>");
    out.println("<head><title>WDemo - Frontend - WebInit</title></head>");
    out.println("<body>");
    out.println("<h1>WDemo - Frontend - WebInit</h1>");
    out.println("</body>");
    out.println("</html>");
  }
}
