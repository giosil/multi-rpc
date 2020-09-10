# Multi-RPC

An easy to use library for xml-rpc, json-rpc and RESTful services implementation.

## Examples

### Service

```java
import java.security.Principal;

import javax.servlet.ServletException;

import org.rpc.server.*;
import org.rpc.util.*;

public class WebRPC 
       extends RpcServlet {

 public void init() 
        throws ServletException {

  rpcExecutor 
     = new MultiRpcExecutor();

  createRpcContex = true;
  restful         = true;
  about           = true;
  basicAuth       = true;

  addWebService(new Test(),
   "TEST",
   "Test handler");
 }

 protected Principal 
   authenticate(String usr, 
                String pwd) {

  if(usr.equals(pwd)) {
    return 
      new SimplePrincipal(usr);
  }

  return null;
 }

}
```

### Handler

```java
public class Test {

 public String hello(String name){
   return "Hello " + name + "!";
 }

}
```

### CORS (Cross-Origin Resource Sharing)

```java
public class WebRPC 
       extends RpcServlet {
  // ...
  
  @Override
  protected
  void doOptions(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException
  {
    // Response to preflight request
    
    response.addHeader("Access-Control-Allow-Methods", "GET, PUT, POST, DELETE, HEAD, OPTIONS");
    response.addHeader("Access-Control-Allow-Origin",  "*");
    response.addHeader("Access-Control-Allow-Headers", "*");
    
    response.setStatus(HttpServletResponse.SC_NO_CONTENT);
  }
  
  @Override
  protected
  void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException
  {
    response.addHeader("Access-Control-Allow-Methods", "GET, PUT, POST, DELETE, HEAD, OPTIONS");
    response.addHeader("Access-Control-Allow-Origin",  "*");
    
    super.doGet(request, response);
  }
  
  @Override
  protected
  void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException
  {
    response.addHeader("Access-Control-Allow-Methods", "GET, PUT, POST, DELETE, HEAD, OPTIONS");
    response.addHeader("Access-Control-Allow-Origin",  "*");
    
    super.doPost(request, response);
  }
}
```

## Build

- `git clone https://github.com/giosil/multi-rpc.git`
- `mvn clean install`

## Contributors

* [Giorgio Silvestris](https://github.com/giosil)
