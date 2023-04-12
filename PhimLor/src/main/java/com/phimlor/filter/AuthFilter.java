package com.phimlor.filter;


import java.io.IOException;


import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.phimlor.model.User;


@WebFilter({"/userAdmin", "/videoAdmin","/reportFavorite","/reportFavoriteUser","/reportFavoriteShare"})
public class AuthFilter implements HttpFilter{

	@Override
	public void doFilter(HttpServletRequest req, HttpServletResponse resp, FilterChain chain)
			throws IOException, ServletException {
		String uri = req.getRequestURI();
		User user = (User) req.getSession().getAttribute("user");
		String error = "";
		if(user == null) {
			resp.sendRedirect("/PhimLor/login");
			return;
		}
		else if((user.getAdmin()==false) && uri.contains("/userAdmin")) {
			System.out.println("Vui lòng đăng nhập với vai trò admin");
			error = resp.encodeURL("Please login with admin role");
			
		}else if((user.getAdmin()==false) && uri.contains("/videoAdmin")) {
			System.out.println("Vui lòng đăng nhập với vai trò admin");
			error = resp.encodeURL("Please login with admin role");
			
		}else if((user.getAdmin()==false) && uri.contains("/reportFavorite")) {
			System.out.println("Vui lòng đăng nhập với vai trò admin");
			error = resp.encodeURL("Please login with admin role");
			
		}else if((user.getAdmin()==false) && uri.contains("/reportFavoriteUser")) {
			System.out.println("Vui lòng đăng nhập với vai trò admin");
			error = resp.encodeURL("Please login with admin role");
			
		}else if((user.getAdmin()==false) && uri.contains("/reportFavoriteShare")) {
			System.out.println("Vui lòng đăng nhập với vai trò admin");
			error = resp.encodeURL("Please login with admin role");
			
		}
		if(!error.isEmpty()) {
			req.getSession().setAttribute("securi", uri);
			req.getSession().setAttribute("securi", error);
			System.out.println("Error!");
			resp.sendRedirect("/PhimLor/login");
		}else {
			System.out.println("No Error!");
			chain.doFilter(req, resp);
			req.getSession().setMaxInactiveInterval(60);
		}
	}

}
