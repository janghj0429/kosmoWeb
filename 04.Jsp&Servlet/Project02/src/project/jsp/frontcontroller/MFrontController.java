package project.jsp.frontcontroller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import project.jsp.command.MCommand;
import project.jsp.command.MJoinCommand;
import project.jsp.command.MLogInCommand;

@WebServlet("/MFrontController")
public class MFrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
    public MFrontController() {
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
		MCommand mcommand = null;
		String viewPage = null;
		
		String check = request.getParameter("mid");
		System.out.println(check);
		
		HttpSession session = null;
		session = request.getSession();
		
		String uri = request.getRequestURI();
		System.out.println("uri : " + uri);
		String conPath = request.getContextPath();
		System.out.println("conPath : " + conPath);
		String com = uri.substring(conPath.length());
		System.out.println("command : " + com);
		
		if(com.equals("/login.do")) {
			mcommand = new MLogInCommand();
			mcommand.execute(request, response);
		}else if(com.equals("/join.do")) {
			mcommand = new MJoinCommand();
			mcommand.execute(request, response);
		}
		
	}
}
