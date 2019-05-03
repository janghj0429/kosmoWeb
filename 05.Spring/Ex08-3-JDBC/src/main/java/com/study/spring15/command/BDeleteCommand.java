package com.study.spring15.command;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

import com.study.spring15.dao.BDao;

public class BDeleteCommand implements BCommand{
	
	@Override
	public void execute(HttpServletRequest request, Model model)
	{
		String bId = request.getParameter("bId");
		
		BDao dao = new BDao();
		dao.delete(bId);

	}
	
}
