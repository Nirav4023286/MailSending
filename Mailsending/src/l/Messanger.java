package l;

import java.util.*;
import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class Messanger {

	public static void sendMail(String to,String Subject,String text,String cc,String bcc) {
		try
		{
			Properties props=new Properties();
			props.put("mail.smtp.host", "smtp.gmail.com");
			props.put("mail.smtp.port","587");
			props.put("mail.smtp.auth", "true");
			props.put("mail.smtp.starttls.enable","true");
			
			Session session = Session.getInstance(props, new Authenticator() {
				@Override
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication("nirajm2025@gmail.com","");
				}
			});
			
			
			MimeMessage message=new MimeMessage(session);
			message.addRecipient(MimeMessage.RecipientType.TO,new InternetAddress(to));
			message.addRecipient(MimeMessage.RecipientType.CC,new InternetAddress(cc));
			message.addRecipient(MimeMessage.RecipientType.BCC,new InternetAddress(bcc));
			message.setSubject(Subject);
			
			String title="<h1 style='text-align:center'>NIRAJMAIL</h1><br><br><hr>";
			String body="<b>"+text+"</b>";
			String footer="<h3 style='text-align:right'>niraj@gmail.com</h3>";
			message.setContent(title+body+footer,"text/html");
			Transport.send(message);
			System.out.println("Mail Sent Successfully");
			}
			catch(Exception e)
			{
				System.out.println("Error: "+e.getMessage());
				e.printStackTrace();
			}
	}
}
