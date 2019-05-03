package com.study.spring15.command;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

import com.study.spring15.dao.BDao;

public class BReplyCommand implements BCommand{

	@Override
	public void execute(HttpServletRequest request, Model model)
	{
		String bId = request.getParameter("bId");
		String bName = request.getParameter("bName");
		String bTitle = request.getParameter("bTitle");
		String bContent = request.getParameter("bContent");
		String bGroup = request.getParameter("bGroup");
		String bStep = request.getParameter("bStep");
		String bIndent = request.getParameter("bIndent");
		
		BDao dao = new BDao();
		dao.reply(bId, bName, bTitle, bContent, bGroup, bStep, bIndent);
		System.out.println("reply command");
		
	}
}
