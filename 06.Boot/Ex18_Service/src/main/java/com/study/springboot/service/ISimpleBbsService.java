package com.study.springboot.service;

import java.util.List;
import java.util.Map;

import com.study.springboot.dto.SimpleBbsDto;

public interface ISimpleBbsService {
	
	public List<SimpleBbsDto> list();
	public SimpleBbsDto view(String id);
	public int write(Map<String, String> map);
	public int delete(String id);
	public int count();
}
