package com.phimlor.Servlet;

import java.io.IOException;
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

import com.phimlor.DAO.UserDAO;
import com.phimlor.model.User;


@WebServlet("/forgotPassword")
public class forgotPassword extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    @Override
    	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    		String method = req.getMethod();
    		if(method.equalsIgnoreCase("POST")) {
    			UserDAO dao = new UserDAO();
    			User user = dao.findById(req.getParameter("username"));
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
//        				message.setFrom(new InternetAddress(fromMail));
        				message.setRecipients(Message.RecipientType.TO, req.getParameter("email"));
        				message.setSubject("Mật khẩu tài khoản của bạn!", "utf-8");
        				message.setText("Bạn nhận được email lấy lại mật khẩu! <br>" + 
        						"Tài khoản: " + user.getId() +"<br>" + "Mật khẩu tạm thời: " +user.getPassword() + "<br>" +
        						"Vui lòng đổi lại mật khẩu mới sau khi lấy lại mật khẩu!"
        						, "utf-8", "html");
        				message.setReplyTo(message.getFrom());
        				
        			} catch (MessagingException e) {
        				e.printStackTrace();
        			}
        			// Gửi message
        			try {
        				Transport.send(message);
        				req.setAttribute("message", "<div class='alert alert-success alert-dismissible fade show' role='alert'>"
        						+ "<strong>Gửi lại mật khẩu thành công!</strong>"
        						+ "  <button type='button' class='btn-close' data-bs-dismiss='alert' aria-label='Close'></button>"
        						+ "</div>");
        			} catch (MessagingException e) {
        				req.setAttribute("message", "<div class='alert alert-danger alert-dismissible fade show' role='alert'>"
        						+ "<strong>Gửi lại mật khẩu không thành công!</strong>"
        						+ "  <button type='button' class='btn-close' data-bs-dismiss='alert' aria-label='Close'></button>"
        						+ "</div>");
        				e.printStackTrace();
        			}
    			}
    		req.getRequestDispatcher("/views/forgotPassword.jsp").forward(req, resp);
    	}
}
