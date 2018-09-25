package com.crinsello.sendgmail.util;


import java.util.Properties;

import javax.mail.AuthenticationFailedException;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


public class SendEmail {

    private String mailSMTPServer;
    private String mailSMTPServerPort;
    private String pUser, pPass;
    public SendEmail() { //Para o GMAIL
        mailSMTPServer = "smtp.gmail.com";
        mailSMTPServerPort = "587";
    }
    SendEmail(String mailSMTPServer, String mailSMTPServerPort) { 
        this.mailSMTPServer = mailSMTPServer;
        this.mailSMTPServerPort = mailSMTPServerPort;
    }
    public void sendMail(String from,String pSenhaFrom, Object[] to, String subject, String message) throws AuthenticationFailedException, Exception {
    	pUser = from;
    	pPass = pSenhaFrom;
    	sendMail(from, to, subject, message);
    }
    public void sendMail(String from, Object[] tos, String subject, String message)throws AuthenticationFailedException,Exception {
    	
		final String username = pUser;
		final String password = pPass;

		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");

		Session session = Session.getInstance(props,
		  new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		  });

		try {

			Message msg = new MimeMessage(session);
			
			
			msg.setFrom(new InternetAddress(pUser));
			
			msg.setRecipients(Message.RecipientType.TO,InternetAddress.parse("crisnello@crisnello.com"));
			
			for(int i = 0; i < tos.length; i++){
					msg.setRecipients(Message.RecipientType.TO,InternetAddress.parse(tos[i].toString()));	   
        	}
			
			if(subject == null)
				msg.setSubject("[ Reader ]");
			else
				msg.setSubject(subject);

			msg.setContent(message, "text/html");

			Transport.send(msg);


		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
    	
				
    }
	public String getPPass() {
		return pPass;
	}
	public void setPPass(String pass) {
		pPass = pass;
	}
	public String getPUser() {
		return pUser;
	}
	public void setPUser(String user) {
		pUser = user;
	}
}



//clase que retorna uma autenticacao para ser enviada e verificada pelo servidor smtp
class SimpleAuth extends Authenticator {
  public String username = null;
  public String password = null;


  public SimpleAuth(String user, String pwd) {
      username = user;
      password = pwd;
  }

  protected PasswordAuthentication getPasswordAuthentication() {
      return new PasswordAuthentication (username,password);
  }
}

