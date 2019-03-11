package com.study.jsp;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class loginOk implements Service {
	
	public loginOk() {
		
	}
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{
		System.out.println("login ok");
		
		request.setCharacterEncoding("UTF-8");
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		System.out.println(id + pw);
		
		MemberDao dao = MemberDao.getInstance();
		MemberDto dto = new MemberDto();
		
		int checkNum = dao.userCheck(id, pw);
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter writer = response.getWriter();
		
		if(checkNum != 1) {
			//html 출력
			writer.println("<html><head></head><body>");
			writer.println("<script language=\"javascript\">");
			writer.println("	alert(\"로그인 실패.\");");
			writer.println("	history.back();");
			writer.println("</script>");
			writer.println("</body></html>");
			writer.close();
			
			System.out.println("로그인 실패");	
		}
		else {
			dto = dao.getMember(id);
			HttpSession session = request.getSession();
			session.setAttribute("id", id);
			session.setAttribute("name", dto.getName());
			session.setAttribute("ValidMem", "yes");
			System.out.println(id +":"+ dto.getName());
			System.out.println("로그인 성공");
			
			//html 출력
			writer.println("<html><head></head><body>");
			writer.println("<script language=\"javascript\">");
			writer.println("	alert(\"로그인 성공.\");");
			writer.println("	document.location.href=\"main.jsp\";");
			writer.println("</script>");
			writer.println("</body></html>");
			writer.close();
			
		}
	}
}
