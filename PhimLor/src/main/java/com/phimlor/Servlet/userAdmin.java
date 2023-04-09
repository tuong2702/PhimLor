package com.phimlor.Servlet;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import com.phimlor.DAO.UserDAO;
import com.phimlor.model.User;
import com.phimlor.model.Video;

/**
 * Servlet implementation class userAdmin
 */
@WebServlet({"/userAdmin", "/userAdmin/delete", "/userAdmin/update", "/userAdmin/edit/*", "/userAdmin/page/*"})
public class userAdmin extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	@Override
		protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			String uri = req.getRequestURI();
			UserDAO dao = new UserDAO();
			User user = new User();
			int indexPage = 1;
			if(uri.contains("update")) {
				try {
					BeanUtils.populate(user, req.getParameterMap());
					dao.update(user);
					req.setAttribute("success", "<div class='alert alert-success alert-dismissible fade show' role='alert'>"
							+ "<strong>Cập nhật thành công!</strong> Dữ liệu của bạn đã được cập nhật."
							+ "  <button type='button' class='btn-close' data-bs-dismiss='alert' aria-label='Close'></button>"
							+ "</div>");
				} catch (IllegalAccessException | InvocationTargetException e) {
					e.printStackTrace();
					req.setAttribute("message", "<div class='alert alert-danger alert-dismissible fade show' role='alert'>"
							+ "<strong>Cập nhật không thành công!</strong> Không thể cập nhật dữ liệu của bạn."
							+ "  <button type='button' class='btn-close' data-bs-dismiss='alert' aria-label='Close'></button>"
							+ "</div>");
				}
			}else if(uri.contains("edit")) {
				String id = uri.substring(uri.lastIndexOf("/")+1);
				user = dao.findById(id);
			}else if(uri.contains("delete")) {
				try {
					 String id = req.getParameter("id");
					 dao.delete(id);
					 req.setAttribute("success", "<div class='alert alert-success alert-dismissible fade show' role='alert'>"
								+ "<strong>Xóa thành công!</strong> Dữ liệu của bạn đã được xóa."
								+ "  <button type='button' class='btn-close' data-bs-dismiss='alert' aria-label='Close'></button>"
								+ "</div>");
				} catch (Exception e) {
					req.setAttribute("message", "<div class='alert alert-danger alert-dismissible fade show' role='alert'>"
							+ "<strong>Xóa không thành công!</strong> Không thể xóa dữ liệu của bạn."
							+ "  <button type='button' class='btn-close' data-bs-dismiss='alert' aria-label='Close'></button>"
							+ "</div>");
					e.printStackTrace();
				}
			}else if(uri.contains("page")) {
				String pageIndex = uri.substring(uri.lastIndexOf("/")+1);
				indexPage = Integer.parseInt(pageIndex);
			}		
			int listIndex = 0;
			List<User> list = dao.FindAll();
			listIndex = list.size() / 7;
			if(list.size() % 7 != 0) {
				listIndex++;
			}
			req.setAttribute("index", listIndex);
			req.setAttribute("form", user);
			req.setAttribute("users", dao.findPage(indexPage, 7));
			req.getRequestDispatcher("/views/userAdmin.jsp").forward(req, resp);
		}

}
