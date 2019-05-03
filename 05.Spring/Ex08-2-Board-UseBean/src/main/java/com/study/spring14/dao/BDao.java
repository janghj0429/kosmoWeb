package com.study.spring14.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.study.spring14.controller.BPageInfo;
import com.study.spring14.dto.BDto;


public class BDao {
	
	DataSource dataSource;
	
	int listCount = 5;	//한페이지당 보여줄 게시물의 갯수
	int pageCount = 5;	//하단에 보여줄페이지 리스트의 갯수
	
	private static BDao instance = new BDao();
	private BDao() {
		try {
			Context context = new InitialContext();
			dataSource = (DataSource)context.lookup("java:comp/env/jdbc/Oracle11g");
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static BDao getInstance() {
		return instance;
	} // 프라이빗 을 퍼블릭스태틱 으로 : 싱글턴패턴
	
	public BPageInfo articlePage(int curPage){
		
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
	
	public void write(String bName, String bTitle, String bContent) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = dataSource.getConnection();
			String query = "insert into mvc_board " + 
						   " (bId, bName, bTitle, bContent, bHit, bGroup, bStep, bIndent) " +
						   " values " +
						   " (mvc_board_seq.nextval, ?, ?, ?, 0, mvc_board_seq.currval, 0, 0 )";
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, bName);
			pstmt.setString(2, bTitle);
			pstmt.setString(3, bContent);
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
	
	public ArrayList<BDto> list(){
		
		ArrayList<BDto> dtos = new ArrayList<BDto>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;
		try {
			con = dataSource.getConnection();
			String query = "select bId, bName, bTitle, bContent, bDate, bHit, bGroup, bStep, bIndent " + 
                    "  from mvc_board " +
	           " order by bGroup desc, bStep asc";
			
			pstmt = con.prepareStatement(query);
			resultSet = pstmt.executeQuery();
			
			while(resultSet.next()) {
				int bId = resultSet.getInt("bId");
				String bName = resultSet.getString("bName");
				String bTitle = resultSet.getString("bTitle");
				String bContent = resultSet.getString("bContent");
				Timestamp bDate = resultSet.getTimestamp("bDate");
				int bHit = resultSet.getInt("bHit");
				int bGroup = resultSet.getInt("bGroup");
				int bStep = resultSet.getInt("bStep");
				int bIndent = resultSet.getInt("bIndent");
				
				BDto dto = new BDto(bId, bName, bTitle, bContent, bDate,
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
//	public ArrayList<BDto> list(int curPage){
//		
//		ArrayList<BDto> dtos = new ArrayList<BDto>();
//		Connection con = null;
//		PreparedStatement pstmt = null;
//		ResultSet resultSet = null;
//		
//		int nStart = (curPage - 1) * listCount + 1;
//		int nEnd = (curPage - 1) * listCount + listCount;
//		
//		try {
//			con = dataSource.getConnection();
//			
//			String query = "select * " +
//						   " from(" +
//						   "  select rownum num, A.* " +
//						   "    from(" + 
//						   "     select * " +
//						   "      from mvc_board " +
//						   "      order by bgroup desc, bstep asc) A " +
//						   "      where rownum <= ?) B " + 
//						   " where b.num >= ?";
//			pstmt = con.prepareStatement(query);
//			pstmt.setInt(1, nEnd);
//			pstmt.setInt(2, nStart);
//			resultSet = pstmt.executeQuery();
//			
//			while(resultSet.next()) {
//				System.out.println("3");
//				int bId = resultSet.getInt("bId");
//				String bName = resultSet.getString("bName");
//				String bTitle = resultSet.getString("bTitle");
//				String bContent = resultSet.getString("bContent");
//				Timestamp bDate = resultSet.getTimestamp("bDate");
//				int bHit = resultSet.getInt("bHit");
//				int bGroup = resultSet.getInt("bGroup");
//				int bStep = resultSet.getInt("bStep");
//				int bIndent = resultSet.getInt("bIndent");
//				System.out.println("4");
//				BDto dto = new BDto(bId, bName, bTitle, bContent, bDate,
//									bHit, bGroup, bStep, bIndent);
//				dtos.add(dto);
//			}
//		}catch(Exception e) {
//			e.printStackTrace();
//		}finally {
//			try {
//				if(resultSet != null) resultSet.close();
//				if(pstmt != null) pstmt.close();
//				if(con != null) con.close();
//			}catch(Exception e2) {
//				e2.printStackTrace();
//			}
//		}
//		
//		return dtos;
//	}
	
	public BDto contentView(String strID, String kind) {
		System.out.println("6");
		if("view".equals(kind) ) {
			upHit(strID);			
		}
		System.out.println("7");
		BDto dto = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;
	
		try{
			con = dataSource.getConnection();
			
			String query = "select * from mvc_board where bId = ?";
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, Integer.parseInt(strID));
			resultSet = pstmt.executeQuery();
			System.out.println("8");
			if(resultSet.next()) {
				int bId = resultSet.getInt("bId");
				String bName = resultSet.getString("bName");
				String bTitle = resultSet.getString("bTitle");
				String bContent = resultSet.getString("bContent");
				Timestamp bDate = resultSet.getTimestamp("bDate");
				int bHit = resultSet.getInt("bHit");
				int bGroup = resultSet.getInt("bGroup");
				int bStep = resultSet.getInt("bStep");
				int bIndent = resultSet.getInt("bIndent");
				System.out.println("9");
				dto = new BDto(bId, bName, bTitle, bContent, bDate,
						bHit, bGroup, bStep, bIndent);
				System.out.println("10");
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
	
	public void modify(String bId, String bName, String bTitle, String bContent)
	{
		Connection con = null;
		PreparedStatement pstmt = null;
		
		String query = "update mvc_board " +
					   " set bName = ?, " +
					   "	 bTitle = ?, " +
					   "	 bContent = ? " +
					   " where bId = ?";
		
		try {
			con = dataSource.getConnection();
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, bName);
			pstmt.setString(2, bTitle);
			pstmt.setString(3, bContent);
			pstmt.setString(4, bId);
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
	
	public void delete(String bId)
	{
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = dataSource.getConnection();
			String query = "delete from mvc_board where bId = ?";
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, Integer.parseInt(bId));
			int rn = pstmt.executeUpdate();
			System.out.println(rn);
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
	
	public BDto reply_view(String str) {
		
		BDto dto = null;
		
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
				int bId = resultSet.getInt("bId");
				String bName = resultSet.getString("bName");
				String bTitle = resultSet.getString("bTitle");
				String bContent = resultSet.getString("bContent");
				Timestamp bDate = resultSet.getTimestamp("bDate");
				int bHit = resultSet.getInt("bHit");
				int bGroup = resultSet.getInt("bGroup");
				int bStep = resultSet.getInt("bStep");
				int bIndent = resultSet.getInt("bIndent");
				
				dto = new BDto(bId, bName, bTitle, bContent, bDate,
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
	
	public void reply(String bId, String bName, String bTitle, String bContent, 
					 String bGroup, String bStep, String bIndent)
	{
		replyShape(bGroup, bStep);
		
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = dataSource.getConnection();
			String query = "insert into mvc_board " +
						   " (bId, bName, bTitle, bContent, bGroup, bStep, bIndent) " +
						   " values (mvc_board_seq.nextval, ?, ?, ?, ?, ?, ?)";
			pstmt = con.prepareStatement(query);
			
			pstmt.setString(1, bName);
			pstmt.setString(2, bTitle);
			pstmt.setString(3, bContent);
			pstmt.setInt(4, Integer.parseInt(bGroup));
			pstmt.setInt(5, Integer.parseInt(bStep) + 1);
			pstmt.setInt(6, Integer.parseInt(bIndent) + 1);
			
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
	
	private void replyShape(String strGroup, String strStep)
	{
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
	
}
