package com.study.spring25.command;

import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import com.study.spring25.dao.TicketDao;
import com.study.spring25.dto.TicketDto;

public class TicketCommand implements ITicketCommand{
	
	private TicketDao ticketDao;
	private TransactionTemplate transactionTemplate2;
	
	public void setTransactionTemplate2(TransactionTemplate transactionTemplate2) {
		this.transactionTemplate2 = transactionTemplate2;
	}
	
	public void setTicketDao(TicketDao ticketDao) {
		this.ticketDao = ticketDao;
	}
	
	public TicketDao getTicketDao() {
		return ticketDao;
	}
	
	@Override
	public void execute(final TicketDto ticketDto) {
		System.out.println("1");
		try {
			transactionTemplate2.execute(new TransactionCallbackWithoutResult() {
				
				@Override
				protected void doInTransactionWithoutResult(TransactionStatus status) {
					ticketDto.setAmount("1");
					ticketDao.buyTicket(ticketDto);
					
					ticketDto.setAmount("6");
					ticketDao.buyTicket(ticketDto);
				}
			});
		}catch(Exception e) {
			//e.printStackTrace();
			System.out.println("transactionTemplate2 : Rollback");
		}
	}
	
}
