package com.study.springboot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.study.springboot.dao.ITicketDao;
import com.study.springboot.dto.CardDto;
import com.study.springboot.service.IBuyCardTicketService;

@Controller
public class MyController {

	@Autowired
	IBuyCardTicketService buyTicket;
	
	@Autowired
	TransactionTemplate transactionTemplate;
	
	@Autowired
	ITicketDao ticket;
	
	@RequestMapping("/")
	public @ResponseBody String root() throws Exception{
		return "Transaction Propagation (4)";
	}
	
	@RequestMapping("/buy_ticket")
	public String buy_ticket() {
		return "buy_ticket";
	}
	
	@RequestMapping("/buy_ticket_card")
	public String buy_ticket_card(CardDto dto, Model model) {
		
		try {
			transactionTemplate.execute(new TransactionCallbackWithoutResult() {
				@Override
				protected void doInTransactionWithoutResult(TransactionStatus arg0) {
					
					buyTicket.buyTicket(dto.getConsumerId(), "1");	
					buyTicket.buyTicket(dto.getConsumerId(), "7");
					
					ticket.buyTicket(dto.getConsumerId(), dto.getAmount());
				}
			});
		}catch(Exception e) {
			System.out.println("[Transaction Propagation #1] Rollback 되었습니다.");
		}
		
		//buyTicket.buyTicket(dto.getConsumerId(), dto.getAmount());
		
		model.addAttribute("dto", dto);
		
		return "buy_ticket_end";
	}
}
