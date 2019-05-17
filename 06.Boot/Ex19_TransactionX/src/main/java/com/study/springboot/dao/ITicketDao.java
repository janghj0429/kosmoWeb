package com.study.springboot.dao;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ITicketDao {
	public void buyTicket(String consumerId, String countnum);
}
