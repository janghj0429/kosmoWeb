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
		request.setCharacterEncoding("UTF-8");
		
		String id, pw;
		
		id = request.getParameter("id");
		pw = request.getParameter("pw");	
		
		MemberDao dao = MemberDao.getInstance();
		MemberDto dto = new MemberDto();
		String json_data = "";
		
		int checkNum = dao.userCheck(id, pw);
		if(checkNum == MemberDao.MEMBER_LOGIN_SUCCESS)
		{
			dto = dao.getMember(id);
			HttpSession session = request.getSession();
			session.setAttribute("id", id);
			session.setAttribute("name", dto.getName());
			System.out.println(id +":"+ dto.getName());
			json_data = "{\"code\":\"success\", \"desc\":\"로그인 성공\"}";
		}else {
			json_data = "{\"code\":\"fail\", \"desc\":\"로그인 실패\"}";
		}
		response.setContentType("application/json; charset=UTF-8");
		PrintWriter writer = response.getWriter();
		writer.println(json_data);
		writer.close();
	}
}
