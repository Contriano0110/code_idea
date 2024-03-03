package com.eat.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.eat.service.SellService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/seller")
public class SellerController {
    Logger logger = Logger.getLogger(SellerController.class);
    @Autowired
    SellService sellService;

    @GetMapping("/check")
    void check(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException {
        String xgname = request.getParameter("xgname");
        logger.debug(xgname);
        PrintWriter out = response.getWriter();
        if (sellService.check(xgname)) {
            out.print("have");
        } else {
            out.print("meiyou");
        }
    }

    @PostMapping("/register")
    String sregister(HttpServletRequest request, HttpServletResponse response, @RequestParam Map map) throws IOException {
        logger.debug(map);
        if (sellService.sregister(map)) {
            response.sendRedirect(request.getServletContext().getContextPath() + "/slogin.html");
            return "success";
        } else {
            //response.sendRedirect(request.getServletContext().getContextPath() + "/sregister.html");
        }
        return "failure";
    }

    @GetMapping("/login")
    String slogin(HttpServletRequest request, @RequestParam Map map) throws IOException {
        logger.debug(map);
        if (sellService.slogin(map)) {
            request.getSession().setAttribute("selllogin", map.get("xgname"));
            return ("success");
        } else {
            request.getSession().setAttribute("selllogin", "_null");
            return ("failure");
        }
    }

    @GetMapping("/logout")
    void logout(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.getSession().setAttribute("selllogin", "_null");
        response.sendRedirect("slogin.html");
    }

    @GetMapping("/islogin")
    String islogin(HttpServletRequest request) {
        String islogin = (String) request.getSession().getAttribute("selllogin");
        if (islogin == null) {
            islogin = "_null";
        }
        return islogin;
    }

//	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		// TODO Auto-generated method stub
//		String op = req.getParameter("op");
//		//login/register
//		//String id = req.getParameter("id");
//		String name = req.getParameter("name");
//		name=new String(name.getBytes(),"utf-8");
//		String pass = req.getParameter("pass");
//		String tel = req.getParameter("tel");
//		String addr = req.getParameter("addr");
//
//		PrintWriter out = resp.getWriter();
//		String sql = null;
////		resp.getWriter().print(sql);
//		if (op == null) {
//			op = "";
//		}
//		switch (op) {
//		case "slogin":
//			sql="select * from seller where xgname='"+name+"'"+"and xgpass='"+pass+"'";
//			logger.debug(sql);
//			if (dao.isExist(sql)) {
//				req.getSession().setAttribute("selllogin", name);
//				out.print("success");
//			}else {
//				req.getSession().setAttribute("selllogin", "_null");
//				out.print("failure");
//			}
//			break;
//		case "logout":
//				req.getSession().setAttribute("selllogin", "_null");
//				resp.sendRedirect("slogin.html");
//			break;
//		case "islogin":
//			String islogin=(String) req.getSession().getAttribute("selllogin");
//			if(islogin==null) {
//				islogin="_null";
//			}
//			out.print(islogin);
//		break;
//		case "check":
//			sql = "select * from seller where xgname='" + name + "'";
//			logger.debug(sql);
//			if (dao.isExist(sql)) {
//				out.print("have");
//			}else {
//				out.print("meiyou");
//			}
//			break;
//		case "sregister":
//			sql="insert into seller(xgname,xgpass,xgtel,xgaddr)"+"values('"+name+"','"+pass+"','"+tel+"','"+addr+"')";
//			logger.debug(sql);
//			if (dao.exeUpdate(sql)) {
//				//out.print("register success");
//				resp.sendRedirect("slogin.html");//跳转
//			} else {
//				//out.print("register fail!");
//				resp.sendRedirect("sregister.html");//停留在本页面
//
//			}
//			break;
//		}
//	}
}
