package io.dabing.shiro.controller;

import com.ford.mediadata.mgt.entity.security.OperationType;
import com.ford.mediadata.mgt.lisenter.LogUtil;

import io.dabing.shiro.service.ShiroService;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by yangkui on 15/10/29. 用户登录逻辑校验
 */
@Controller
public class LoginController {
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private ShiroService shiroService;

	// 跳转到登陆页面
	@RequestMapping(value = "/toLogin.do")
	public String toLogin(HttpServletRequest request) {
		return "/WEB-INF/jsp/login.jsp";
	}

	@RequestMapping(value = "/login.do", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, String> login(@RequestBody LoginVo loginVo, HttpServletRequest request) {
		Map<String, String> retval = new HashMap<String, String>();
		String errorMsg = "";
		HttpSession session = request.getSession(true);
		if (StringUtils.isEmpty(loginVo.getValidCode())) {
			retval.put("errorMsg", "验证码不能为空!");
			return retval;
		}

		String verifyCode = (String) session.getAttribute(VerifyCodeController.VERFIY_KEY);
		if (verifyCode == null || !loginVo.getValidCode().toLowerCase().equals(verifyCode.toLowerCase())) {
			retval.put("errorMsg", "验证码不正确!");
			session.setAttribute(VerifyCodeController.VERFIY_KEY, StringUtils.EMPTY);// 清空已有的验证码，防止暴力破解
			return retval;
		} else {
			session.setAttribute(VerifyCodeController.VERFIY_KEY, StringUtils.EMPTY);// 清空已有的验证码，防止暴力破解
		}

		UsernamePasswordToken token = new UsernamePasswordToken(loginVo.getUserName(), loginVo.getPassword());
		// 获取用户（不一定是登录的用户）
		Subject currentUser = SecurityUtils.getSubject();

		try {
			// 校验当前用户是否登录成功，如果登录不成功该方法会抛出一些指定的异常
			// 这里会调用AuthorizingRealm类当中的doGetAuthenticationInfo(AuthenticationToken
			// token)方法
			currentUser.login(token);
			// 获取用户额外的属性,便于页面上获取
			Map<String, String> attrs = shiroService.getUserAttr(loginVo.getUserName());
			retval.putAll(attrs);
		} catch (AuthenticationException e) {
			logger.error("用户{},登录不成功,原因：{}", loginVo.getUserName(), e);
			errorMsg = e.getMessage();
		}

		retval.put("errorMsg", errorMsg);
		return retval;
	}

	@RequestMapping(value = "/logout.do", method = RequestMethod.POST)
	@ResponseBody
	public void logout(HttpServletRequest request) {
		// 获取用户（不一定是登录的用户）
		Subject currentUser = SecurityUtils.getSubject();
		if (currentUser != null && currentUser.getPrincipal() != null) {
			// 记录日志操作日志
			LogUtil.writer(OperationType.USER_LOGOUT, currentUser.getPrincipal().toString());
			currentUser.logout();
		}
	}

}
