package com.phimlor.Servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.phimlor.DAO.ShareDAO;
import com.phimlor.DAO.ThongKeDAO;
import com.phimlor.DAO.VideoDAO;
import com.phimlor.model.Share;


@WebServlet({"/reportFavoriteShare", "/reportFavoriteShare/click"})
public class reportFavorteShare extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    @Override
    	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    		VideoDAO daoVideo = new VideoDAO();
    		req.setAttribute("videos", daoVideo.FindAll());
    	
    		String uri = req.getRequestURI();
    		ThongKeDAO dao = new ThongKeDAO(); 
        	String id = req.getParameter("id");
    		if(uri.contains("click")) {
    		    req.setAttribute("shares", dao.reportFavoriteShare(id));	
    		}
    		req.getRequestDispatcher("/views/reportFavoriteShare.jsp").forward(req, resp);
    	}

}
