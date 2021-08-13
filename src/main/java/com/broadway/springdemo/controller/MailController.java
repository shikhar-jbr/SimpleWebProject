package com.broadway.springdemo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServlet;
import java.io.IOException;

@Controller
public class MailController {

    @Autowired
    private JavaMailSender javaMailSender;

    @GetMapping("/email")
    public String getEMailForm(){

        return "email";
    }

    @PostMapping("/email")
    public String postEmail(HttpServlet request){

        String toEmail= request.getInitParameter("to");
        String subject= request.getInitParameter("subject");
        String body= request.getInitParameter("msg");

        sendEmail(toEmail,subject,body);
        sendEmailWithAttachment(toEmail, subject, body);

        return "email";
    }


    private void sendEmail(String toEmail, String subject, String body) {

        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo(toEmail);
        msg.setSubject(subject);
        msg.setText(body);

        javaMailSender.send(msg);

    }

    private void sendEmailWithAttachment(String toEmail, String subject, String body){

        MimeMessage msg = javaMailSender.createMimeMessage();
        // true = multipart message
        MimeMessageHelper helper = null;
        try {
            helper = new MimeMessageHelper(msg, true);


        helper.setTo(toEmail);

        helper.setSubject(subject);

        // default = text/plain
        //helper.setText("Check attachment for image!");

        // true = text/html
        helper.setText("<h1> Check attachment for image!</h1> <br>"+"<p>"+body+"</p >",true);

        // hard coded a file path
        //FileSystemResource file = new FileSystemResource(new File("path/android.png"));

        helper.addAttachment("my_photo.png", new ClassPathResource("android.png"));

        javaMailSender.send(msg);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
