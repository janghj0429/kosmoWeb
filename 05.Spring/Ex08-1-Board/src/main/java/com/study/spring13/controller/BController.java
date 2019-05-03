package com.study.spring13.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.study.spring13.command.BCommand;
import com.study.spring13.command.BContentCommand;
import com.study.spring13.command.BDeleteCommand;
import com.study.spring13.command.BListCommand;
import com.study.spring13.command.BModifyCommand;
import com.study.spring13.command.BReplyCommand;
import com.study.spring13.command.BReplyViewCommand;
import com.study.spring13.command.BWriteCommand;

@Controller
public class BController {
	
	BCommand command = null;

	@RequestMapping("/list")
	public String list(HttpServletRequest request, Model model) {
		System.out.println("list()");
		command = new BListCommand();
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
//
//	@RequestMapping("/write")
//	public String write(/*HttpServletRequest req, */Model model) {
//		System.out.println("write()");
//		command = new BWriteCommand();
////		model.addAttribute("bName", req.getParameter("bName"));
////		model.addAttribute("bTitle", req.getParameter("bTitle"));
////		model.addAttribute("bContent", req.getParameter("bContent"));
//		command.execute(model);
//		
//		return "list";
//	}
//	
//	@RequestMapping("/content_view")
//	public String content_view(Model model) {
//		command = new BContentCommand();
//		command.execute(model);
//		
//		return "content_view";
//	}
		
//		String uri = request.getRequestURI();
//		System.out.println("uri : " + uri);
//		String conPath = request.getContextPath();
//		System.out.println("conPath : " + conPath);
//		String com = uri.substring(conPath.length());
//		System.out.println("command : " + com);
//		
//		HttpSession session = null;
//		session = request.getSession();
//		int curPage = 1;
//		if(session.getAttribute("cpage") != null) {
//			curPage = (int)session.getAttribute("cpage");
//		}
//		
//		if(com.equals("/write_view.do")) {
//			viewPage = "write_view.jsp";
//		}else if(com.equals("/write.do")) {
//			command = new BWriteCommand();
//			command.execute(request, response);
//			viewPage = "list.do";
//		}else if(com.equals("/list.do")) {
//			command = new BListCommand();
//			command.execute(request, response);
//			viewPage = "list.jsp";
//		}else if(com.equals("/content_view.do")) {
//			command = new BContentCommand();
//			command.execute(request, response);
//			viewPage = "content_view.jsp";
//		}else if(com.equals("/modify_view.do")) {
//			command = new BContentCommand();
//			command.execute(request, response);
//			viewPage = "modify_view.jsp";
//		}else if(com.equals("/modify.do")) {
//			command = new BModifyCommand();
//			command.execute(request, response);
//			
//			command = new BContentCommand();
//			command.execute(request, response);
//			viewPage = "content_view.jsp";
//			//		위세줄대신에	"list.do"로 보내도됨
//		}else if(com.equals("/delete.do")) {
//			command = new BDeleteCommand();
//			command.execute(request, response);
//			viewPage = "list.do?page="+curPage;
//		}else if(com.equals("/reply_view.do")) {
//			command = new BReplyViewCommand();
//			command.execute(request, response);
//			viewPage = "reply_view.jsp";
//		}else if(com.equals("/reply.do")) {
//			System.out.println("리플라이");
//			command = new BReplyCommand();
//			command.execute(request, response);
//			viewPage = "list.do?page="+curPage;
//		}
//			
//			
//		RequestDispatcher dispatcher = request.getRequestDispatcher(viewPage);
//		dispatcher.forward(request, response);
//		
//		
//	}

}
