package com.study.project.controller;

import java.io.File;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.study.project.dao.FileDao;

@Controller
public class FController {

	@Autowired
	private SqlSession sqlSession;
	
	private HttpSession session = null;
	
	int listCount = 5;	//한 페이지당 보여줄 게시물의 갯수
	int pageCount = 5;	//하단에 보여줄 페이지 리스트의 갯수
	int nPage = 1;
	
	@RequestMapping("/filelist")
	public String filelist(HttpServletRequest request, Model model) {
		
		session = request.getSession();
		
		try {
			if(request.getParameter("page") != null) {
				String sPage = request.getParameter("page");
				nPage = Integer.parseInt(sPage);
			}else {
				if(session.getAttribute("cpage") != null) {
					nPage = Integer.parseInt((String) session.getAttribute("cpage"));
					//nPage = (int)session.getAttribute("cpage");
				}
			}
		}catch(Exception e) {
			System.out.println("리스트페이징익셉션");
		}
		
		FileDao dao = sqlSession.getMapper(FileDao.class);
		int nTotalPage = dao.articleCount();
		FPageInfo finfo = articlePage(nPage, nTotalPage);
		
		model.addAttribute("page", finfo);
		
		//현재 페이지를 임의로 최대보다 크게, 최소보다 작게 넣을 수 있으므로
		//체크된 값을 사용한다.
		nPage = finfo.getCurPage();
		session.setAttribute("cpage", nPage);
		
		int nStart = (nPage -1) * listCount + 1;
		int nEnd = (nPage - 1) * listCount + listCount;
		
		model.addAttribute("list", dao.listDao(nStart, nEnd));
		return "fileBoard/filelist";
	}
	
	public FPageInfo articlePage(int curPage, int nTotalCount) {
		
		//총페이지 수
		int totalPage = nTotalCount / listCount;
		if(nTotalCount % listCount > 0)
			totalPage++;
				
		//현재페이지
		int myCurPage = curPage;
		if(myCurPage > totalPage)
			myCurPage = totalPage;
		if(myCurPage < 1)
			myCurPage = 1;
		
		//시작페이지
		int startPage = ( (myCurPage -1)/pageCount ) * pageCount + 1;
		
		//끝페이지
		int endPage = startPage + pageCount - 1;
		if(endPage > totalPage)
			endPage = totalPage;
		System.out.println("아티클 : ");
		
		FPageInfo finfo = new FPageInfo();
		//pinfo.setBoardCategory(boardCategory);
		finfo.setTotalCount(nTotalCount);
		finfo.setListCount(listCount);
		finfo.setTotalPage(totalPage);
		finfo.setCurPage(curPage);
		finfo.setPageCount(pageCount);
		finfo.setStartPage(startPage);
		finfo.setEndPage(endPage);
		
		return finfo;		
	}
	
	@RequestMapping("/upload_view")
	public String upload_view() {
		return "fileBoard/upload_view";
	}
	
	@RequestMapping("/upload")
	public String upload(HttpServletRequest request, Model model) {
		//session.getAttribute("fName");
		
		
		return "fileBoard/upload";
	}
	
	@RequestMapping("/empty")
	public String empty(HttpServletRequest request, Model model) {
		session = request.getSession();
		String fName = (String)session.getAttribute("fName");
		String fTitle = (String)session.getAttribute("fTitle");
		String fContent = (String)session.getAttribute("fContent");
		String fileName = (String)session.getAttribute("fileName");
		String orgfileName = (String)session.getAttribute("orgfileName");
		
		FileDao dao = sqlSession.getMapper(FileDao.class);
		dao.insertFile(fName, fTitle, fContent, fileName, orgfileName);
		System.out.println(fName);
		
		return "redirect:filelist";
	}
	
	@RequestMapping("/filecontent_view")
	public String filecontent_view(HttpServletRequest request, Model model) {
		FileDao dao = sqlSession.getMapper(FileDao.class);
		dao.upHit(request.getParameter("fId"));
		model.addAttribute("filecontent_view", dao.filecontentView(request.getParameter("fId")) );
		
		return "fileBoard/filecontent_view";
	}
	
	@RequestMapping("/filedelete")
	public String filedelete(HttpServletRequest request, Model model) {
		FileDao dao = sqlSession.getMapper(FileDao.class);
		dao.filedelete(request.getParameter("fId"));//나중엔 이름or ID 맞춰서 작성자만 삭제가능하게
		
		return "redirect:filelist";
	}
	
	@RequestMapping("/download")
	public ModelAndView  download(HttpServletRequest req, HttpServletResponse resp, Model model)
		throws Exception 
	{
		String fileName = req.getParameter("fileName");
		String oriFileName = req.getParameter("orgfileName");
	    
	    String saveDirectory = 
	    		req.getSession().getServletContext().getRealPath("/resources/fileFolder");
	    
	    File downloadFile = new File(saveDirectory+"/"+fileName);
	    System.out.println(saveDirectory + "/"+ fileName);
	    
	    if(!downloadFile.canRead()){
	        throw new Exception("File can't read(파일을 찾을 수 없습니다)");
	    }
	    
	    ModelAndView mv = new ModelAndView();
	    mv.setViewName("fileDownloadView");
	  	mv.addObject("downloadFile", downloadFile);
	  	mv.addObject("oriFileName", oriFileName);
	  		 
	  	return mv;
	  	//return new ModelAndView("뷰", "속성명", "값");
	}
	
}
