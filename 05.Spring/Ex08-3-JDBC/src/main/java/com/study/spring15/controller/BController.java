package com.study.spring15.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.study.spring15.command.BCommand;
import com.study.spring15.command.BContentCommand;
import com.study.spring15.command.BDeleteCommand;
import com.study.spring15.command.BListCommand;
import com.study.spring15.command.BModifyCommand;
import com.study.spring15.command.BReplyCommand;
import com.study.spring15.command.BReplyViewCommand;
import com.study.spring15.command.BWriteCommand;
import com.study.spring15.util.Constant;

@Controller
public class BController {
	
	@Autowired
	private ApplicationContext context;
	
	BCommand command = null;
	
	public JdbcTemplate template;
	
	@Autowired
	public void setTemplate(JdbcTemplate template) {
		this.template = template;
		Constant.template = this.template;
	}

	@RequestMapping("/list")
	public String list(HttpServletRequest request, Model model) {
		System.out.println("list()");
		
		command = new BListCommand();
		//command = context.getBean(BListCommand.class);//Component 설정필요함
		command.execute(request, model);
		
		return "list";
	}
	
	@RequestMapping("/content_view")
	public String content_view(HttpServletRequest request, Model model) {
		command = new BContentCommand();
		command.execute(request, model);
		return "content_view";
	}
	
	@RequestMapping("/write_view")
	public String write_view() {
		return "write_view";
	}
	
	@RequestMapping("/write")
	public String write(HttpServletRequest request, Model model) {
		command = new BWriteCommand();
		command.execute(request, model);
		
		command = new BListCommand();
		command.execute(request, model);
		
		return "list";
	}
	
	@RequestMapping("/modify_view")
	public String modify_view(HttpServletRequest request, Model model) {
		command = new BContentCommand();
		command.execute(request, model);
		
		return "modify_view";
	}
	
	@RequestMapping("/modify")
	public String modify(HttpServletRequest request, Model model) {
		command = new BModifyCommand();
		command.execute(request, model);
		
		command = new BContentCommand();
		command.execute(request, model);
		
		return "content_view";
	}
	
	@RequestMapping("/delete")
	public String delete(HttpServletRequest request, Model model) {
		command = new BDeleteCommand();
		command.execute(request, model);
		
		command = new BListCommand();
		command.execute(request, model);
		
		return "list";
	}
	
	@RequestMapping("/reply_view")
	public String reply_view(HttpServletRequest request, Model model) {
		command = new BReplyViewCommand();
		command.execute(request, model);
		
		return "reply_view";
	}
	
	@RequestMapping("/reply")
	public String reply(HttpServletRequest request, Model model) {
		command = new BReplyCommand();
		command.execute(request, model);
		
		command = new BListCommand();
		command.execute(request, model);
		
		return "list";
	}
	
}
