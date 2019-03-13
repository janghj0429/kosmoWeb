package com.ex.jsp.command;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ex.jsp.dao.BDao;
import com.ex.jsp.dto.BDto;

public class BContentCommand implements BCommand{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
	{
		
		System.out.println("content command or modify");
		
		String bId = request.getParameter("bId");
		String bKind = request.getParameter("kind");
		BDao dao = BDao.getInstance();
		BDto dto = dao.contentView(bId, bKind);
		
		request.setAttribute("content_view", dto);
		
	}
	
}
