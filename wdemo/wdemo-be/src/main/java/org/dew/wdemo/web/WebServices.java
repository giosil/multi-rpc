package org.dew.wdemo.web;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;

import org.dew.wdemo.ws.WSContacts;

/**
 * Entry point per il servizio RPC.
 */
@WebServlet(name = "WebServices", loadOnStartup = 1, urlPatterns = { "/rpc/*" })
public 
class WebServices extends org.rpc.server.RpcServlet 
{
  private static final long serialVersionUID = 628211903812731650L;
  
  public 
  void init() 
      throws ServletException 
  {
    System.out.println("[wdemo-be] WebServices.init()...");
    
    rpcExecutor = new org.rpc.server.MultiRpcExecutor();
    
    restAudit  = null;
    restTracer = null;
    
    legacy           = false;
    createRpcContex  = true;
    checkSession     = false;
    checkSessionREST = false;
    restful          = true;
    about            = true;
    
    basicAuth        = false;
    encoding         = "UTF-8";
    
    addWebService(new WSContacts(),    "CONTACTS",    "Contacts manager");
  }
}