package com.study.jsp;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/PostMethod")
public class PostMethod extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException 
	{
		System.out.println("doGet");

		response.setContentType("text/html; charset=UTF-8");
		PrintWriter writer = response.getWriter();
		
		writer.println("<html>");
		writer.println("<head>");
		writer.println("<style>");
		writer.println("h1{color:Green;}");
		writer.println("</style>");
		writer.println("</head>");
		writer.println("<body>");
		writer.println("<h1>GET 방식입니다. <br>따라서 doGet메서드가 호출됨</h1>");
		writer.println("</body>");
		writer.println("</html>");
		

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException
	{
		System.out.println("doPost");

		response.setContentType("text/html; charset=UTF-8");
		PrintWriter writer = response.getWriter();
		
		writer.println("<html>");
		writer.println("<head>");
		writer.println("<style>");
		writer.println("h1{color:orange;}");
		writer.println("</style>");
		writer.println("</head>");
		writer.println("<body>");
		writer.println("<h1>POST 방식입니다. <br>따라서 doPost메서드가 호출됨</h1>");
		writer.println("</body>");
		writer.println("</html>");
	}

}
