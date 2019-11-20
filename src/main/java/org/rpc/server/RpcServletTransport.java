package org.rpc.server;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintWriter;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public
class RpcServletTransport implements RpcServerTransport
{
  protected static final int BUFF_LENGTH = 1024;
  
  protected HttpServletRequest  req;
  protected HttpServletResponse resp;
  protected RpcAuthorizationChecker rac;
  
  public
  RpcServletTransport(HttpServletRequest req, HttpServletResponse resp)
  {
    this.req  = req;
    this.resp = resp;
  }
  
  public
  RpcServletTransport(HttpServletRequest req, HttpServletResponse resp, RpcAuthorizationChecker rac)
  {
    this.req  = req;
    this.resp = resp;
    this.rac  = rac;
  }
  
  public
  String[] readRequest(String sContentType)
    throws Exception
  {
    String[] asResult = new String[3];
    InputStream in = null;
    try {
      in = req.getInputStream();
      
      ByteArrayOutputStream bos = new ByteArrayOutputStream();
      byte[] buff = new byte[BUFF_LENGTH];
      int n;
      while((n = in.read(buff)) > 0) {
        bos.write(buff, 0, n);
      }
      asResult[1] = bos.toString();
      asResult[2] = req.getPathInfo();
      
      if(sContentType == null || sContentType.length() == 0) {
        // Is XML?
        int iLength    = asResult[1].length();
        boolean boXml  = false;
        for(int i = 0; i < iLength; i++) {
          char c = asResult[1].charAt(i);
          if(c > 33) {
            boXml = c == '<';
            break;
          }
        }
        if(boXml) {
          int j = 2;
          char[] acLast3 = {32, 32, 32};
          for(int i = iLength-1; i >= 0 && j >= 0; i--) {
            char c = asResult[1].charAt(i);
            if(j == 2 && c < 33) continue;
            acLast3[j--] = c;
          }
          String sLast3 = new String(acLast3);
          if(sLast3.equals("pe>")) { // Envelope>
            asResult[0] = "application/soap+xml";
          }
          else {
            asResult[0] = "text/xml";
          }
        }
        else {
          asResult[0] = "application/json";
        }
      }
      else {
        asResult[0] = sContentType;
      }
    }
    finally {
      if(in != null) try{ in.close(); } catch(Exception ex) {}
    }
    return asResult;
  }
  
  public
  boolean checkAuthorization(String methodName)
  {
    if(rac != null) {
      return rac.checkAuthorization(methodName, req, resp);
    }
    return true;
  }
  
  public
  void writeResponse(String sContentType, String responseData, boolean boTransEncChunked)
    throws Exception
  {
    if(boTransEncChunked) {
      // La specifica del charset e' necessaria in SOAP con client .NET
      resp.addHeader("Content-Type",      sContentType + "; charset=utf-8");
      resp.addHeader("Transfer-Encoding", "chunked");
      PrintWriter out = resp.getWriter();
      out.write(responseData, 0, responseData.length());
      out.flush();
    }
    else {
      byte[] data = responseData.getBytes(resp.getCharacterEncoding());
      resp.addHeader("Content-Type",   sContentType);
      resp.setHeader("Content-Length", String.valueOf(data.length));
      ServletOutputStream os = resp.getOutputStream();
      os.write(data, 0, data.length);
      os.flush();
    }
  }
  
  public
  String getEncoding()
  {
    return resp.getCharacterEncoding();
  }
}
