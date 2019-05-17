package com.study.springboot.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.study.springboot.dto.SimpleBbsDto;

@Mapper
public interface ISimpleBbsDao {

	public List<SimpleBbsDto> listDao();
	public SimpleBbsDto viewDao(@Param("_id")String id);
	public int writeDao(@Param("_writer")String writer, 
						@Param("_title")String title, 
						@Param("_content")String content);
	public int deleteDao(@Param("_id")String id);
}
