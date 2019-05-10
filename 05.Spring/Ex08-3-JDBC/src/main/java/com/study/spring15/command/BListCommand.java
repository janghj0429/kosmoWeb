package com.study.spring15.command;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

import com.study.spring15.dao.BDao;
import com.study.spring15.dto.BDto;

public class BListCommand implements BCommand{
	
	@Override
	public void execute(HttpServletRequest request, Model model)
	{
		BDao dao = new BDao();
		ArrayList<BDto>	dtos = dao.list();
		model.addAttribute("list", dtos);
	}

}
