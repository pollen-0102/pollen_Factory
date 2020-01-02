package com.oracle.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class UserLoginController {
	// �û���½�ύ
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login() throws Exception {
		return "login";
	}

	// û����Ȩ
	@RequestMapping(value = "/refuse", method = RequestMethod.GET)
	public String refuse() throws Exception {
		return "refuse";
	}

	// �˳�
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout() throws Exception {
		// ����
		Subject subject = SecurityUtils.getSubject();
		subject.logout();
		return "login";
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String loginSubmit(Model model, HttpServletRequest request, HttpSession session) throws Exception {
		String authticationError = "";
		Subject subject = SecurityUtils.getSubject();
		String validateCode = (String) session.getAttribute("validateCode");
		if (request.getParameter("randomcode").equals(validateCode)) {

			if (subject.isAuthenticated()) {

				return "redirect:/index";
			} else {
				// shiro����֤�����г��ִ�����쳣��·��ͨ��request����
				String exceptionClassName = (String) request.getAttribute("shiroLoginFailure");
				if (UnknownAccountException.class.getName().equals(exceptionClassName)) {
					authticationError = "未知用户";
				} else if (IncorrectCredentialsException.class.getName().equals(exceptionClassName)) {
					authticationError = "不合法凭证";
				}
				model.addAttribute("authticationError", authticationError);
				return "login";
			}
		} else {
			authticationError = "验证码错误";
			model.addAttribute("authticationError", authticationError);
			return "login";
		}
	}

	// ������ҳ
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String index(HttpServletRequest request, HttpSession session) throws Exception {
		// �ж��û��Ƿ��¼������Ѿ���¼�� ����ת��ҳ
		Subject subject = SecurityUtils.getSubject();
		String activeUser = (String) subject.getPrincipal();
		session.setAttribute("activeUser", activeUser);
		return "index";
	}

}
