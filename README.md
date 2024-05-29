# Multi-RPC

An easy to use library for json-rpc, xml-rpc, SOAP and RESTful/JSONP services implementation.

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
   authenticate(String usr, String pwd) {

  if(usr.equals(pwd)) {
    return new SimplePrincipal(usr);
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

### RESTful call

```
http://localhost:8080/webapp/rpc/TEST.hello/World
```

### JSONP (JSON with Padding) call

```
http://localhost:8080/webapp/rpc/TEST.hello/World?callback=handleResponse
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
    map.put("name",      "Clark Kent");
    map.put("birthdate", "1974-11-19");
    
    List<Object> list = new ArrayList<Object>();
    list.add("3");
    
    Map<String, Object> struct = new HashMap<String, Object>();
    struct.put("person", map);
    
    // Wrapped Map 
    WMap wmap = new WMap(map);
    Date date = wmap.getSQLDate("birthdate");
    System.out.println("wmap.getSQLDate(\"birthdate\") -> " + date);
    
    // Wrapped List 
    WList wlist = new WList(list);
    int val = wlist.getInt(0);
    System.out.println("wlist.getInt(0) -> " + val);
    
    // Wrapped Structure 
    WStruct wstruct = new WStruct(struct);
    String name = wstruct.getString("person.name");
    System.out.println("wstruct.getString(\"person.name\") -> " + name);
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

## Publish to maven central

1. Create an account on https://central.sonatype.com;

2. Create a `namespace` (`groupId`): e.g. io.github.giosil;

	2.1 To verify a `namespace` with github (by Code Hosting Services) you must create the public repository (e.g. github.com/giosil/verification-key)
	
	2.2 At the end of the process when your `namespace` (`groupId`) is registered you can simply delete the empty repository.

3. Installing GnuPG from https://www.gnupg.org/download;

	3.1 Generating a Key Pair with `gpg --gen-key`;
	
	3.2 Distributing your Public Key:
	
		`gpg --keyserver keyserver.ubuntu.com --send-keys 6B79F0BC81328E1C07EF79A5090B44F4D0F7C43F`
		`gpg --keyserver keys.openpgp.org --send-keys 6B79F0BC81328E1C07EF79A5090B44F4D0F7C43F`
		`gpg --keyserver pgp.mit.edu --send-keys 6B79F0BC81328E1C07EF79A5090B44F4D0F7C43F`

4. Requirements your deployment components (view `pom-giosil.xml`):

	4.1 Supply Javadoc and Sources;
	
	4.2 Provide Files Checksums (md5 and sha1);
	
	4.3 Sign Files with GPG/PGP (asc);
	
	4.4 Sufficient Metadata in `pom.xml` (project name/description/url, licenses, developers, scm);
	
	4.5 `groupId` is the `namespace` verified.

5. Build library `mvn -f .\pom-giosil.xml clean install -DcreateChecksum=true`;

6. Create zip file (e.g. `io/github/giosil/multi-rpc` from `$HOME/.m2/repository`, delete `_remote.repositories`);

7. Upload zip file from the page `https://central.sonatype.com/publishing/deployments`.

## Contributors

* [Giorgio Silvestris](https://github.com/giosil)
