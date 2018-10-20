package com.zwb.dao;

import java.util.List;

import com.zwb.vo.User;

public interface UserDao {

	User getPwdQueryByUserName(String userName);

	List<String> geRolesQueryByUserName(String userName);

}
