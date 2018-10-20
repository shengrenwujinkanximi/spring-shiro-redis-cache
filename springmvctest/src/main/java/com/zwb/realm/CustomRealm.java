package com.zwb.realm;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import com.zwb.dao.UserDao;
import com.zwb.vo.User;

public class CustomRealm extends AuthorizingRealm {

	@Autowired
	private UserDao userDao;
	
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		String userName = (String) principals.getPrimaryPrincipal();
		Set<String> roles = getRolesByUserName(userName);
		Set<String> permission = getPermissionByRoleName(userName);
		SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
		simpleAuthorizationInfo.addRoles(roles);
		simpleAuthorizationInfo.addStringPermissions(permission);
		
		return simpleAuthorizationInfo;
	}

	private Set<String> getPermissionByRoleName(String userName) {
		Set<String> permissions = new HashSet<>();
		permissions.add("user:delete");
		permissions.add("user:add");
		return permissions;
	}

	private Set<String> getRolesByUserName(String userName) {
		System.out.println("从数据库中获取数据");
		List<String> list = userDao.geRolesQueryByUserName(userName);
		Set<String> roles = new HashSet<>(list);
		return roles;
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		String userName = (String) token.getPrincipal();
		String pwd = getPwdByUserName(userName);
		if(pwd == null) {
			return null;
		}
		SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(userName, pwd, "customRealm");
		simpleAuthenticationInfo.setCredentialsSalt(ByteSource.Util.bytes(userName));
		return simpleAuthenticationInfo;
	}

	private String getPwdByUserName(String userName) {
		User user = userDao.getPwdQueryByUserName(userName);
		super.setName("customRealm");
		if(user !=null) {
			return user.getPassword();
		}
		return null;
	}
	public static void main(String[] args) {
		Md5Hash md5Hash = new Md5Hash("12345","Mark");
		System.out.println(md5Hash);
	}
}
