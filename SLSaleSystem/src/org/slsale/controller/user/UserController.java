package org.slsale.controller.user;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.slsale.pojo.user.User;
import org.slsale.service.user.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class UserController {
	private Logger logger = Logger.getLogger(UserController.class);
	/**
	 * 注入service
	 */
	@Resource
	private UserService userService;
	/**
	 * 欢迎页
	 * @return
	 */
	@RequestMapping("/index.html")
	public String index(){
		logger.debug("UserController welcome slsalesystem===============");
		return "index";
	}
	/**
	 * 登录
	 * @return
	 */
	@RequestMapping("/login.html")
	public String login(){
		return "login";
	}
	/**
	 * 用户输入
	 * @param user
	 * @return
	 */
	@RequestMapping("/dologin.html")
	public ModelAndView doLogin(User user){
		logger.debug("doLogin=====>loginCode: " + user.getLoginCode() + 
				"---password: "+user.getPassword());
		try {
			user = userService.getLoginUser(user);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ModelAndView("loginsuccess");
	}
	/**
	 * 退出
	 * @return
	 */
	@RequestMapping("/exit.html")
	public String exit(){
		return "exit";
	}
	/**
	 * 注册
	 * @return
	 */
	@RequestMapping("/register.html")
	public String register(){
		return "register";
	}
	/**
	 * 注册输入
	 * @param user
	 * @return
	 */
	@RequestMapping("doregister.html")
	public ModelAndView doRegister(User user){
		try {
			int f = userService.addUser(user);
			if(f > 0){
				user = userService.getLoginUser(user);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ModelAndView("regsuccess");
	}
	
}
