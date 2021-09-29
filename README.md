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
  encoding        = null; // e.g. "UTF-8", "ISO-8859-1"

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

### JSON serialization / deserialization

```java
import org.json.JSON;

public class Test {
  
  public static void main(String[] args) {
    Object data = JSON.parse(args[0]);
    System.out.println("JSON.parse -> " + data);
    
    String json = JSON.stringify(data);
    System.out.println("JSON.stringify -> " + json);
  }
  
}
```

### Wrapped Collections

```java
import java.sql.Date;
import java.util.HashMap;
import java.util.Map;

import org.util.WMap;

public class Test {
  
  public static void main(String[] args) {
    Map<String, Object> map = new HashMap<String, Object>();
    map.put("date", "1974-11-19");
    
    WMap wmap = new WMap(map);
    
    Date date = wmap.getSQLDate("date");
    System.out.println(date);
  }
  
}
```

### Utils and Beans manipulation

```java
import java.util.HashMap;
import java.util.Map;

import org.util.WUtil;

public class Test {
  
  public static void main(String[] args) {
    Map<String, Object> map = new HashMap<String, Object>();
    map.put("name", "MARIO ROSSI");
    
    Person person = WUtil.populateBean(Person.class, map);
    System.out.println(person);
    
    Map<String, Object> mapPerson = WUtil.toMapObject(person, false);
    System.out.println(mapPerson);
  }
  
  public static class Person {
    String name;
    public String getName() {
      return name;
    }
    public void setName(String name) {
      this.name = name;
    }
    @Override
    public String toString() {
      return "name=" + name;
    }
  }
}
```

## Build

- `git clone https://github.com/giosil/multi-rpc.git`
- `mvn clean install`

## Contributors

* [Giorgio Silvestris](https://github.com/giosil)
