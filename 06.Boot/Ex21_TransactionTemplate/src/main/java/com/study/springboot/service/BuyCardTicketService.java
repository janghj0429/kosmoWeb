package com.study.springboot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import com.study.springboot.dao.ICardDao;
import com.study.springboot.dao.ITicketDao;

@Service
public class BuyCardTicketService implements IBuyCardTicketService {

	@Autowired
	ICardDao card;
	
	@Autowired
	ITicketDao ticket;
	
//	@Autowired
//	PlatformTransactionManager transactionManager;
//	
//	@Autowired
//	TransactionDefinition definition;
	@Autowired
	TransactionTemplate transactionTemplate;
	
	@Override
	public void buyTicket(String consumerId, String countnum) {
		
//		TransactionStatus status = transactionManager.getTransaction(definition);
		
		try {
			transactionTemplate.execute(new TransactionCallbackWithoutResult() {
				@Override
				protected void doInTransactionWithoutResult(TransactionStatus arg0) {
					
					card.payCard(consumerId, countnum);		
					ticket.buyTicket(consumerId, countnum);
				}
			});
			
			
//			transactionManager.commit(status);
			
		}catch(Exception e) {
			System.out.println("[TransactionTemplate] Rollback 되었습니다.");
//			transactionManager.rollback(status);
		}	
	}

}
