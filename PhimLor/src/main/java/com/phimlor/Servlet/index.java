package com.phimlor.Servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.phimlor.DAO.VideoDAO;
import com.phimlor.filter.CookieUtils;
import com.phimlor.model.Video;


@WebServlet({"/index", "/index/page/*", "/index/logout"})
public class index extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String uri = request.getRequestURI();
		VideoDAO dao = new VideoDAO();
		List<Video> video = dao.FindAll();
		int indexPage= 1;
		if(uri.contains("page")){
			String index = uri.substring(uri.lastIndexOf("/")+1);
			indexPage = Integer.parseInt(index);
		}else if(uri.contains("logout")) {
			request.getSession().removeAttribute("user");
		}
		int pageNumber = 0;
		pageNumber = video.size() / 9;
		if(video.size() % 9 != 0) {
			pageNumber++;
		}
		request.setAttribute("listPages", pageNumber);
		request.setAttribute("videos", dao.findPage(indexPage, 9));
		request.getRequestDispatcher("/views/index.jsp").forward(request, response);
	}

}
