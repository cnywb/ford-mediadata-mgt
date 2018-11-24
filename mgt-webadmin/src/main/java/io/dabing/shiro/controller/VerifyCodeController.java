package io.dabing.shiro.controller;

import io.dabing.shiro.verifycode.VerifyCodeUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by yangkui on 15/10/30. 图片验证码生成器
 */
@Controller
@RequestMapping(value = "/verfyCode.do", method = RequestMethod.GET)
public class VerifyCodeController {
	public static final String VERFIY_KEY = "IO_DABING_SHIRO_VERIFYCODE";

	@RequestMapping
	public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
		response.setContentType("image/jpeg");

		// 生成随机字串
		String verifyCode = VerifyCodeUtils.generateVerifyCode(4);

		// 存入会话session
		HttpSession session = request.getSession(true);
		session.setAttribute(VERFIY_KEY, verifyCode.toLowerCase());
		// 生成图片
		int w = 200, h = 80;
		VerifyCodeUtils.outputImage(w, h, response.getOutputStream(), verifyCode);
	}

}
