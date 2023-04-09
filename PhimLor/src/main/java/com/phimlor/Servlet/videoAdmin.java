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

import com.phimlor.DAO.VideoDAO;
import com.phimlor.model.Video;


@WebServlet({"/videoAdmin", "/videoAdmin/create", "/videoAdmin/update", "/videoAdmin/delete",
	"/videoAdmin/edit/*", "/videoAdmin/page/*"})
public class videoAdmin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String uri = request.getRequestURI();
		VideoDAO dao = new VideoDAO();
		Video video = new Video();
		int indexPage = 1;
		if(uri.contains("create")) {
			try {
				BeanUtils.populate(video, request.getParameterMap());
				dao.create(video);
				request.setAttribute("success", "<div class='alert alert-success alert-dismissible fade show' role='alert'>"
						+ "<strong>Thêm mới thành công!</strong> Dữ liệu của bạn đã được thêm mới."
						+ "  <button type='button' class='btn-close' data-bs-dismiss='alert' aria-label='Close'></button>"
						+ "</div>");
			} catch (IllegalAccessException | InvocationTargetException e) {
				request.setAttribute("message", "<div class='alert alert-danger alert-dismissible fade show' role='alert'>"
						+ "<strong>Thêm mới không thành công!</strong> Không thể thêm mới dữ liệu của bạn."
						+ "  <button type='button' class='btn-close' data-bs-dismiss='alert' aria-label='Close'></button>"
						+ "</div>");
				e.printStackTrace();
			}
		}else if(uri.contains("update")) {
			try {
				BeanUtils.populate(video, request.getParameterMap());
				dao.update(video);
				request.setAttribute("success", "<div class='alert alert-success alert-dismissible fade show' role='alert'>"
						+ "<strong>Cập nhật thành công!</strong> Dữ liệu của bạn đã được cập nhật."
						+ "  <button type='button' class='btn-close' data-bs-dismiss='alert' aria-label='Close'></button>"
						+ "</div>");
			} catch (IllegalAccessException | InvocationTargetException e) {
				request.setAttribute("message", "<div class='alert alert-danger alert-dismissible fade show' role='alert'>"
						+ "<strong>Cập nhật không thành công!</strong> Không thể cập nhật dữ liệu của bạn."
						+ "  <button type='button' class='btn-close' data-bs-dismiss='alert' aria-label='Close'></button>"
						+ "</div>");
				e.printStackTrace();
			}
		}else if(uri.contains("delete")) {
			 try {
				 String id = request.getParameter("id");
				 dao.delete(id);
				 request.setAttribute("success", "<div class='alert alert-success alert-dismissible fade show' role='alert'>"
							+ "<strong>Xóa thành công!</strong> Dữ liệu của bạn đã được xóa."
							+ "  <button type='button' class='btn-close' data-bs-dismiss='alert' aria-label='Close'></button>"
							+ "</div>");
			} catch (Exception e) {
				request.setAttribute("message", "<div class='alert alert-danger alert-dismissible fade show' role='alert'>"
						+ "<strong>Xóa không thành công!</strong> Không thể xóa dữ liệu của bạn."
						+ "  <button type='button' class='btn-close' data-bs-dismiss='alert' aria-label='Close'></button>"
						+ "</div>");
				e.printStackTrace();
			}
		}else if(uri.contains("edit")) {
			String id = uri.substring(uri.lastIndexOf("/")+1);
			video = dao.findById(id);
		}else if(uri.contains("page")) {
			String pageIndex = uri.substring(uri.lastIndexOf("/")+1);
			indexPage = Integer.parseInt(pageIndex);
		}		
		int listIndex = 0;
		List<Video> list = dao.FindAll();
		listIndex = list.size() / 7;
		if(list.size() % 7 != 0) {
			listIndex++;
		}
		request.setAttribute("form", video);
		request.setAttribute("index", listIndex);
		request.setAttribute("videos", dao.findPage(indexPage, 7));
		request.getRequestDispatcher("/views/videoAdmin.jsp").forward(request, response);
	}

}
