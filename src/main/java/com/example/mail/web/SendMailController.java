package com.example.mail.web;

import java.io.UnsupportedEncodingException;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;

import com.example.mail.auth.MailAuthentication;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SendMailController {
  
  @RequestMapping(value = "/mail")
  public void sender () throws AddressException, MessagingException  {

    Properties props = System.getProperties();
    props.put("mail.smtp.port", "587");
    props.put("mail.smtp.auth", "true");
    props.put("mail.smtp.host", "smtp.gmail.com");
    props.put("mail.smtp.starttls.enable", "true");

    Session session = Session.getDefaultInstance(props, new Authenticator() {
      String username = "id";
      String password = "password";

      protected PasswordAuthentication getPasswordAuthentication() {
        return new PasswordAuthentication(username, password);
      }
    });
    MimeMessage message = new MimeMessage(session);

    try {
      InternetAddress[] tos = new InternetAddress[2];
      tos[0] = new InternetAddress("from");
      tos[1] = new InternetAddress("to");
      message.setFrom(new InternetAddress("id<id@mail.com>"));
      message.setRecipients(Message.RecipientType.TO, tos);
      message.setSubject("test", "UTF-8");
      message.setText("test content", "UTF-8");

      Transport.send(message);
    } catch (AddressException e) {
      e.printStackTrace();
    } catch (MessagingException e)  {
      e.printStackTrace();
    }
  }
}