package com.study.spring13.command;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

import com.study.spring13.dao.BDao;
import com.study.spring13.dto.BDto;

public class BContentCommand implements BCommand{

	@Override
	public void execute(HttpServletRequest request, Model model)
	{
		String bId = request.getParameter("bId");
		String bKind = request.getParameter("kind");
		BDao dao = BDao.getInstance();
		BDto dto = dao.contentView(bId, bKind);
		
		request.setAttribute("content_view", dto);
	}
}
