package com.study.jsp;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class joinOk implements Service {
	
	public joinOk() {
		
	}
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{
		System.out.println("join ok");
		
		request.setCharacterEncoding("UTF-8");
		MemberDto dto = new MemberDto();
		MemberDao dao = MemberDao.getInstance();
		
		dto.setId(request.getParameter("id"));
		dto.setPw(request.getParameter("pw"));
		dto.setName(request.getParameter("name"));
		dto.seteMail(request.getParameter("eMail"));
		dto.setrDate(new Timestamp(System.currentTimeMillis()));
		dto.setAddress(request.getParameter("address"));
		
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter writer = response.getWriter();
				
		if(dao.confirmId(dto.getId()) == MemberDao.MEMBER_EXISTENT){
			//html 출력
			writer.println("<html><head></head><body>");
			writer.println("<script language=\"javascript\">");
			writer.println("	alert(\"아이디가 이미 존재 합니다.\");");
			writer.println("	history.back();");
			writer.println("</script>");
			writer.println("</body></html>");
			writer.close();
		}else{
			int ri = dao.insertMember(dto);
			if(ri == MemberDao.MEMBER_JOIN_SUCCESS){
				HttpSession session = request.getSession();
				session.setAttribute("id", dto.getId());
				session.setAttribute("name", dto.getName());
				session.setAttribute("pw", dto.getPw());
				
				//html 출력
				writer.println("<html><head></head><body>");
				writer.println("<script language=\"javascript\">");
				writer.println("	alert(\"회원가입을 축하 합니다.\");");
				writer.println("	document.location.href=\"login.jsp\";");
				writer.println("</script>");
				writer.println("</body></html>");
				writer.close();
				System.out.println("가입 성공");
			}else{
				//html출력
				writer.println("<html><head></head><body>");
				writer.println("<script language=\"javascript\">");
				writer.println("	alert(\"회원가입에 실패 했습니다.\");");
				writer.println("	document.location.href=\"login.jsp\";");
				writer.println("</script>");
				writer.println("</body></html>");
				writer.close();
				System.out.println("에러 발생 가입 실패");;
			}
		}
	}
}
