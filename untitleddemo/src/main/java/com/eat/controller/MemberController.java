package com.eat.controller;

import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import com.eat.service.MemberService;
import org.apache.log4j.Logger;
import org.apache.tomcat.dbcp.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/member")
public class MemberController {
	Logger logger = Logger.getLogger(MemberController.class);
	@Autowired
	MemberService memberService;

//	public void init() throws ServletException {
//		try {
//			String path = getServletContext().getRealPath("/");
//			path += "WEB-INF\\classes\\log4j.properties";
//			logger.debug(path);
//			PropertyConfigurator.configure(path);
//		} catch (Exception e) {
//			logger.debug(e.getMessage());
//		}
//	}

	@GetMapping("/check")
	String check(HttpServletRequest request) throws IOException {
		String xgmemid = request.getParameter("xgmemid");
		logger.debug(xgmemid);
		if (memberService.check(xgmemid)) {
			return "have";
		} else {
			return "meiyou";
		}
	}

	@PostMapping("/register")
	String register(HttpServletRequest request, HttpServletResponse response, @RequestParam Map map) throws IOException {
		logger.debug(map);
		if (memberService.register(map)) {
			response.sendRedirect(request.getServletContext().getContextPath() + "/login.html");
			return "success";
		} else {
			//response.sendRedirect(request.getServletContext().getContextPath() + "/register.html");
		}
		return "failure";
	}

	@GetMapping("/login")
	String login(HttpServletRequest request, @RequestParam Map map) throws IOException {
		logger.debug(map);
		if (memberService.login(map)) {
			request.getSession().setAttribute("islogin", map.get("xgmemid"));
			return ("success");
		} else {
			request.getSession().setAttribute("islogin", "_null");
			return ("failure");
		}
	}

	@GetMapping("/logout")
	void logout(HttpServletRequest request,HttpServletResponse response) throws IOException {
		request.getSession().setAttribute("islogin", "_null");
		response.sendRedirect("login.html");
	}

	@GetMapping("/islogin")
	void islogin(HttpServletRequest request,HttpServletResponse response) throws IOException {
		String islogin=(String) request.getSession().getAttribute("islogin");
		if(islogin==null) {
			islogin="_null";
		}
		response.getWriter().print(islogin);
	}

//	@Override
//	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		// TODO Auto-generated method stub
//		// login/register
//		//获取请求参数
//		String op = req.getParameter("op");
//		String memid = req.getParameter("memid");
//		String mempass = req.getParameter("mempass");
//		String memalias = req.getParameter("memalias");
//		// menu
//		String name = req.getParameter("name");
//		String price = req.getParameter("price");
//		String pic = req.getParameter("pic");
//		String remark = req.getParameter("remark");
//		//PrintWriter它的实例就是向前台的页面输出结果
//		//获取一个向浏览器输出的对象
//		PrintWriter out = resp.getWriter();
//		String sql = null;// 初始化
////		resp.getWriter().print(sql);
//		if (op == null) {
//			op = "";
//		}
//		switch (op) {
//		case "login":
//			sql = "select * from Member where xgmemid='" + memid + "'" + " and xgmempass='" + mempass + "'";
//			logger.debug(sql);
//			if (dao.isExist(sql)) {
//				req.getSession().setAttribute("islogin", memid);
//				// 会话连接
//				out.print("login success!");
//			} else {
//				req.getSession().setAttribute("islogin", "_null");
//				// 会话连接
//				out.print("login failure!");
//			}
//			break;
//		case "logout":
//			//
//			req.getSession().setAttribute("islogin", "_null");// //对islogin对象赋值
//			resp.sendRedirect("login.html");
//		break;
//		case "islogin":
//			//getSession会话连接
//			String islogin=(String) req.getSession().getAttribute("islogin");//取得islogin对象的值
//			if(islogin==null) {
//				islogin="_null";
//			}
//			out.print(islogin);
//			break;
//		case "check":
//			sql = "select * from Member where xgmemid='" + memid + "'";
//			logger.debug(sql);
//			if (dao.isExist(sql)) {
//				out.print("have");
//			} else {
//				out.print("meiyou");
//			}
//			break;
//		case "register":
//			sql = "insert into Member(xgmemid,xgmempass,xgmemalias) values('" + memid + "','" + mempass + "','" + memalias
//					+ "')";
//			logger.debug(sql);
//			if (dao.exeUpdate(sql)) {
//				// out.print("register success");
//				resp.sendRedirect("login.html");
//
//			} else {
//				// out.print("register fail!");
//				resp.sendRedirect("register.html");
//			}
//			break;
//
//		}
//	}
}
