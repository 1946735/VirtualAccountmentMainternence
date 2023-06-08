package com.vtacctmain.vtacctmain.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class vtAcct_Controller {

	private Logger log = LoggerFactory.getLogger(getClass());
	
	@GetMapping(value = "/login/{userId}")
	public String login(@PathVariable("userId") String data ) {
		log.info("login userId={}",data);
		return "login - ok";
	}
	
	
	
}
