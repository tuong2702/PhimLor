package com.phimlor.Servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.phimlor.DAO.UserDAO;
import com.phimlor.model.User;

/**
 * Servlet implementation class register
 */
@WebServlet({ "/register", "/register/click" })
public class register extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String method = req.getMethod();
		String uri = req.getRequestURI();
		User user = new User();
		UserDAO dao = new UserDAO();
		if (uri.contains("click")) {
			Boolean list = dao.findByEmail(req.getParameter("email"));
			if (list == false) {
				req.setAttribute("message", "<div class='alert alert-danger alert-dismissible fade show' role='alert'>"
						+ "<strong>Đăng ký không thành công!</strong>. Email này đã được sử dụng!"
						+ "  <button type='button' class='btn-close' data-bs-dismiss='alert' aria-label='Close'></button>"
						+ "</div>");
			} else {

				user.setId(req.getParameter("username"));
				user.setFullname(req.getParameter("fullname"));
				user.setPassword(req.getParameter("password"));
				user.setEmail(req.getParameter("email"));
				user.setAdmin(false);
				try {
					dao.create(user);
					req.setAttribute("success",
							"<div class='alert alert-success alert-dismissible fade show' role='alert'>"
									+ "<strong>Đăng ký thành công!</strong>"
									+ "  <button type='button' class='btn-close' data-bs-dismiss='alert' aria-label='Close'></button>"
									+ "</div>");
				} catch (Exception e) {
					req.setAttribute("message",
							"<div class='alert alert-danger alert-dismissible fade show' role='alert'>"
									+ "<strong>Đăng ký không thành công!</strong>. Username này đã tồn tại!"
									+ "  <button type='button' class='btn-close' data-bs-dismiss='alert' aria-label='Close'></button>"
									+ "</div>");
				}
			}
		}
		req.getRequestDispatcher("/views/register.jsp").forward(req, resp);
	}

}
