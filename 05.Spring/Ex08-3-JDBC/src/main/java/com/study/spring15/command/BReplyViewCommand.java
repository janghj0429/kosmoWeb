package com.study.spring15.command;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

import com.study.spring15.dao.BDao;
import com.study.spring15.dto.BDto;

public class BReplyViewCommand implements BCommand{

	@Override
	public void execute(HttpServletRequest request, Model model)
	{
		String bId = request.getParameter("bId");
		
		BDao dao = new BDao();
		BDto dto = dao.reply_view(bId);
		
		request.setAttribute("reply_view", dto);
		
	}
}
