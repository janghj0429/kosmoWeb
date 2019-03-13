package com.ex.jsp.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ex.jsp.dao.BDao;

public class BDeleteCommand implements BCommand{
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
	{
		String bId = request.getParameter("bId");
		System.out.println("delete command");
		
		BDao dao = BDao.getInstance();
		dao.delete(bId);

	}
}
