package com.study.jsp02;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/ContextInitParam")
public class ContextInitParam extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{
		System.out.println("doGet111");
		
		String id = getServletContext().getInitParameter("id");
		String pw = getServletContext().getInitParameter("pw");
		String path = getServletContext().getInitParameter("path");
		
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter writer = response.getWriter();
		writer.println("<html><head></head><body>");
		writer.println("아이디 : " + id + "<br>");
		writer.println("비밀번호 : " + pw + "<br>");
		writer.println("path : " + path);
		writer.println("</body></html>");
		
		writer.close();	//context 는 jsp08에서 다 읽을 수 있음
						//서버에 있는 web.xml에 넣으면 전체 다 읽을 수 있음 (공유)

	}

}
