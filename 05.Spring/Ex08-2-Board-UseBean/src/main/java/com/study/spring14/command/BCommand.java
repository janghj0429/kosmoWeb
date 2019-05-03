package com.study.spring14.command;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

public interface BCommand {
	
	void execute(HttpServletRequest request, Model model);
			
}
