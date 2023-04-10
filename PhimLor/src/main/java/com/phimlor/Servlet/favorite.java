package com.phimlor.Servlet;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Properties;

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

import com.phimlor.DAO.FavoriteDAO;
import com.phimlor.DAO.ShareDAO;
import com.phimlor.model.Favorite;
import com.phimlor.model.Share;
import com.phimlor.model.User;
import com.phimlor.model.Video;

@WebServlet({"/favorite", "/favorite/userid/*", "/favorite/edit/*", "/favorite/dislike/*", "/favorite/share/*"})
public class favorite extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
        @Override
    	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    		FavoriteDAO dao = new FavoriteDAO();
    		Favorite favorite = new Favorite();
    		String uri = req.getRequestURI();
    		User user = (User) req.getSession().getAttribute("user"); 
    		String id = "";
    		if(uri.contains("userid")) {
    			id = uri.substring(uri.lastIndexOf("/")+1);
    		}else if(uri.contains("edit")) {
    			String id1 = uri.substring(uri.lastIndexOf("/")+1);
    			favorite = dao.findById(id1);
    			req.setAttribute("form", favorite);
    			req.setAttribute("favorites", dao.ListFavoriteUser(user.getId()));
    			req.getRequestDispatcher("/views/favorite.jsp").forward(req, resp);
    			return;
    		}else if(uri.contains("dislike")) {
    			String idVideo = uri.substring(uri.lastIndexOf("/")+1);
    			dao.remove(user.getId(), idVideo);
    			req.setAttribute("favorites", dao.ListFavoriteUser(user.getId()));
    			req.getRequestDispatcher("/views/favorite.jsp").forward(req, resp);
    			return;
    		}else if(uri.contains("share")) {
    			String idVd = uri.substring(uri.lastIndexOf("/")+1);
    			favorite = dao.findById(idVd);
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
//    				message.setFrom(new InternetAddress(fromMail));
    				message.setRecipients(Message.RecipientType.TO, req.getParameter("toMail"));
    				message.setSubject("Video được chia sẻ với bạn!", "utf-8");
    				message.setText("<h3>"+favorite.getVideo().getTitle()+"</h3> <br>" +
    								"<img src='https://img.youtube.com/vi/"+favorite.getVideo().getPoster()+"/maxresdefault.jpg' width='50%' style='border-radius: 10px'> <br>" +
    								"Xem video tại website: https://www.youtube.com/embed/"+favorite.getVideo().getId()+"<br>"+
    								"<small>Người chia sẻ: "+favorite.getUser().getFullname()+"</small> <br>"+
    								"<small>Email: "+favorite.getUser().getEmail()+"</small>"
    						, "utf-8", "html");
    				message.setReplyTo(message.getFrom());
    				
    			} catch (MessagingException e) {
    				e.printStackTrace();
    			}
    			
    			try {
    				//thêm vào bảng share
    				Video video = new Video();
    				User user2 = new User();
    				ShareDAO daoShare = new ShareDAO();
    				Share share = new Share();
    				share.setEmail(req.getParameter("toMail"));
    				share.setShareDate(new Date());
    				video.setId(favorite.getVideo().getId());
    				share.setVideo(video);
    				user.setId(favorite.getUser().getId());
    				share.setUser(user);
    				daoShare.create(share);
    				
    				// Gửi message
    				Transport.send(message);
    				req.setAttribute("sendMail", true);
    			} catch (MessagingException e) {
    				req.setAttribute("sendMail", false);
    				e.printStackTrace();
    			}
    			req.setAttribute("favorites", dao.ListFavoriteUser(user.getId()));
    			req.getRequestDispatcher("/views/favorite.jsp").forward(req, resp);
    			return;
    		}
    		req.setAttribute("favorites", dao.ListFavoriteUser(id));
    		req.getRequestDispatcher("/views/favorite.jsp").forward(req, resp);
    	}
}
