package com.study.project;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.study.project.controller.BPageInfo;
import com.study.project.dao.BoardDao;

@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

	@Autowired
	private SqlSession sqlSession;
	
	private HttpSession session = null;
	
	int listCount = 5;	//한 페이지당 보여줄 게시물의 갯수
	int pageCount = 5;	//하단에 보여줄 페이지 리스트의 갯수
	int nPage = 1;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		
		//return "main"; 로그인, 가입 추가하고 메인
		return "home";
	}
	
	@RequestMapping("/list")
	public String list(HttpServletRequest request, Model model) {
		//ArrayList<ContentDto> dtos = dao.listDao();
		session = request.getSession();
		//int nPage = 1;
		
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
		
		BoardDao dao = sqlSession.getMapper(BoardDao.class);
		int nTotalPage = dao.articleCount();
		BPageInfo pinfo = articlePage(nPage, nTotalPage);
		
		model.addAttribute("page", pinfo);
		
		//현재 페이지를 임의로 최대보다 크게, 최소보다 작게 넣을 수 있으므로
		//체크된 값을 사용한다.
		nPage = pinfo.getCurPage();
		session.setAttribute("cpage", nPage);
		
		int nStart = (nPage -1) * listCount + 1;
		int nEnd = (nPage - 1) * listCount + listCount;
		
		model.addAttribute("list", dao.listDao(nStart, nEnd));
		
		return "board/list";
	}
	
	public BPageInfo articlePage(int curPage, int nTotalCount) {
		
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
		
		BPageInfo pinfo = new BPageInfo();
		//pinfo.setBoardCategory(boardCategory);
		pinfo.setTotalCount(nTotalCount);
		pinfo.setListCount(listCount);
		pinfo.setTotalPage(totalPage);
		pinfo.setCurPage(curPage);
		pinfo.setPageCount(pageCount);
		pinfo.setStartPage(startPage);
		pinfo.setEndPage(endPage);
		
		return pinfo;		
	}
	
	@RequestMapping("/writeForm")
	public String writeForm() {
		
		return "board/writeForm";
	}
	
	@RequestMapping("/write")
	public String write(HttpServletRequest request, Model model) {
		
		BoardDao dao = sqlSession.getMapper(BoardDao.class);
		System.out.println(Integer.parseInt(request.getParameter("bCategory")));
		System.out.println(request.getParameter("bName"));
		System.out.println(request.getParameter("bTitle"));
		System.out.println(request.getParameter("bContent"));
		
		dao.writeDao(Integer.parseInt(request.getParameter("bCategory")), request.getParameter("bName"),
				request.getParameter("bTitle"), request.getParameter("bContent"));
		
		session = request.getSession();
		session.setAttribute("cpage", 1);
		
		return "redirect:list";
	}
	
	@RequestMapping("/content_view")
	public String content_view(HttpServletRequest request, Model model) {
		
		BoardDao dao = sqlSession.getMapper(BoardDao.class);
		if(request.getParameter("kind").equals("view")) {
			dao.upHit(request.getParameter("bId"));
		}//히트수 컨텐츠뷰에만 넣어도 된다면 kind파라메터 없어도됨.
		model.addAttribute("content_view", dao.contentDao(request.getParameter("bId")) );
		
		return "board/content_view";
	}
	
	@RequestMapping("/modify_view")
	public String modify_view(HttpServletRequest request, Model model) {
		
		BoardDao dao = sqlSession.getMapper(BoardDao.class);
		if(request.getParameter("kind").equals("view")) {
			dao.upHit(request.getParameter("bId"));
		}
		model.addAttribute("content_view", dao.contentDao(request.getParameter("bId")) );
		return "board/modify_view";
	}
	
	@RequestMapping("/modify")
	public String modify(HttpServletRequest request, Model model) {
		
		BoardDao dao = sqlSession.getMapper(BoardDao.class);
		dao.modifyDao(request.getParameter("bId"), request.getParameter("bTitle"), request.getParameter("bContent") );
		
		return "redirect:list";
	}
	
	@RequestMapping("/reply_view")
	public String reply_view(HttpServletRequest request, Model model) {
		
		BoardDao dao = sqlSession.getMapper(BoardDao.class);	
		model.addAttribute("reply_view", dao.reply_view(request.getParameter("bId")) );
		
		return "board/reply_view";
	}
	
	@RequestMapping("/reply")
	public String reply(HttpServletRequest request, Model model) {
		
		
		BoardDao dao = sqlSession.getMapper(BoardDao.class);
		dao.replyShape(Integer.parseInt(request.getParameter("bGroup")), 
					   Integer.parseInt(request.getParameter("bStep")) );
		
		dao.replyDao(request.getParameter("bCategory"),
					 request.getParameter("bName"), request.getParameter("bTitle"),
					 request.getParameter("bContent"), Integer.parseInt(request.getParameter("bGroup")),
					 Integer.parseInt(request.getParameter("bStep")) + 1, 
					 Integer.parseInt(request.getParameter("bIndent")) + 1);
		
		return "redirect:list";
	}
	
	@RequestMapping("/view")
	public String view() {
		
		return "/view";
	}
	
	@RequestMapping("/delete")
	public String delete(HttpServletRequest request, Model model) {
		BoardDao dao = sqlSession.getMapper(BoardDao.class);
		dao.deleteDao(request.getParameter("bId"));
		return "redirect:list";
	}
	
	
	
}
