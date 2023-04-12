package com.phimlor.Servlet;

import java.io.IOException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.phimlor.DAO.FavoriteDAO;
import com.phimlor.DAO.ThongKeDAO;
import com.phimlor.DAO.VideoDAO;
import com.phimlor.model.Favorite;
import com.phimlor.model.Video;


@WebServlet({"/reportFavoriteUser", "/reportFavoriteUser/click"})
public class reportFavoriteUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    @Override
    	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	VideoDAO daoVideo = new VideoDAO();
    	req.setAttribute("videos", daoVideo.FindAll());
    	
    	String id = req.getParameter("id");
    	ThongKeDAO dao = new ThongKeDAO();
      	String uri = req.getRequestURI();
    	if(uri.contains("click")) {
    		List<Favorite> list = dao.reportFavoriteUser(id);
    		req.setAttribute("favorites", list);
    	}
    	req.getRequestDispatcher("/views/reportFavoriteUser.jsp").forward(req, resp);
    	}
    
}
