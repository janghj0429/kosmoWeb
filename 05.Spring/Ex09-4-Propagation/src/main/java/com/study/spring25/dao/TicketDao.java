package com.study.spring25.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import com.study.spring25.dto.TicketDto;

public class TicketDao {

	JdbcTemplate template;
	
	//PlatformTransactionManager transactionManager;
	TransactionTemplate transactionTemplate1;
	
	public void setTemplate(JdbcTemplate template) {
		this.template = template;
	}
	
//	public void setTransactionManager(PlatformTransactionManager transactionManager) {
//		this.transactionManager = transactionManager;
//	}
	public void setTransactionTemplate1(TransactionTemplate transactionTemplate) {
		this.transactionTemplate1 = transactionTemplate;
	}
	
	public TicketDao() {
		System.out.println(template);
	}
	
	//void를 int로 바꿔서 성공시 1아닐시 0 리턴하면
	//컨트롤러에서 리턴값으로 if문 사용해서 서로 다른페이지 띄워줄수있다.
	public void buyTicket(final TicketDto dto) {
		
		try {
			transactionTemplate1.execute(new TransactionCallbackWithoutResult() {
				
				@Override
				protected void doInTransactionWithoutResult(TransactionStatus arg0) {
					template.update(new PreparedStatementCreator() {
						
						@Override
						public PreparedStatement createPreparedStatement(Connection con)
								throws SQLException{
							String query = "insert into card (consumerId, amount) values (?, ?)";
							PreparedStatement pstmt = con.prepareStatement(query);
							pstmt.setString(1, dto.getConsumerId());
							pstmt.setString(2, dto.getAmount());
							
							return pstmt;
						}
					});
					
					template.update(new PreparedStatementCreator() {
						
						@Override
						public PreparedStatement createPreparedStatement(Connection con)
								throws SQLException{
							String query = "insert into ticket (consumerId, countnum) values (?, ?)";
							PreparedStatement pstmt = con.prepareStatement(query);
							pstmt.setString(1, dto.getConsumerId());
							pstmt.setString(2, dto.getAmount());
							
							return pstmt;
						}
					});
				}				
			});

//			return result;
		}catch(Exception e) {
			//e.printStackTrace();
			System.out.println("transactionTemplate1 : Rollback");
		}
		
//		TransactionDefinition definition = new DefaultTransactionDefinition();
//		TransactionStatus status = transactionManager.getTransaction(definition);
		
//		try {
//			
//			
//			transactionManager.commit(status);
//			
//		}catch(Exception e) {
//			//e.printStackTrace();
//			System.out.println("Rollback 되었습니다.");
//			
//			transactionManager.rollback(status);
//		}

	}
}
