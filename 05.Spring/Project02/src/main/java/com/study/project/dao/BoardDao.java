package com.study.project.dao;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Param;

import com.study.project.dto.BoardDto;

public interface BoardDao {

	public ArrayList<BoardDto> listDao(@Param("_nStart")int nStart, @Param("_nEnd")int nEnd);
	public int articleCount();
	public void writeDao(@Param("_bCategory")int bCategory, 
			@Param("_bName")String bName, @Param("_bTitle")String bTitle, @Param("_bContent")String bContent);
	public BoardDto contentDao(@Param("_bId")String bId);
	public void upHit(@Param("_bId")String bId);
	public void modifyDao(@Param("_bId")String bId, @Param("_bTitle")String bTitle, @Param("_bContent")String bContent);
	public BoardDto viewDao(String strID);
	public void deleteDao(@Param("_bId")String bId);
	public BoardDto reply_view(@Param("_bId")String bId);
	public void replyShape(@Param("_bGroup")int bGroup, @Param("_bStep")int bStep);
	public void replyDao(@Param("_bCategory")String bCategory, @Param("_bName")String bName, 
			@Param("_bTitle")String bTitle, @Param("_bContent")String bContent, 
			@Param("_bGroup")int bGroup, @Param("_bStep")int bStep, 
			@Param("_bIndent")int bIndent);
	
}
