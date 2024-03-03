package com.eat.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.eat.service.CartService;
import com.eat.util.MyUtil;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//@WebServlet(urlPatterns = "/cart")
@RestController
@RequestMapping("/cart")
public class CartController{
	Logger logger = Logger.getLogger(CartController.class);
	@Autowired
	CartService cartService;

	@GetMapping("/insert")
	String insert(HttpServletRequest request, HttpServletResponse response) throws IOException {
		int xgmenuid = Integer.parseInt(request.getParameter("xgmenuid"));
		logger.debug(xgmenuid);
		String xgmemid = (String) request.getSession().getAttribute("islogin");
		logger.debug(xgmemid);
		if (cartService.insert(xgmemid,xgmenuid)){
			return "success";
		} else {
			return "failure";
		}
	}

	@GetMapping("/delete")
	String delete(HttpServletRequest request, HttpServletResponse response) throws IOException {
		int xgmenuid = Integer.parseInt(request.getParameter("xgmenuid"));
		logger.debug(xgmenuid);
		String xgmemid = (String) request.getSession().getAttribute("islogin");
		logger.debug(xgmemid);
		if (cartService.delete(xgmemid,xgmenuid)){
			return "success";
		} else {
			return "failure";
		}
	}

	@PostMapping("/select")
	List select(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String name = request.getParameter("name");
		logger.debug(name);
		if (name == null) {
			name = "";
		}
		String memid = (String) request.getSession().getAttribute("islogin");
		logger.debug(memid);
		List lst=cartService.select(name, memid);
		logger.debug(lst);
		return lst;
	}
	/**
	 * 可以把方法写在doPost()方法中,在doGet()方法中调用执行,这样,无论你提交的是post还是get方法都可以执行
	 */
//	@Override
//	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		// req.setCharacterEncoding("utf-8");
//		doPost(req, resp);
//	}
//	//
//	@Override
//	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		//获取html页面中op对象的值
//
//		String op = req.getParameter("op");
//		String id = req.getParameter("id");
//		String menuid = req.getParameter("menuid");
//		/**
//		 * response顾名思义就是服务器对浏览器的相应，PrintWriter它的实例就是向前台的JSP页面输出结果，比如
//out.print("Hello World")；
//		 */
//
//		PrintWriter out = resp.getWriter();
//		String sql = null;
//		if (op == null) {
//			op = "";
//		}
//		/**
//		 * 通过req.getsession()获得session 对象 再调用它的getAttibute（String key）方法来获得含有关键字“islogin”的对象！
//		 */
//
//		String memid = (String) req.getSession().getAttribute("islogin");
//		logger.debug(memid);
//		switch (op) {
//		case "insert":
//			sql = "insert into cart(xgmenuid,xgmemid) values('" + menuid + "', '" + memid + "')";
//			logger.debug(sql);
//
//			if (dao.exeUpdate(sql)) {
//				out.print("addcart success!");
//			} else {
//				out.print("addcart failure!");
//			}
//			break;
//		case "delete":
//
//			sql = "delete from cart where xgmenuid=" + menuid+" and xgmemid='"+memid+"'";
//			logger.debug(sql);
//			if (dao.exeUpdate(sql)) {
//				out.print("delete success!");
//			} else {
//				out.print("delete failure!");
//			}
//			break;
//		case "getcart":
//
//			String name = req.getParameter("name");
//			if (name == null) {
//				name = "";
//			}
//			sql = "select menu.xgid,xgpic,xgname,xgprice,xgremark " + " from menu,cart" + " where menu.xgid=cart.xgmenuid"
//					+ " and xgmemid='" + memid + "'" + " and xgname like '%" + name + "%'";
//			logger.debug(sql);
//			List lst = dao.getData(sql);
//			// resp.getWriter().print(lst.size());
//
//			out.println(MyUtil.toJSON(lst));
//			break;
//		default:
//
//			break;
//		}
//
//	}
}
