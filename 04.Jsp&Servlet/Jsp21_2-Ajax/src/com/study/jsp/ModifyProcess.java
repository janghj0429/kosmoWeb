package com.study.jsp;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/ModifyProcess")
public class ModifyProcess extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException 
	{
		request.setCharacterEncoding("UTF-8");
		
		HttpSession session = request.getSession();
		String id = (String)session.getAttribute("id");
		String pw = request.getParameter("pw");
		//String name = request.getParameter("name");
		String eMail = request.getParameter("eMail");
		String address = request.getParameter("address");
		System.out.println(id + pw +eMail + address);
		
		MemberDao dao = MemberDao.getInstance();
		MemberDto dto = new MemberDto();
		dto.setId(id);
		dto.setPw(pw);
		//dto.setName(name);
		dto.seteMail(eMail);
		dto.setAddress(address);
		//dto.setrDate(new Timestamp(System.currentTimeMillis()));//정보 변경 시간
		
		String json_data = "";
		int checkNum = dao.updateMember(dto);
		if(checkNum == 1) {
			json_data = "{\"code\":\"success\", \"desc\":\"정보 변경 성공\"}";
		}else {
			json_data = "{\"code\":\"fail\", \"desc\":\"정보 변경 실패\"}";
		}
		
		response.setContentType("application/json; charset=UTF-8");
		PrintWriter writer = response.getWriter();
		writer.println(json_data);
		writer.close();
	}

}
