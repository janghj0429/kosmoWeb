package com.study.jsp;

import java.io.IOException;
import java.sql.Timestamp;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("*.do")
public class FrontCon extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
    public FrontCon() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException
	{
		System.out.println("doGet");
		actionDo(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException 
	{
		System.out.println("doPost");
		actionDo(request, response);
	}
	
	private void actionDo(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{
		request.setCharacterEncoding("UTF-8");
		System.out.println("actionDo");
		
		String uri = request.getRequestURI();
		System.out.println("uri : " + uri);
		String conPath = request.getContextPath();
		System.out.println("conPath : " + conPath);
		String command = uri.substring(conPath.length());
		System.out.println("command : " + command);
		
		if(command.equals("/loginOk.do")) {
			loginOk(request, response);
		}else if(command.equals("/modifyOk.do")) {
			modifyOk(request, response);
		}else if(command.equals("/joinOk.do")) {
			joinOk(request, response);
		}else if(command.equals("/logoutOk.do")) {
			logoutOk(request, response);
		}
	}
	
	public void loginOk(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{
		request.setCharacterEncoding("UTF-8");
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		System.out.println(id + pw);
		
		MemberDao dao = MemberDao.getInstance();
		MemberDto dto = new MemberDto();
		
		int checkNum = dao.userCheck(id, pw);
		
		if(checkNum != 1) {
			System.out.println("로그인 실패");	
			response.sendRedirect("login.jsp");
		}
		else {
			dto = dao.getMember(id);
			HttpSession session = request.getSession();
			session.setAttribute("id", id);
			session.setAttribute("name", dto.getName());
			session.setAttribute("ValidMem", "yes");
			System.out.println(id +":"+ dto.getName());
			System.out.println("로그인 성공");
			
			response.sendRedirect("main.jsp");
		}
	}
	
	public void modifyOk(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{

		MemberDto dto = new MemberDto();
		MemberDao dao = MemberDao.getInstance();
		
		HttpSession session = request.getSession();
		String id = (String)session.getAttribute("id");
		
		dto.setId(id);
		dto.setPw(request.getParameter("pw"));
		//dto.setName(request.getParameter("name"));
		dto.seteMail(request.getParameter("eMail"));
		dto.setAddress(request.getParameter("address"));
		
		int ri = dao.updateMember(dto);
		if(ri == 1)	{
			System.out.println("변경 성공");
			//writer.println("");
			response.sendRedirect("main.jsp");
		}else {
			response.sendRedirect("modify.jsp");
		}
	}
	
	public void joinOk(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{
		request.setCharacterEncoding("UTF-8");
		MemberDto dto = new MemberDto();
		MemberDao dao = MemberDao.getInstance();

		//dto.setId(set.getString("id"));	
		//dto.setrDate(new Timestamp(System.currentTimeMillis()));
		
		dto.setId(request.getParameter("id"));
		dto.setPw(request.getParameter("pw"));
		dto.setName(request.getParameter("name"));
		dto.seteMail(request.getParameter("eMail"));
		dto.setrDate(new Timestamp(System.currentTimeMillis()));
		dto.setAddress(request.getParameter("address"));
		
		//String id = request.getParameter("id");
		//int checkNum = dao.confirmId(id);

		if(dao.confirmId(dto.getId()) == MemberDao.MEMBER_EXISTENT){
			System.out.println("아이디가 존재합니다.");
			response.sendRedirect("lonin.jsp");
		}else{
			int ri = dao.insertMember(dto);
			if(ri == MemberDao.MEMBER_JOIN_SUCCESS){
				HttpSession session = request.getSession();
				session.setAttribute("id", dto.getId());
				session.setAttribute("name", dto.getName());
				session.setAttribute("pw", dto.getPw());
				
				System.out.println("가입 성공");
				response.sendRedirect("lonin.jsp");
			}else{
				System.out.println("에러 발생 가입 실패");;
			}
		}
	}
	
	public void logoutOk(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{
		response.sendRedirect("logout.jsp");
	}
	
}
