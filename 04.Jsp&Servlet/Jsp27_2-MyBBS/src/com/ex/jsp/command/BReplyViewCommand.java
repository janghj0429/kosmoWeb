package com.ex.jsp.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ex.jsp.dao.BDao;
import com.ex.jsp.dto.BDto;



public class BReplyViewCommand implements BCommand{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
	{
		String bId = request.getParameter("bId");
		
		BDao dao = BDao.getInstance();
		BDto dto = dao.reply_view(bId);
		
		System.out.println("reply view command");
		
		request.setAttribute("reply_view", dto);
		
	}
}
