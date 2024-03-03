package com.eat.filter;

/**
 * Filter的用途 
1. 解决中文乱码问题 
2. 权限访问控制 
3. 过滤敏感词汇 
4. 压缩响应信息
 */

import org.apache.log4j.Logger;

import java.io.IOException;


import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
@WebFilter(filterName = "myfilter",urlPatterns="/*")
public class MyFilter implements Filter{//过滤器
	@Override
	public void destroy() {
		// 
	}
	@Override
	public void doFilter(ServletRequest arg0, ServletResponse arg1, FilterChain arg2)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		arg0.setCharacterEncoding("UTF-8");
		arg1.setCharacterEncoding("UTF-8");
		arg2.doFilter(arg0, arg1);
		
	}
	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
	}
}
