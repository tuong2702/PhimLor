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


@WebServlet({"/updateAccount", "/updateAccount/click"})
public class updateAccount extends HttpServlet {
	private static final long serialVersionUID = 1L;
    @Override
    	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    		String method = req.getMethod();
    		UserDAO dao = new UserDAO();
    		User user = (User) req.getSession().getAttribute("user");
    		String uri = req.getRequestURI();
    		if(uri.contains("click")) {
    			try {
    				User user1 = new User();
        			user1.setId(req.getParameter("username"));
        			user1.setPassword(req.getParameter("password"));
        			user1.setFullname(req.getParameter("fullname"));
        			user1.setEmail(req.getParameter("email"));
        			user1.setAdmin(false);
        			dao.update(user1);
        			req.getSession().setAttribute("user", user1);
        			req.setAttribute("success", "<div class='alert alert-success alert-dismissible fade show' role='alert'>"
    						+ "<strong>Cập nhật thành công!</strong>"
    						+ "  <button type='button' class='btn-close' data-bs-dismiss='alert' aria-label='Close'></button>"
    						+ "</div>");
    			} catch (Exception e) {
    				req.setAttribute("success", "<div class='alert alert-danger alert-dismissible fade show' role='alert'>"
    						+ "<strong>Cập nhật không thành công!</strong>"
    						+ "  <button type='button' class='btn-close' data-bs-dismiss='alert' aria-label='Close'></button>"
    						+ "</div>");
    			}
    			User user2 = (User) req.getSession().getAttribute("user");
    			req.setAttribute("username", user2.getId());
        		req.setAttribute("password", user2.getPassword());
        		req.setAttribute("fullname", user2.getFullname());
        		req.setAttribute("email", user2.getEmail());
        		req.getRequestDispatcher("/views/updateAccount.jsp").forward(req, resp);
        		return;
    		}
    		
    		req.setAttribute("username", user.getId());
    		req.setAttribute("password", user.getPassword());
    		req.setAttribute("fullname", user.getFullname());
    		req.setAttribute("email", user.getEmail());

    		req.getRequestDispatcher("/views/updateAccount.jsp").forward(req, resp);
    	}
}
