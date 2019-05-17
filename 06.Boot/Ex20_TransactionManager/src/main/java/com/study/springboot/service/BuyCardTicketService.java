package com.study.springboot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;

import com.study.springboot.dao.ICardDao;
import com.study.springboot.dao.ITicketDao;

@Service
public class BuyCardTicketService implements IBuyCardTicketService {

	@Autowired
	ICardDao card;
	
	@Autowired
	ITicketDao ticket;
	
	@Autowired
	PlatformTransactionManager transactionManager;
	
	@Autowired
	TransactionDefinition definition;
	
	@Override
	public void buyTicket(String consumerId, String countnum) {
		
		TransactionStatus status = transactionManager.getTransaction(definition);
		
		try {
			card.payCard(consumerId, countnum);		
			ticket.buyTicket(consumerId, countnum);
			
			transactionManager.commit(status);
			
		}catch(Exception e) {
			System.out.println("[PlatformTransactionManager] Rollback 되었습니다.");
			transactionManager.rollback(status);
		}	
	}

}
