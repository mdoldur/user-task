package com.mdoldur.usertask.service.interfaces.common;

import java.util.List;

public interface GenericService {
	
	List<String> getBeans();
	
	List<String> getBeansByName(String name);
	
}
