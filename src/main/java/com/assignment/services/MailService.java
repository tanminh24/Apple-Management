package com.assignment.services;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class MailService {

    @Autowired
    JavaMailSender emailSender;

    public void sendEmailRegister(String toEmail, String username, String token) {

        MimeMessage message = emailSender.createMimeMessage();

        boolean multipart = true;

        try {
            MimeMessageHelper messageHelper = new MimeMessageHelper(message, multipart, "utf-8");
            String htmlMsg = "<h3>Dear, " + username + "</h3>"
                    + "<p>Chào mừng bạn đến với trang web của chúng tôi.</p>"
                    + "<p>Để sử dụng các tính năng, vui lòng xác thực tài khoản của bạn bằng cách nhấp vào nút bên dưới.</p>"
                    + "<a type=\"button\" target=\"_blank\"\r\n"
                    + "    style=\"background-color: #6c757d;\r\n"
                    + "           color: white;\r\n"
                    + "           padding: 14px 25px;\r\n"
                    + "           text-align: center;\r\n"
                    + "           text-decoration: none;\r\n"
                    + "           display: inline-block;\"\r\n"
                    + "    href=\"http://localhost:8080/SOF3021_IT17103_ASSIGNMENT/account/verify-account/" + token
                    + "\">\r\n"
                    + "    Xác thực tài khoản\r\n"
                    + "</a>\r\n"
                    + "";
            message.setContent(htmlMsg, "text/html; charset=utf-8");
            messageHelper.setTo(toEmail);
            messageHelper.setSubject("Xác thực tài khoản");

            this.emailSender.send(message);
            System.out.println("Gửi mail thành công");
        } catch (MessagingException e) {
            e.printStackTrace();
        }

    }
    
    public void sendEmailForgotPassword(String toEmail, String username, String verifyCode) {

        MimeMessage message = emailSender.createMimeMessage();

        boolean multipart = true;

        try {
            MimeMessageHelper messageHelper = new MimeMessageHelper(message, multipart, "utf-8");
            String htmlMsg = "<h3>Dear, " + username + "</h3>"
                    + "<p>Mã xác thực của bạn là:</p>"
                    + "<p type=\"button\" target=\"_blank\"\r\n"
                    + "    style=\"background-color: darkgray;\r\n"
                    + "           color: white;\r\n"
                    + "           padding: 14px 25px;\r\n"
                    + "           text-align: center;\r\n"
                    + "           text-decoration: none;\r\n"
                    + "           display: inline-block;\r\n"
                    + "           font-size: 26px;\"\r\n"
                    + "    >\r\n" 
                    + verifyCode
                    + "</p>"
                    + "";
            message.setContent(htmlMsg, "text/html; charset=utf-8");
            messageHelper.setTo(toEmail);
            messageHelper.setSubject("Mã xác thực");

            this.emailSender.send(message);
            System.out.println("Gửi mail thành công");
        } catch (MessagingException e) {
            e.printStackTrace();
        }

    }
    
    public void sendEmailNewPassword(String toEmail, String username, String newPassword) {

        MimeMessage message = emailSender.createMimeMessage();

        boolean multipart = true;

        try {
            MimeMessageHelper messageHelper = new MimeMessageHelper(message, multipart, "utf-8");
            String htmlMsg = "<h3>Dear, " + username + "</h3>"
                    + "<p>Mật khẩu mới của bạn là:</p>"
                    + "<p type=\"button\" target=\"_blank\"\r\n"
                    + "    style=\"background-color: darkgray;\r\n"
                    + "           color: white;\r\n"
                    + "           padding: 14px 25px;\r\n"
                    + "           text-align: center;\r\n"
                    + "           text-decoration: none;\r\n"
                    + "           display: inline-block;\r\n"
                    + "           font-size: 26px;\"\r\n"
                    + "    >\r\n" 
                    + newPassword
                    + "</p>";
            message.setContent(htmlMsg, "text/html; charset=utf-8");
            messageHelper.setTo(toEmail);
            messageHelper.setSubject("Mật khẩu mới");

            this.emailSender.send(message);
            System.out.println("Gửi mail thành công");
        } catch (MessagingException e) {
            e.printStackTrace();
        }

    }

}
