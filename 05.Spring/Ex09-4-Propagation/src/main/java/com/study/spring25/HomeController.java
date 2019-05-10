package com.study.spring25;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.study.spring25.command.TicketCommand;
import com.study.spring25.dao.TicketDao;
import com.study.spring25.dto.TicketDto;



@Controller
public class HomeController {
	
	private TicketDao dao;
	private TicketCommand ticketCommand;
	
	@Autowired
	public void setDao(TicketDao dao) {
		this.dao = dao;
	}
	
	@Autowired
	public void setTicketCommand(TicketCommand ticketCommand) {
		this.ticketCommand = ticketCommand;
	}
	
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
	
	@RequestMapping("/buy_ticket")
	public String buy_ticket() {
		return "buy_ticket";
	}
	
	@RequestMapping("/buy_ticket_card")
	public String buy_ticket_card(TicketDto ticketDto, Model model) {
		
		//dao.buyTicket(ticketDto);
		
		ticketCommand.execute(ticketDto);	
		
		model.addAttribute("ticketInfo", ticketDto);
		
		return "buy_ticket_end";
		
//		int result = 0;
//		if(dao.buyTicket(ticketDto) == 1) {
//			model.addAttribute("ticketInfo", ticketDto);
//			return "buy_ticket_end";
//		}else {
//			return "fail";
//			//아니면 alert 띄우고 리다이렉트로 처음 페이지 출력
//		}
		
		
		
		
		//return "buy_ticket_end";
	}
	
}
