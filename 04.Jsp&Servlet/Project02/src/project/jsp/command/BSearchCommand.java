package project.jsp.command;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import project.jsp.dao.BoardDao;
import project.jsp.dto.BoardDto;
import project.jsp.frontcontroller.BPageInfo;

public class BSearchCommand implements BCommand{
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
	{
		int nPage = 1;
		try {
			String sPage = request.getParameter("page");
			nPage = Integer.parseInt(sPage);
			
		}catch(Exception e) {
			
		}
		HttpSession session = null;	
		session = request.getSession();
		
//		String column = request.getParameter("column");
//		String word = request.getParameter("word");
		String column , word;
		System.out.println("세션값"+session.getAttribute("column")+session.getAttribute("word"));
		
		if(request.getParameter("column") != null && request.getParameter("word")!=null) {
			System.out.println("파라미터");
			column = request.getParameter("column");
			word = request.getParameter("word");
			session.setAttribute("column", column);
			session.setAttribute("word", word);
		}else {
			System.out.println("세션");
			column = (String)session.getAttribute("column");
			word = (String)session.getAttribute("word");
		}
		
		
		
		BoardDao dao = BoardDao.getInstance();
		BPageInfo pinfo = dao.articleSearchPage(nPage, column, word);
		request.setAttribute("page", pinfo);
		System.out.println("article 완");
		
		nPage = pinfo.getCurPage();
		
		session.setAttribute("cpage", nPage);
		System.out.println("검색:"+pinfo.getBoardColumn() + pinfo.getBoardWord());
		
		ArrayList<BoardDto>	dtos = dao.searchList(nPage, column, word);
		request.setAttribute("list", dtos);
	}
}
