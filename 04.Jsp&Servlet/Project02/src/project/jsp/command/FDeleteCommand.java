package project.jsp.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import project.jsp.dao.FileDao;



public class FDeleteCommand implements BCommand{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
	{
		String fName = request.getParameter("fName");
		
		FileDao dao = FileDao.getInstance();
		dao.filedelete(fName);
	}
}
