package com.study.project.dao;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Param;

import com.study.project.dto.FileDto;

public interface FileDao {

	public ArrayList<FileDto> listDao(@Param("_nStart")int nStart, @Param("_nEnd")int nEnd);
	public int articleCount();
	public void insertFile(@Param("_fName")String fName, @Param("_fTitle")String fTitle, 
			@Param("_fContent")String fContent, @Param("_fileName")String fileName,
			@Param("_orgfileName")String orgfileName);
	public FileDto filecontentView(@Param("_fId")String fId);
	public void upHit(@Param("_fId")String fId);
	public void filedelete(@Param("_fId")String fId);
}
