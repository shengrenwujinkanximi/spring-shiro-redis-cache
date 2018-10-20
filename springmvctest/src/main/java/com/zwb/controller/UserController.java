package com.zwb.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zwb.vo.User;

@Controller
public class UserController {

	@RequestMapping(value="/subLogin",method=RequestMethod.POST,
			produces="application/json;charset=utf-8")
	@ResponseBody
	public String subLogin(User user) {
		Subject subject = SecurityUtils.getSubject();
		UsernamePasswordToken token = new UsernamePasswordToken(user.getUserName(), user.getPassword());
		try {
			token.setRememberMe(user.isRemeberMe());
			subject.login(token);
		} catch (AuthenticationException e) {
			return e.getMessage();
		}
		
		if(subject.hasRole("admin")) {
			return "有admin权限";
		}
		return "无admin权限";
	}
	
	@RequiresRoles("admin")
	@RequestMapping(value="/testRole",method=RequestMethod.GET)
	@ResponseBody
	public String testRole() {
		return "testRole success";
	}
	
	@RequiresRoles("admin1")
	@RequestMapping(value="/testRole1",method=RequestMethod.GET)
	@ResponseBody
	public String testRole1() {
		return "testRole1 success";
	}
	
	@RequestMapping(value="/testRoles",method=RequestMethod.GET)
	@ResponseBody
	public String testRoles() {
		return "testRoles success";
	}
	
	@RequestMapping(value="/testRoles1",method=RequestMethod.GET)
	@ResponseBody
	public String testRoles1() {
		return "testRoles1 success";
	}
	
	@RequestMapping("/logout")
	@ResponseBody
	public String logout() {
		Subject subject = SecurityUtils.getSubject();
		subject.logout();
		return "logout";
	}
	
	@RequestMapping("/hello")
	@ResponseBody
	public String hello() {
		return "hello";
	}
}
