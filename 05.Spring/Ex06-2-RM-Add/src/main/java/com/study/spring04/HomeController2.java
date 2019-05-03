package com.study.spring04;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Handles requests for the application home page.
 */
@Controller
@RequestMapping("/board")
public class HomeController2 {
	
	@RequestMapping("/")
	public String home(Model model) {
		
		model.addAttribute("name", "홍길동");
		
		return "board/home";
	}
	
	@RequestMapping("/write")
	public String write(Model model) {
		
		model.addAttribute("id", 30);
		
		return "board/write";
	}
	
}
