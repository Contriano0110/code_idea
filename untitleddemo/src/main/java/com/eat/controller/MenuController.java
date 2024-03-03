package com.eat.controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.eat.entity.Menu;
import com.eat.service.MenuService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

//import org.apache.commons.fileupload.FileItem;
//import org.apache.commons.fileupload.disk.DiskFileItemFactory;
//import org.apache.commons.fileupload.servlet.ServletFileUpload;

//import com.eat.menu.*;
//import com.eat.myutil.*;
import java.util.*;
@RestController
@RequestMapping("/menu")
public class MenuController{
	Logger logger = Logger.getLogger(MenuController.class);

	@Autowired
	MenuService menuService;

	@PostMapping("/insert")
	String insert(HttpServletRequest request,Menu menu, MultipartFile pic) throws IOException {
		//logger.debug(menu);
		//logger.debug(pic);
		//int sellid =  request.getSession().getAttribute("sellid");
		//menu.setSellerid(sellid);
		String sellname = (String) request.getSession().getAttribute("selllogin");//商家名
		logger.debug(sellname);
		//response.getWriter().print("test");
		String fileName=request.getServletContext().getRealPath("/")+"upload/"+menu.getXgname()+"_.png";
		logger.debug(fileName);
		pic.transferTo(new File(fileName));
		if (menuService.insertMenu(menu,sellname)) {
			logger.debug("i come back");
			return "success";
		} else {
			return "failure";
		}
	}

	@PostMapping("/memberselect")
	List memberselect(HttpServletRequest request) throws IOException {
		String name = request.getParameter("name");
		logger.debug(name);
		if (name == null) {
			name = "";
		}
		List lst = menuService.getMenu(name);
		logger.debug(lst);
		return lst;
	}

	@PostMapping("/sellerselect")
	List sellerselect(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String sellername = (String) request.getSession().getAttribute("selllogin");//商家名
		logger.debug(sellername);
		String name = request.getParameter("name");//模糊查询
		if(name==null) name = "";
		Map map = new HashMap();
		map.put("xgsellname",sellername);
		map.put("xgname",name);
		List lst = menuService.getsMenu(map);
		logger.debug(lst);
		return lst;
	}

	@GetMapping("/check")
	String check(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String name = request.getParameter("name");//菜名
		String sellername = (String) request.getSession().getAttribute("selllogin");
		logger.debug(sellername);
		if (menuService.check(name, sellername)) {
			return "have";
		} else {
			return "meiyou";
		}
		//wait
//		if (menus)
//		sql = "select * from menu where xgname='" + name + "' and xgsellid='"+sellid+"'";
//		logger.debug(sql);
//		if (dao.isExist(sql)) {
//			out.print("have");
//		} else {
//			out.print("meiyou");
//		}
	}

	@GetMapping("/delete/{xgid}")
	String delete(@PathVariable int xgid) throws IOException {
		logger.debug(xgid);
		if (menuService.delete(xgid)) {
			return "success";
		} else {
			return "failure";
		}
	}

//	@GetMapping("/delete")
//	void delete(HttpServletRequest request, HttpServletResponse response) throws IOException {
//		int menuid = Integer.parseInt(request.getParameter("menuid"));
//		if (menuService.delete(menuid)) {
//			response.getWriter().print("delete success!");
//		} else {
//			response.getWriter().print("delete failure!");
//		}
//	}

//	@Override
//	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		//获取html中name对象的值
//		String name=req.getParameter("name");
//		String op=req.getParameter("op");
//		String menuid=req.getParameter("menuid");
//		PrintWriter out = resp.getWriter();
//		//取得selllogin对象的值
//		String sellname=(String) req.getSession().getAttribute("selllogin");
//		if(op==null) {
//			op="";
//		}
//		String sql=null;
//		sql = "select xgid from seller where xgname='"+sellname+"'";
//		logger.debug(sql);
//		int sellid=dao.count(sql);
//		switch (op) {
//		case "delete":
//			sql = "delete from menu where xgid=" + menuid;
//			logger.debug(sql);
//			if (dao.exeUpdate(sql)) {
//				out.print("delete success!");
//			} else {
//				out.print("delete failure!");
//			}
//			break;
//		case "check":
//			sql = "select * from menu where xgname='" + name + "' and xgsellid='"+sellid+"'";
//			logger.debug(sql);
//			if (dao.isExist(sql)) {
//				out.print("have");
//			} else {
//				out.print("meiyou");
//			}
//			break;
//		case "select":
//			if(name==null) {
//				sql="select xgid,xgname,xgprice,xgpic,xgremark from menu where xgsellid='"+sellid+"'";
//				logger.debug(sql);
//			}else {
//				sql="select xgid,xgname,xgprice,xgpic,xgremark from menu where xgname like '%"+name+"%' and xgsellid='"+sellid+"'";
//				logger.debug(sql);
//			}
//			List lst=dao.getData(sql);
//			resp.setContentType("charset=UTF-8");
//			out.print(MyUtil.toJSON(lst));
//			break;
//		default:
//			break;
//		}
//
//
//	}
	
}
