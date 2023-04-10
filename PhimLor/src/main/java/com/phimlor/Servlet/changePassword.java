package com.phimlor.Servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import com.phimlor.DAO.UserDAO;
import com.phimlor.model.User;

/**
 * Servlet implementation class changePassword
 */
@WebServlet("/changePassword")
public class changePassword extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    @Override
    	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    		String method = req.getMethod();
    		UserDAO dao = new UserDAO();
    		User user = (User) req.getSession().getAttribute("user");
    		req.setAttribute("user", user.getId());
    		String username = req.getParameter("username");
			String password = req.getParameter("password");
			String newPassword = req.getParameter("newPassword");
			String confirmPassword = req.getParameter("confirmPassword");
    		if(method.equalsIgnoreCase("POST")) {
    			if(user.getPassword().contains(password)) {
    				if(newPassword.contains(confirmPassword)) {
    					  try {
    						  	user.setId(user.getId());
    						  	user.setPassword(newPassword);
    						  	user.setEmail(user.getEmail());
    						  	user.setFullname(user.getFullname());
    						  	user.setAdmin(false);
							   dao.update(user);
							   req.setAttribute("success", "<div class='alert alert-success alert-dismissible fade show' role='alert'>"
		        						+ "<strong>Đổi mật khẩu thành công!</strong>"
		        						+ "  <button type='button' class='btn-close' data-bs-dismiss='alert' aria-label='Close'></button>"
		        						+ "</div>");
						} catch (Exception e) {
							req.setAttribute("success", "<div class='alert alert-danger alert-dismissible fade show' role='alert'>"
	        						+ "<strong>Đổi mật khẩu không thành công!</strong>"
	        						+ "  <button type='button' class='btn-close' data-bs-dismiss='alert' aria-label='Close'></button>"
	        						+ "</div>");
						}
    				}else {
    					req.setAttribute("success", "<div class='alert alert-danger alert-dismissible fade show' role='alert'>"
        						+ "<strong>Đổi mật khẩu không thành công!</strong> Mật khẩu mới và xác nhận mật khẩu mới không khớp."
        						+ "  <button type='button' class='btn-close' data-bs-dismiss='alert' aria-label='Close'></button>"
        						+ "</div>");
    				}
    			}else {
    				req.setAttribute("success", "<div class='alert alert-danger alert-dismissible fade show' role='alert'>"
    						+ "<strong>Đổi mật khẩu không thành công!</strong> Mật khẩu cũ không khớp."
    						+ "  <button type='button' class='btn-close' data-bs-dismiss='alert' aria-label='Close'></button>"
    						+ "</div>");
    			}
    		}
    		req.getRequestDispatcher("/views/changePassword.jsp").forward(req, resp);
    	}
}
