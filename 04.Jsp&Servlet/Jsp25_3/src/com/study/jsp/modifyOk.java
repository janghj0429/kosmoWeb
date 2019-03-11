package com.study.jsp;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class modifyOk implements Service {
	
	public modifyOk() {
		
	}
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{
		System.out.println("modify ok");
		
		MemberDto dto = new MemberDto();
		MemberDao dao = MemberDao.getInstance();
		HttpSession session = request.getSession();
		String id = (String)session.getAttribute("id");
		
		dto.setId(id);
		dto.setPw(request.getParameter("pw"));
		//dto.setName(request.getParameter("name"));
		dto.seteMail(request.getParameter("eMail"));
		dto.setAddress(request.getParameter("address"));
		
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter writer = response.getWriter();
		
		int ri = dao.updateMember(dto);
		if(ri == 1)	{
			System.out.println("변경 성공");

			//html 출력
			writer.println("<html><head></head><body>");
			writer.println("<script language=\"javascript\">");
			writer.println("	alert(\"정보 수정 성공.\");");
			writer.println("	document.location.href=\"main.jsp\";");
			writer.println("</script>");
			writer.println("</body></html>");
			writer.close();

		}else {
			//html 출력
			writer.println("<html><head></head><body>");
			writer.println("<script language=\"javascript\">");
			writer.println("	alert(\"정보 수정 실패.\");");
			writer.println("	document.location.href=\"modify.jsp\";");
			writer.println("</script>");
			writer.println("</body></html>");
			writer.close();

		}
	}
}
