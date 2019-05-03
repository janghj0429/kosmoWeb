package com.study.spring14.command;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

import com.study.spring14.dao.BDao;
import com.study.spring14.dto.BDto;

@Component
public class BListCommand implements BCommand{
	
	@Override
	public void execute(HttpServletRequest request, Model model)
	{
		BDao dao = BDao.getInstance();
		ArrayList<BDto>	dtos = dao.list();
		model.addAttribute("list", dtos);
	}

}
