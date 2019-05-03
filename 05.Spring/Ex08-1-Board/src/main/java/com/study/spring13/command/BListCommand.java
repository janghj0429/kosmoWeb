package com.study.spring13.command;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

import com.study.spring13.dao.BDao;
import com.study.spring13.dto.BDto;

public class BListCommand implements BCommand{
	
	@Override
	public void execute(HttpServletRequest request, Model model)
	{
		BDao dao = BDao.getInstance();
		ArrayList<BDto>	dtos = dao.list();
		model.addAttribute("list", dtos);
	}

}
