package com.study.spring25.command;

import com.study.spring25.dto.TicketDto;

public interface ITicketCommand {

	public void execute(TicketDto ticketDto);
	
}
