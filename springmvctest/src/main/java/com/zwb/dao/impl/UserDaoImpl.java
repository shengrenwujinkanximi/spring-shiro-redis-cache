package com.zwb.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import com.zwb.dao.UserDao;
import com.zwb.vo.User;

@Repository("userDao")
public class UserDaoImpl implements UserDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public User getPwdQueryByUserName(String userName) {
		String sql = "select * from t_user where name=?";
		List<User> list = jdbcTemplate.query(sql, new String[] {userName}, new RowMapper<User>() {

			@Override
			public User mapRow(ResultSet rs, int rowNum) throws SQLException {
				User user = new User();
				user.setUserName(rs.getString("name"));
				user.setPassword(rs.getString("pwd"));
				return user;
			}
		});
		
		if(CollectionUtils.isEmpty(list)) {
			return null;
		}
		return list.get(0);
	}

	@Override
	public List<String> geRolesQueryByUserName(String userName) {
		String roleSql = "select rolename from t_user_role where username=?";
		return jdbcTemplate.query(roleSql, new String[] {userName}, new RowMapper<String>() {

			@Override
			public String mapRow(ResultSet rs, int rowNum) throws SQLException {
				return rs.getString("rolename");
			}
			
		});
	}

}
