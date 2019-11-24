# Multi-RPC

A simple  library for xml-rpc and json-rpc implementation.

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

 public String hello(String name) {
   return "Hello " + name + "!";
 }

}
```

## Build

- `git clone https://github.com/giosil/multi-rpc.git`
- `mvn clean install`

## Contributors

* [Giorgio Silvestris](https://github.com/giosil)
