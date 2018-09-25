package com.crinsello.sendgmail;

import javax.mail.AuthenticationFailedException;

import com.crinsello.sendgmail.util.SendEmail;

public class EnviarEmail {

  public static void main(String[] args) {
	try {

	  String pAssunto = "[Email from crisnello code]";

	  String pCorpo = "<html>Email enviado com sucesso. <br><a href=\"www.crisnello.com\">crisnello</a></html>";
	  String[] to = new String[]{"crisnello@msn.com"};
	  
	  SendEmail sm = new SendEmail();
	  sm.sendMail("bancodenotas@crisnello.com","SENHA_DO_EMAIL",to,pAssunto,pCorpo);


	} catch (AuthenticationFailedException e) {
	  e.printStackTrace();
	} catch (Throwable e) {
	  e.printStackTrace();
	}
  }

}
