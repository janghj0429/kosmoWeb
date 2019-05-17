package com.study.springboot.jdbc;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class UserDAO {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public List<UserDTO> listForBeanPropertyRowMapper(){
		String query = "select * from myuser";
		List<UserDTO> list = jdbcTemplate.query(
				query, new BeanPropertyRowMapper<UserDTO>(UserDTO.class));
		
		//for(UserDTO my : list) {
		// System.out.println(my);
		// }
		
		return list;
	}
	
	public int insert(UserDTO user) {
		String query = "insert into myuser(id, name) values(?, ?)";
		return jdbcTemplate.update(query, user.getId(), user.getName());
	}
}
