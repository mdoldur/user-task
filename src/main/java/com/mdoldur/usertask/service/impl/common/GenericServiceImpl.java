package com.mdoldur.usertask.service.impl.common;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import com.mdoldur.usertask.service.interfaces.common.GenericService;

@Service
public class GenericServiceImpl implements GenericService {
    @Autowired
    private ApplicationContext appContext;

	@Override
	public List<String> getBeans() {
		List<String> result = new ArrayList<>();
		result.addAll(Arrays.asList(appContext.getBeanDefinitionNames()));
		return result;
	}
	
	@Override
	public List<String> getBeansByName(String name) {
		List<String> result = new ArrayList<>();
		result.addAll(Arrays.asList(appContext.getBeanDefinitionNames()));
		return result
				.stream()
				.filter(item -> item.contains(name))
				.collect(Collectors.toList());
	}
	
}
