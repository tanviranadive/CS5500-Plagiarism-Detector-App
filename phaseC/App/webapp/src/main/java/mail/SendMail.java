/**
 * 
 */
package mail;


import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Send mail to user with report
 * @author adi
 * @date Mar 23, 2018
 */
public class SendMail {
	
	private SendMail()
	{	}

	private static String from = null;  // GMail user name
	private static String pass = null; // GMail password
	private static final Logger logger = LoggerFactory.getLogger(SendMail.class);
	private static Properties prop = new Properties();
	private static InputStream input = null;

	/**
	 * send mail to user with subject and body
	 * @param to
	 * @param subject
	 * @param body
	 */
	public static void sendFromGMail(String to, String subject, String body) {
		Properties props = System.getProperties();
		
		try {

			input = new FileInputStream("src/main/resources/application.properties");

			// load a properties file
			prop.load(input);

			// get the property value and print it out
			from = prop.getProperty("app.email.id");
			pass = prop.getProperty("app.email.pass");

		} catch (IOException ex) {
			logger.error("failed to fetch email properties"+ex.getMessage());
		}
		
		String host = "smtp.gmail.com";
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", host);
		props.put("mail.smtp.user", from);
		props.put("mail.smtp.password", pass);
		props.put("mail.smtp.port", "587");
		props.put("mail.smtp.auth", "true");

		Session session = Session.getDefaultInstance(props);
		MimeMessage message = new MimeMessage(session);

		try {
			message.setFrom(new InternetAddress(from));
			InternetAddress toAddress = new InternetAddress(to);          
			message.addRecipient(Message.RecipientType.TO, toAddress);


			message.setSubject(subject);
			message.setText(body);
			Transport transport = session.getTransport("smtp");
			transport.connect(host, from, pass);
			transport.sendMessage(message, message.getAllRecipients());
			transport.close();
		}
		catch (AddressException ae) {
			logger.error("Could not send mail, invalid address");
		}
		catch (MessagingException me) {
			logger.error("Could not send mail");
		}
	}
}