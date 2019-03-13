package com.ex.jsp.command;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ex.jsp.dao.BDao;
import com.ex.jsp.dto.BDto;

public class BListCommand implements BCommand{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
	{
		
		System.out.println("list command");
		
		BDao dao = BDao.getInstance();
		ArrayList<BDto> dtos = dao.list();
		request.setAttribute("list", dtos);
	}
}
