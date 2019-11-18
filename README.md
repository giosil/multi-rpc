# Multi-RPC

A simple  library for xml-rpc and json-rpc implementation.

## Examples

### Service

```java
public class WebRPC extends org.rpc.server.RpcServlet {
	
	public void init() throws javax.servlet.ServletException {
		rpcExecutor = new org.rpc.server.MultiRpcExecutor();
		
		createRpcContex  = true;
		restful          = true;
		about            = true;
		basicAuth        = true;
		
		addWebService(new TestHandler(), "TEST", "Test handler");
	}
	
	protected java.security.Principal authenticate(String username, String password) {
		if(username.equals(password)) return new org.rpc.util.SimplePrincipal(username);
		return null;
	}
	
}
```

### Handler

```java
public class TestHandler {
	
	public String hello(String name) throws Exception {
		return "Hello " + name + "!";
	}
	
}
```

## Build

- `git clone https://github.com/giosil/wcollections.git`
- `mvn clean install`

## Contributors

* [Giorgio Silvestris](https://github.com/giosil)
