package com.ex.jsp.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ex.jsp.dao.BDao;


public class BWriteCommand implements BCommand{
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
	{
		String bName = request.getParameter("bName");
		String bTitle = request.getParameter("bTitle");
		String bContent = request.getParameter("bContent");
		System.out.println("write command");
		
		BDao dao = BDao.getInstance();
		dao.write(bName, bTitle, bContent);
	}
}
