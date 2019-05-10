package com.study.springboot;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyController {
	
	@RequestMapping("/")
	public String test1() throws Exception{
		return "MyController - @RequestMapping(/)";
	}
	
	@RequestMapping("/demo")
	public String test2() throws Exception{
		return "MyController - @RequestMapping(/demo)";
	}
}
