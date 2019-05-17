package com.study.springboot.dao;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ICardDao {
	public void payCard(String consumerId, String amount);
}
