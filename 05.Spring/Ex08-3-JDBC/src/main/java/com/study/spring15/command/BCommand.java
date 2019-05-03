package com.study.spring15.command;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

public interface BCommand {
	
	void execute(HttpServletRequest request, Model model);
			
}
