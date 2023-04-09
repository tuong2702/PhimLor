package com.phimlor.Servlet;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.Random;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import com.phimlor.DAO.FavoriteDAO;
import com.phimlor.DAO.VideoDAO;
import com.phimlor.model.Favorite;
import com.phimlor.model.User;
import com.phimlor.model.Video;


@WebServlet({"/xemVideo", "/xemVideo/xem/*","/xemVideo/like/*", "/xemVideo/share/*"})
public class xemVideo extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
   
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String uri = request.getRequestURI();
		VideoDAO dao = new VideoDAO();
		FavoriteDAO daoFa = new FavoriteDAO();
		Favorite favorite = new Favorite();
		Video video = new Video();
		User user = new User();
		User userSession = (User) request.getSession().getAttribute("user");
		Random rd = new Random();
		int start = rd.nextInt(6) + 1;
		if(uri.contains("like")) {
			if(userSession == null) {
				response.sendRedirect("/PhimLor/login");
				return;
			}else {
				String id1 = uri.substring(uri.lastIndexOf("/")+1);
				Boolean check = daoFa.checkLike(userSession.getId(), id1);
				if(check==false) {
					video.setId(id1);	
					favorite.setVideo(video);
					user.setId(userSession.getId());
					favorite.setUser(user);
					favorite.setLikeDate(new Date());
					daoFa.create(favorite);
					request.setAttribute("checked", "dislike");
					request.setAttribute("item", dao.findById(id1));
				}else {
					daoFa.remove(userSession.getId(), id1);
					request.setAttribute("checked", null);
					request.setAttribute("item", dao.findById(id1));
				}
			}
		}else if(uri.contains("share")) {
			String idVd = uri.substring(uri.lastIndexOf("/")+1);
			video = dao.findById(idVd);
			// Thông số kết nối
			Properties props = new Properties();
			props.setProperty("mail.smtp.auth", "true");
			props.setProperty("mail.smtp.starttls.enable", "true");
		    props.setProperty("mail.smtp.ssl.protocols", "TLSv1.2");
			props.setProperty("mail.smtp.host", "smtp.gmail.com");
			props.setProperty("mail.smtp.port", "587");
			// Kết nối
			Session session = Session.getInstance(props, new Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					String username = "tuonglqpc04126@fpt.edu.vn";
					String password = "2722003tuong";
					return new PasswordAuthentication(username, password);
				}
			});
			//Tạo massage
			MimeMessage message = new MimeMessage(session);
			try {
//				message.setFrom(new InternetAddress(fromMail));
				message.setRecipients(Message.RecipientType.TO, request.getParameter("toMail"));
				message.setSubject("Video được chia sẻ với bạn!", "utf-8");
				message.setText("<h3>"+video.getTitle()+"</h3> <br>" +
								"<img src='https://img.youtube.com/vi/"+video.getPoster()+"/maxresdefault.jpg' width='50%' style='border-radius: 10px'> <br>" +
								"Xem video tại website: https://www.youtube.com/embed/"+video.getId()+"<br>"+
								"<small>Người chia sẻ: "+userSession.getFullname()+"</small> <br>"+
								"<small>Email: "+userSession.getEmail()+"</small>"
						, "utf-8", "html");
				message.setReplyTo(message.getFrom());
				
			} catch (MessagingException e) {
				e.printStackTrace();
			}
			// Gửi message
			try {
				Transport.send(message);
				request.setAttribute("sendMail", true);
			} catch (MessagingException e) {
				request.setAttribute("sendMail", false);
				e.printStackTrace();
			}
			request.setAttribute("item", dao.findById(idVd));
			request.setAttribute("videos", dao.findPage(start, 3));
			request.getRequestDispatcher("/views/xemVideo.jsp").forward(request, response);
			return;
		}else if(uri.contains("xem")) {
			String id = uri.substring(uri.lastIndexOf("/")+1);
			if(userSession != null) {
				Boolean check = daoFa.checkLike(userSession.getId(), id);
				if(check==false) {
					request.setAttribute("checked", null);
				}else {
					request.setAttribute("checked", "Dislike");
				}
			}
			request.setAttribute("item", dao.findById(id));
		}
		request.setAttribute("videos", dao.findPage(start, 3));
		request.getRequestDispatcher("/views/xemVideo.jsp").forward(request, response);
	}
}
