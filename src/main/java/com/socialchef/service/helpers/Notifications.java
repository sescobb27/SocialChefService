package com.socialchef.service.helpers;

import java.util.Date;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.socialchef.service.models.User;

public class Notifications {

	public static boolean sendEmail(User chef, String msg) {
		Properties properties = System.getProperties();
		final String email_from = "sescobb27";
		try {
			properties = System.getProperties();
			properties.put("mail.smtp.starttls.enable", "true"); // added this line
			properties.put("mail.smtp.host", "smtp.gmail.com");
			properties.put("mail.smtp.user", email_from);
			properties.put("mail.smtp.password", "vpmSFP41");
			properties.put("mail.smtp.port", "587");
			properties.put("mail.smtp.auth", "true");
		    Session session = Session.getInstance(properties, new Authenticator() {
		    	@Override
				protected PasswordAuthentication getPasswordAuthentication() {
		    		return new PasswordAuthentication(email_from, "vpmSFP41");
		    	}
			});
		    MimeMessage message = new MimeMessage(session);
		    InternetAddress from = new InternetAddress(email_from);
	        message.setSubject("SocialChef: Purchase Notification");
	        message.setFrom(from);
	        message.addRecipients(Message.RecipientType.TO,
	        		InternetAddress.parse(chef.getEmail()));
			// Now set the actual message
			message.setText(msg);
			message.setSentDate(new Date());
			// Send message
			Transport transport = session.getTransport("smtp");
			transport.connect("smtp.gmail.com", email_from, "vpmSFP41");
			System.out.println("Transport: "+transport.toString());
			Transport.send(message);
			return true;
		} catch (MessagingException mex) {
			mex.printStackTrace();
		}
		return false;
	}
}
