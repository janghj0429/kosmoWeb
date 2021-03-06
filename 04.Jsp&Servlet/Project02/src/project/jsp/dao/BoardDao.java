package project.jsp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import project.jsp.dto.BoardDto;
import project.jsp.frontcontroller.BPageInfo;

public class BoardDao {

	DataSource dataSource;
	
	int listCount = 5;	//한페이지당 보여줄 게시물의 갯수
	int pageCount = 5;	//하단에 보여줄페이지 리스트의 갯수
	
	private static BoardDao instance = new BoardDao();
	private BoardDao() {
		try {
			Context context = new InitialContext();
			dataSource = (DataSource)context.lookup("java:comp/env/jdbc/Oracle11g");
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static BoardDao getInstance() {
		return instance;
	} // 프라이빗 을 퍼블릭스태틱 으로 : 싱글턴패턴
	
	public BPageInfo articlePage(int curPage, int boardCategory){
		
		System.out.println("아티클");
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;
		
		int listCount = 5;	// 한페이지당 보여줄 게시물의 객수
		int pageCount = 5;	// 하단에 보여줄 페이지리스트의 객수
		
		//총 게시물의 갯수
		int totalCount = 0;
		try {
			con = dataSource.getConnection();
			
			String query = "select count(*) as total from ("
						 + "select * from mvc_board where bCategory = ?)";
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, boardCategory);
			resultSet = pstmt.executeQuery();
			
			if(resultSet.next()) {
				totalCount = resultSet.getInt("total");
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(resultSet != null) resultSet.close();
				if(pstmt != null) pstmt.close();
				if(con != null) con.close();
			}catch(Exception e2) {
				e2.printStackTrace();
			}
		}
		
		//총페이지 수
		int totalPage = totalCount / listCount;
		if(totalCount % listCount > 0)
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
		System.out.println("아티클 : " + boardCategory);
		
		BPageInfo pinfo = new BPageInfo();
		pinfo.setBoardCategory(boardCategory);
		pinfo.setTotalCount(totalCount);
		pinfo.setListCount(listCount);
		pinfo.setTotalPage(totalPage);
		pinfo.setCurPage(curPage);
		pinfo.setPageCount(pageCount);
		pinfo.setStartPage(startPage);
		pinfo.setEndPage(endPage);
		
		return pinfo;
	}
	
	public ArrayList<BoardDto> list(int curPage, int boardCategory){
		
		ArrayList<BoardDto> dtos = new ArrayList<BoardDto>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;
		
		int nStart = (curPage - 1) * listCount + 1;
		int nEnd = (curPage - 1) * listCount + listCount;
//		System.out.println("리스트카테고리 : " + Category);
//		System.out.println("start: " + nStart + " end : " + nEnd);
		
		try {
			con = dataSource.getConnection();
			
			String query = "select * from( " +
						   "select rownum num, A.* from( " +
						   "select * from( " +
						   "select * from mvc_board where bcategory=?) " +
						   "order by bgroup desc, bstep asc) A " +
						   "where rownum <= ?) B where b.num >= ? ";
			
//			String query = "select * " +
//						   " from(" +
//						   "  select rownum num, A.* " +
//						   "    from(" + 
//						   "     select * " +
//						   "      from mvc_board where bCategory = ?" +
//						   "      order by bgroup desc, bstep asc) A " +
//						   "      where rownum <= ?) B " + 
//						   " where b.num >= ?";
			System.out.println("here");
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, boardCategory);
			pstmt.setInt(2, nEnd);
			pstmt.setInt(3, nStart);
			resultSet = pstmt.executeQuery();
//			System.out.println( resultSet.getInt("bCategory") + " listDao");
			System.out.println("here2");
			while(resultSet.next()) {
				System.out.println("here3");
				int bCategory = resultSet.getInt("bCategory");
				int bId = resultSet.getInt("bId");
				String bName = resultSet.getString("bName");
				String bTitle = resultSet.getString("bTitle");
				String bContent = resultSet.getString("bContent");
				Timestamp bDate = resultSet.getTimestamp("bDate");
				int bHit = resultSet.getInt("bHit");
				int bGroup = resultSet.getInt("bGroup");
				int bStep = resultSet.getInt("bStep");
				int bIndent = resultSet.getInt("bIndent");
				String mark = checkDate(bDate);
				bTitle = mark + bTitle;
				System.out.println("here4");
				BoardDto dto = new BoardDto(bCategory, bId, bName, bTitle, bContent, bDate,
									bHit, bGroup, bStep, bIndent);
				dtos.add(dto);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(resultSet != null) resultSet.close();
				if(pstmt != null) pstmt.close();
				if(con != null) con.close();
			}catch(Exception e2) {
				e2.printStackTrace();
			}
		}
		
		return dtos;
	}
	
	public BPageInfo articleWholePage(int curPage){
		
		System.out.println("아티클");
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;
		
		int listCount = 5;	// 한페이지당 보여줄 게시물의 객수
		int pageCount = 5;	// 하단에 보여줄 페이지리스트의 객수
		
		//총 게시물의 갯수
		int totalCount = 0;
		try {
			con = dataSource.getConnection();
			
			String query = "select count(*) as total from mvc_board";
	
			pstmt = con.prepareStatement(query);
			resultSet = pstmt.executeQuery();
			
			if(resultSet.next()) {
				totalCount = resultSet.getInt("total");
				System.out.println(totalCount);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(resultSet != null) resultSet.close();
				if(pstmt != null) pstmt.close();
				if(con != null) con.close();
			}catch(Exception e2) {
				e2.printStackTrace();
			}
		}
		
		//총페이지 수
		int totalPage = totalCount / listCount;
		if(totalCount % listCount > 0)
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
		System.out.println("w아티클");
		
		BPageInfo pinfo = new BPageInfo();
		pinfo.setTotalCount(totalCount);
		pinfo.setListCount(listCount);
		pinfo.setTotalPage(totalPage);
		pinfo.setCurPage(curPage);
		pinfo.setPageCount(pageCount);
		pinfo.setStartPage(startPage);
		pinfo.setEndPage(endPage);
		
		return pinfo;
	}
	
	public ArrayList<BoardDto> wholeList(int curPage){
		
		ArrayList<BoardDto> dtos = new ArrayList<BoardDto>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;
		
		int nStart = (curPage - 1) * listCount + 1;
		int nEnd = (curPage - 1) * listCount + listCount;
//		System.out.println("리스트카테고리 : " + Category);
//		System.out.println("start: " + nStart + " end : " + nEnd);
		
		try {
			con = dataSource.getConnection();
			
			String query = "select * from( " +
						   " select rownum num, A.* from( " +
						   " select * from mvc_board order by bgroup desc, bstep asc) A "+
						   " where rownum <= ?) B where b.num >= ? ";
			
			System.out.println("here");
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, nEnd);
			pstmt.setInt(2, nStart);
			resultSet = pstmt.executeQuery();

			System.out.println("here2");
			while(resultSet.next()) {
				System.out.println("here3");
				int bCategory = resultSet.getInt("bCategory");
				int bId = resultSet.getInt("bId");
				String bName = resultSet.getString("bName");
				String bTitle = resultSet.getString("bTitle");
				String bContent = resultSet.getString("bContent");
				Timestamp bDate = resultSet.getTimestamp("bDate");
				int bHit = resultSet.getInt("bHit");
				int bGroup = resultSet.getInt("bGroup");
				int bStep = resultSet.getInt("bStep");
				int bIndent = resultSet.getInt("bIndent");
				String mark = checkDate(bDate);
				bTitle = mark + bTitle;
				System.out.println("here4");
				BoardDto dto = new BoardDto(bCategory, bId, bName, bTitle, bContent, bDate,
									bHit, bGroup, bStep, bIndent);
				dtos.add(dto);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(resultSet != null) resultSet.close();
				if(pstmt != null) pstmt.close();
				if(con != null) con.close();
			}catch(Exception e2) {
				e2.printStackTrace();
			}
		}
		
		return dtos;
	}
	
	public void write(int bCategory, String bName, String bTitle, String bContent) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = dataSource.getConnection();
			String query = "insert into mvc_board " + 
						   " (bCategory, bId, bName, bTitle, bContent, bHit, bGroup, bStep, bIndent) " +
						   " values " +
						   " (?, mvc_board_seq.nextval, ?, ?, ?, 0, mvc_board_seq.currval, 0, 0 )";
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, bCategory);
			pstmt.setString(2, bName);
			pstmt.setString(3, bTitle);
			pstmt.setString(4, bContent);
			int rn = pstmt.executeUpdate();
			System.out.println("write 메서드 실행");
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(pstmt != null) pstmt.close();
				if(con != null) con.close();
			}catch(Exception e2) {
				e2.printStackTrace();
			}
		}
	}
	
	public BoardDto contentView(String strID, String kind) {
		if(kind.equals("view") ) {
			upHit(strID);			
		}
		
		BoardDto dto = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;
	
		try{
			con = dataSource.getConnection();
			
			String query = "select * from mvc_board where bId = ?";
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, Integer.parseInt(strID));
			resultSet = pstmt.executeQuery();
			
			if(resultSet.next()) {
				int bCategory = resultSet.getInt("bCategory");
				int bId = resultSet.getInt("bId");
				String bName = resultSet.getString("bName");
				String bTitle = resultSet.getString("bTitle");
				String bContent = resultSet.getString("bContent");
				Timestamp bDate = resultSet.getTimestamp("bDate");
				int bHit = resultSet.getInt("bHit");
				int bGroup = resultSet.getInt("bGroup");
				int bStep = resultSet.getInt("bStep");
				int bIndent = resultSet.getInt("bIndent");
				
				dto = new BoardDto(bCategory, bId, bName, bTitle, bContent, bDate,
						bHit, bGroup, bStep, bIndent);
				System.out.println("content dao진행");
			}
		}catch(Exception e)	{
			e.printStackTrace();
		}finally{
			try {
				if(resultSet != null) resultSet.close();
				if(pstmt != null) pstmt.close();
				if(con != null) con.close();
			}catch(Exception e2) {
				e2.printStackTrace();
			}
		}
		
		return dto;
	}
	
	private void upHit(String bId) {
		//contentView 메서드에서 호출됨. 히트수 증가 
		//현재는 수정하면 2개씩 증가됨 게시판 작성시에는 수정해서 작업.
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = dataSource.getConnection();
			String query = "update mvc_board set bHit = bHit + 1 where bId = ?";
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, bId);
			
			int rn = pstmt.executeUpdate();
			
		}catch(Exception e)	{
			e.printStackTrace();
		}finally{
			try {
				if(pstmt != null) pstmt.close();
				if(con != null) con.close();
			}catch(Exception e2) {
				e2.printStackTrace();
			}
		}
	}
	
	public void modify(String bId, String bTitle, String bContent)
	{
		Connection con = null;
		PreparedStatement pstmt = null;
		
		String query = "update mvc_board " +
					   " set bTitle = ?, " +
					   "	 bContent = ? " +
					   " where bId = ?";
		
		try {
			con = dataSource.getConnection();
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, bTitle);
			pstmt.setString(2, bContent);
			pstmt.setString(3, bId);
			int rn = pstmt.executeUpdate();
			
		}catch(Exception e)	{
			e.printStackTrace();
		}finally{
			try {
				if(pstmt != null) pstmt.close();
				if(con != null) con.close();
			}catch(Exception e2) {
				e2.printStackTrace();
			}
		}
		
	}
	
	public void delete(String bId) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		String query = "delete from mvc_board " +
					   " where bId = ?";
		
		try {
			con = dataSource.getConnection();
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, bId);
			System.out.println(bId +" delete boardDao진행");
			int rn = pstmt.executeUpdate();
		}catch(Exception e)	{
			e.printStackTrace();
		}finally{
			try {
				if(pstmt != null) pstmt.close();
				if(con != null) con.close();
			}catch(Exception e2) {
				e2.printStackTrace();
			}
		}	
	}
	
	public BoardDto reply_view(String str) {
		
		BoardDto dto = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;
		
		try {
			con = dataSource.getConnection();
			String query = "select * from mvc_board where bId = ?";
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, Integer.parseInt(str));
			resultSet = pstmt.executeQuery();
			
			if(resultSet.next()) {
				int bCategory = resultSet.getInt("bCategory");
				int bId = resultSet.getInt("bId");
				String bName = resultSet.getString("bName");
				String bTitle = resultSet.getString("bTitle");
				String bContent = resultSet.getString("bContent");
				Timestamp bDate = resultSet.getTimestamp("bDate");
				int bHit = resultSet.getInt("bHit");
				int bGroup = resultSet.getInt("bGroup");
				int bStep = resultSet.getInt("bStep");
				int bIndent = resultSet.getInt("bIndent");
				
				dto = new BoardDto(bCategory, bId, bName, bTitle, bContent, bDate,
						bHit, bGroup, bStep, bIndent); 
			}
			
		}catch(Exception e)	{
			e.printStackTrace();
		}finally{
			try {
				if(pstmt != null) pstmt.close();
				if(con != null) con.close();
			}catch(Exception e2) {
				e2.printStackTrace();
			}
		}
		return dto;
	}

	
	public void reply(String bCategory, String bId, String bName, String bTitle,  
			String bContent, String bGroup, String bStep, String bIndent)	{
		
		replyShape(bGroup, bStep);
		
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = dataSource.getConnection();
			String query = "insert into mvc_board " +
						   " (bCategory, bId, bName, bTitle, bContent, bGroup, bStep, bIndent) " +
						   " values (?, mvc_board_seq.nextval, ?, ?, ?, ?, ?, ?)";
			pstmt = con.prepareStatement(query);
			
			pstmt.setInt(1, Integer.parseInt(bCategory));
			pstmt.setString(2, bName);
			pstmt.setString(3, bTitle);
			pstmt.setString(4, bContent);
			pstmt.setInt(5, Integer.parseInt(bGroup));
			pstmt.setInt(6, Integer.parseInt(bStep) + 1);
			pstmt.setInt(7, Integer.parseInt(bIndent) + 1);
			
			int rn = pstmt.executeUpdate();
		}catch(Exception e)	{
			e.printStackTrace();
		}finally{
			try {
				if(pstmt != null) pstmt.close();
				if(con != null) con.close();
			}catch(Exception e2) {
				e2.printStackTrace();
			}
		}
	}
		
	private void replyShape(String strGroup, String strStep) {
			
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = dataSource.getConnection();
			String query = "update mvc_board " +
						   " set bStep = bStep + 1" +
						   " where bGroup = ? and bStep > ?";
			
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, Integer.parseInt(strGroup));
			pstmt.setInt(2, Integer.parseInt(strStep));
			
			int rn = pstmt.executeUpdate();
		}catch(Exception e)	{
			e.printStackTrace();
		}finally{
			try {
				if(pstmt != null) pstmt.close();
				if(con != null) con.close();
			}catch(Exception e2) {
				e2.printStackTrace();
			}
		}
	}	
	
	public BPageInfo articleSearchPage(int curPage, String column, String word){
		
		System.out.println("통합아티클");
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;
		
		int listCount = 5;	// 한페이지당 보여줄 게시물의 객수
		int pageCount = 5;	// 하단에 보여줄 페이지리스트의 객수
		System.out.println(column + word);
		//총 게시물의 갯수
		int totalCount = 0;
		try {
			con = dataSource.getConnection();
			
			String query = "select count(*) as total from " +
						   " mvc_board where " + column +
						   " like " + " '%"+word+"%'";
						   
			pstmt = con.prepareStatement(query);
			//pstmt.setString(1, column);
			//pstmt.setString(1, word);
			resultSet = pstmt.executeQuery();
			
			if(resultSet.next()) {
				totalCount = resultSet.getInt("total");
				System.out.println(totalCount);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(resultSet != null) resultSet.close();
				if(pstmt != null) pstmt.close();
				if(con != null) con.close();
			}catch(Exception e2) {
				e2.printStackTrace();
			}
		}
		
		//총페이지 수
		int totalPage = totalCount / listCount;
		if(totalCount % listCount > 0)
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
		System.out.println("검색아티클 : " + column + word);
		
		BPageInfo pinfo = new BPageInfo();
		pinfo.setBoardColumn(column);
		pinfo.setBoardWord(word);
		pinfo.setTotalCount(totalCount);
		pinfo.setListCount(listCount);
		pinfo.setTotalPage(totalPage);
		pinfo.setCurPage(curPage);
		pinfo.setPageCount(pageCount);
		pinfo.setStartPage(startPage);
		pinfo.setEndPage(endPage);
		
		return pinfo;
	}
	
	public ArrayList<BoardDto> searchList(int curPage, String column, String word){
		
		ArrayList<BoardDto> dtos = new ArrayList<BoardDto>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;
		
		int nStart = (curPage - 1) * listCount + 1;
		int nEnd = (curPage - 1) * listCount + listCount;
//		System.out.println("리스트카테고리 : " + Category);
//		System.out.println("start: " + nStart + " end : " + nEnd);
		
		try {
			con = dataSource.getConnection();
			
			String query = "select * from( " +
						   "select rownum num, A.* from( " +
						   "select * from( " +
						   "select * from mvc_board where "+column+" like "+ " '%"+word+"%'" + ")" +
						   "order by bgroup desc, bstep asc) A " +
						   "where rownum <= ?) B where b.num >= ? ";
			
			System.out.println("통합");
			System.out.println(column + word);
			pstmt = con.prepareStatement(query);
			//pstmt.setString(1, column);
			//pstmt.setString(1, "%"+word+"%");
			pstmt.setInt(1, nEnd);
			pstmt.setInt(2, nStart);
			resultSet = pstmt.executeQuery();
//			System.out.println( resultSet.getInt("bCategory") + " listDao");
			System.out.println("here2");
			while(resultSet.next()) {
				System.out.println("here3");
				int bCategory = resultSet.getInt("bCategory");
				int bId = resultSet.getInt("bId");
				String bName = resultSet.getString("bName");
				String bTitle = resultSet.getString("bTitle");
				String bContent = resultSet.getString("bContent");
				Timestamp bDate = resultSet.getTimestamp("bDate");
				int bHit = resultSet.getInt("bHit");
				int bGroup = resultSet.getInt("bGroup");
				int bStep = resultSet.getInt("bStep");
				int bIndent = resultSet.getInt("bIndent");
				String mark = checkDate(bDate);
				bTitle = mark + bTitle;
				System.out.println("here4");
				BoardDto dto = new BoardDto(bCategory, bId, bName, bTitle, bContent, bDate,
									bHit, bGroup, bStep, bIndent);
				dtos.add(dto);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(resultSet != null) resultSet.close();
				if(pstmt != null) pstmt.close();
				if(con != null) con.close();
			}catch(Exception e2) {
				e2.printStackTrace();
			}
		}
		
		return dtos;
	}
	

	
	public String checkDate(Timestamp bDate) {
		String result = "";	
//		1. 글을 작성하면 작성한 날 하루 동안 new 표시하기
//		java.text.SimpleDateFormat sf = new java.text.SimpleDateFormat("yyyy-MM-dd");
//		String inputDate = sf.format(입력된 날짜);
//		String now = sf.format(new java.util.Date());
//		String mark = "";
//		if(inputDate.equals(now)){
//		 mark = "new";
//		}
		Date boardDate = bDate;
		long now = System.currentTimeMillis();
		long inputDate = boardDate.getTime();
		if(now - inputDate < (1000*60*60*24*2)) {
			result = "new	";
		}		
		return result;
	}
	
	
}
