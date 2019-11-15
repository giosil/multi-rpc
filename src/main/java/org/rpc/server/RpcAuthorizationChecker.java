package org.rpc.server;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public
interface RpcAuthorizationChecker
{
	public boolean checkAuthorization(String methodName, HttpServletRequest request, HttpServletResponse response);
}
