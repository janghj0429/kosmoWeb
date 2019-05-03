package com.study.spring13.command;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

import com.study.spring13.dao.BDao;

public class BDeleteCommand implements BCommand{
	
	@Override
	public void execute(HttpServletRequest request, Model model)
	{
		String bId = request.getParameter("bId");
		
		BDao dao = BDao.getInstance();
		dao.delete(bId);

	}
	
}
