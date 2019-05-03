package com.study.spring14.command;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

import com.study.spring14.dao.BDao;


public class BModifyCommand implements BCommand{

	@Override
	public void execute(HttpServletRequest request, Model model)
	{
		String bId = request.getParameter("bId");
		String bName = request.getParameter("bName");
		String bTitle = request.getParameter("bTitle");
		String bContent = request.getParameter("bContent");
		System.out.println(bId+ bName+ bTitle+ bContent);
		
		System.out.println("3");
		BDao dao = BDao.getInstance();
		System.out.println("4");
		dao.modify(bId, bName, bTitle, bContent);
		System.out.println("5");
		
	}
	
}
