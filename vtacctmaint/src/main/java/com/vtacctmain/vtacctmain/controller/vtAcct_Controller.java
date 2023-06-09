package com.vtacctmain.vtacctmain.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.vtacctmain.vtacctmain.repository.MemberRepository;

@RestController
@RequestMapping("/vtacct/controller")
public class vtAcct_Controller {

	private Logger log = LoggerFactory.getLogger(getClass());
	private MemberRepository memberRepository;
	
	@RequestMapping("/gibis_join")
	public ModelAndView porcess() {
		
		log.info("오나?");		
		return new ModelAndView("gibis_join");
	}
	
	
	
	@GetMapping(value = "/login/{userId}")
	public String login(@PathVariable("userId") String data ) {
		log.info("login userId={}",data);
		return "login - ok";
	}
	
	
	
}
