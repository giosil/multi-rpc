package org.rpc.util;

import java.util.Map;

public
interface Mapable
{
	public void fromMap(Map map);
	
	public Map toMap();
}
