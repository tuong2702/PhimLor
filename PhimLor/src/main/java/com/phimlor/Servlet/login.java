package com.phimlor.Servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.phimlor.DAO.UserDAO;
import com.phimlor.filter.CookieUtils;
import com.phimlor.model.User;



@WebServlet("/login")
public class login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
	@Override
		protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setAttribute("username", CookieUtils.get("username", req));
		req.setAttribute("password", CookieUtils.get("password", req));
		String userCheck = CookieUtils.get("username", req);
		String passCheck = CookieUtils.get("password", req);
		if(userCheck=="" && passCheck=="") {
			req.setAttribute("checkRemember", null);
		}else {
			req.setAttribute("checkRemember", "checked");
		}
		String method = req.getMethod();
		if (method.equalsIgnoreCase("POST")) {
			String id = req.getParameter("username");
			String pw = req.getParameter("password");
			String remember = req.getParameter("remember");
			try {
					UserDAO dao = new UserDAO();
					User user = dao.findById(id);
			if(!user.getPassword().equals(pw)) {
				req.setAttribute("message", "<div class='alert alert-danger alert-dismissible fade show' role='alert'>"
						+ "<strong>Đăng nhập không thành công!</strong> Mật khẩu của bạn không đúng."
						+ "  <button type='button' class='btn-close' data-bs-dismiss='alert' aria-label='Close'></button>"
						+ "</div>");
			}
			else {
				int hours = (remember == null) ? 0 : 30 * 24; // 0 = xóa
				CookieUtils.add("username", id, hours, resp);
				CookieUtils.add("password", pw, hours, resp);
				req.getSession().setAttribute("user", user);
				resp.sendRedirect("/PhimLor/index"); 
				return;
			}
			} catch (Exception e) {
				req.setAttribute("message", "<div class='alert alert-danger alert-dismissible fade show' role='alert'>"
						+ "<strong>Đăng nhập không thành công!</strong> Tên đăng nhập của bạn không đúng."
						+ "  <button type='button' class='btn-close' data-bs-dismiss='alert' aria-label='Close'></button>"
						+ "</div>");
			}
		}
		req.getRequestDispatcher("/views/login.jsp").forward(req, resp);
		}

}
