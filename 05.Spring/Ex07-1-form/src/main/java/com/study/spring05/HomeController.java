package com.study.spring05;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.study.spring05.member.Member;

@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		
		return "home";
	}
	
	@RequestMapping("/board/confirmId")
	public String confirmId(HttpServletRequest req, Model model) {
		
		String id = req.getParameter("id");
		String pw = req.getParameter("pw");
		
		model.addAttribute("id", id);
		model.addAttribute("pw", pw);
		
		return "board/confirmId";
	}
	
	@RequestMapping("/board/checkId")
	public String checkId(@RequestParam("id") String id, @RequestParam("pw") int pw , Model model) {

		model.addAttribute("identity", id);
		model.addAttribute("password", pw);
		
		return "board/checkId";
	}
	
	@RequestMapping("/member/join1")
	public String joinData(@RequestParam("name") String name,
						   @RequestParam("id") String id,
						   @RequestParam("pw") String pw,
						   @RequestParam("email") String email, Model model) {
		
		Member member = new Member();
		member.setName(name);
		member.setId(id);
		member.setPw(pw);
		member.setEmail(email);
		
		model.addAttribute("memberInfo", member);
		
		return "member/join1";
	}
	
	@RequestMapping("/member/join2")
	public String joinData(Member member) {
		
		return "member/join2";
	}
}
