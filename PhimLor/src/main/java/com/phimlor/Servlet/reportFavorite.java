package com.phimlor.Servlet;

import java.io.IOException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.phimlor.DAO.ThongKeDAO;
import com.phimlor.model.Report;

@WebServlet("/reportFavorite")
public class reportFavorite extends HttpServlet {
	private static final long serialVersionUID = 1L;
    @Override
    	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	ThongKeDAO dao = new ThongKeDAO();
    	List<Report> list = dao.reportFavorite();
    	req.setAttribute("reports", list);
    	req.getRequestDispatcher("/views/reportFavorite.jsp").forward(req, resp);
    	}
    	
}
