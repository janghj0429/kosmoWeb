package com.study.spring14.command;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

import com.study.spring14.dao.BDao;
import com.study.spring14.dto.BDto;

@Component("contentHandler")
public class BContentCommand implements BCommand{

	@Override
	public void execute(HttpServletRequest request, Model model)
	{
		String bId = request.getParameter("bId");
		String bKind = request.getParameter("kind");
		System.out.println("content : " +bId + bKind);
		BDao dao = BDao.getInstance();
		BDto dto = dao.contentView(bId, bKind);
		
		request.setAttribute("content_view", dto);
	}
}
