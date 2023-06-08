package com.vtacctmain.vtacctmain.logController;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

//@Slf4j
@RestController
public class LogController {
	
	
	private final Logger log = LoggerFactory.getLogger(getClass()); //lombok 이 자동으로 해줌 @Slf4j
	
	
	@RequestMapping("/log-test")
	public String logTest() {
		
		String name = "Virtual";
		
		System.out.println("System.out.println name = "+name);
		 
		 log.trace("log-trace = ",name);
		 log.debug("log-debug = ",name);
		 log.info("log-info = ",name);
		 log.warn("log-warn = ",name);
		 log.error("log-error = ",name);
		
		 // 로그를 사용하지 않아도 a+b 계산 로직이 먼저 실행 됨, 이런방식으로 사용하면 안됨.
		 log.debug("String concat log = "+name);		
		return "ok";
		
	}
	
}
