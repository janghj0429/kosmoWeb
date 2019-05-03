package com.study.spring14.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.study.spring14.command.BCommand;
import com.study.spring14.command.BContentCommand;
import com.study.spring14.command.BDeleteCommand;
import com.study.spring14.command.BListCommand;
import com.study.spring14.command.BModifyCommand;
import com.study.spring14.command.BReplyCommand;
import com.study.spring14.command.BReplyViewCommand;
import com.study.spring14.command.BWriteCommand;

@Controller
public class BController {
	
	@Autowired
	private ApplicationContext context;
	
	BCommand command = null;

	@RequestMapping("/list")
	public String list(HttpServletRequest request, Model model) {
		System.out.println("list()");
		
		//command = new BListCommand();
		command = context.getBean(BListCommand.class);//Component 설정필요함
		command.execute(request, model);
		
		return "list";
	}
	
	@RequestMapping("/content_view")
	public String content_view(HttpServletRequest request, Model model) {
		
		model.addAttribute("request", request);
		//command = new BContentCommand();
		command = (BContentCommand)context.getBean("contentHandler");
		command.execute(request, model);
		return "content_view";
	}
	
	@RequestMapping("/write_view")
	public String write_view() {
		return "write_view";
	}
	
	@RequestMapping("/write")
	public String write(HttpServletRequest request, Model model) {
		
		model.addAttribute("request", request);
		//command = new BWriteCommand();
		command = (BWriteCommand)context.getBean("writeHandler");
		command.execute(request, model);
		
//		command = new BListCommand();
//		command.execute(request, model);
		
		return "redirect:list";
	}
	
	@RequestMapping("/modify_view")
	public String modify_view(HttpServletRequest request, Model model) {
			
		command = new BContentCommand();
		command.execute(request, model);
		
		return "modify_view";
	}
	
	@RequestMapping("/modify")
	public String modify(HttpServletRequest request, Model model) {
		
		System.out.println("1");
		model.addAttribute("request", request);
		//command = new BModifyCommand();
		command = (BModifyCommand)context.getBean("modifyHandler");
		command.execute(request, model);
		System.out.println("2");
//		command = new BContentCommand();
//		command.execute(request, model);
		
		return "redirect:content_view";
	}
	
	@RequestMapping("/delete")
	public String delete(HttpServletRequest request, Model model) {
		
		model.addAttribute("request", request);
		//command = new BDeleteCommand();
		command = (BDeleteCommand)context.getBean("deleteHandler");
		command.execute(request, model);
		
//		command = new BListCommand();
//		command.execute(request, model);
		
		return "redirect:list";
	}
	
	@RequestMapping("/reply_view")
	public String reply_view(HttpServletRequest request, Model model) {
		
		model.addAttribute("request", request);
		//command = new BReplyViewCommand();
		command = (BReplyViewCommand)context.getBean("replyViewHandler");
		command.execute(request, model);
		
		return "reply_view";
	}
	
	@RequestMapping("/reply")
	public String reply(HttpServletRequest request, Model model) {
		
		model.addAttribute("request", request);
		//command = new BReplyCommand();
		command = (BReplyCommand)context.getBean("replyHandler");
		command.execute(request, model);
		
//		command = new BListCommand();
//		command.execute(request, model);
		
		return "redirect:list";
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
