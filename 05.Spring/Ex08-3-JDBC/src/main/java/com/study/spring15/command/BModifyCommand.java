package com.study.spring15.command;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

import com.study.spring15.dao.BDao;


public class BModifyCommand implements BCommand{

	@Override
	public void execute(HttpServletRequest request, Model model)
	{
		String bId = request.getParameter("bId");
		String bName = request.getParameter("bName");
		String bTitle = request.getParameter("bTitle");
		String bContent = request.getParameter("bContent");
		System.out.println("11");
		
		BDao dao = new BDao();
		dao.modify(bId, bName, bTitle, bContent);
		
	}
	
}
