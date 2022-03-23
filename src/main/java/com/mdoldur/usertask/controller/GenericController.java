package com.mdoldur.usertask.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mdoldur.usertask.service.interfaces.common.GenericService;

@RestController
@RequestMapping(value = "/")
public class GenericController {
    @Autowired
    GenericService genericService;
    
    @GetMapping(value = "/beans")
    public List<String> getBeans(@RequestParam(name = "name") Optional<String> name) {
    	if (name.isPresent()) {
    		return genericService.getBeansByName(name.get());
    	}
    	return genericService.getBeans();
    }
    
}
