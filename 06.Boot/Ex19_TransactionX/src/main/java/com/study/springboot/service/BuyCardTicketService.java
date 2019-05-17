package com.study.springboot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.study.springboot.dao.ICardDao;
import com.study.springboot.dao.ITicketDao;

@Service
public class BuyCardTicketService implements IBuyCardTicketService {

	@Autowired
	ICardDao card;
	
	@Autowired
	ITicketDao ticket;
	
	@Override
	public void buyTicket(String consumerId, String countnum) {
		
		card.payCard(consumerId, countnum);
		
		ticket.buyTicket(consumerId, countnum);
	}

}
