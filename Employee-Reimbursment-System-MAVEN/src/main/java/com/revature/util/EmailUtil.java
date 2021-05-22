package com.revature.util;

import java.util.Properties;
import java.util.Scanner;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.revature.controllers.FrontControllerServlet;
import com.revature.models.ErsUser;

public class EmailUtil {
	private static final Logger logger = LogManager.getLogger(FrontControllerServlet.class);

	private static final Scanner scanner = new Scanner(System.in);

	public static void sendEmail(String toEmail, String subject, String text) {

		final String username = "shopabxy2021@gmail.com";
		final String password = "abcshop123"; // getInputString("Server Password for " + username + ": ");

		Properties prop = new Properties();
		prop.put("mail.smtp.host", "smtp.gmail.com");
		prop.put("mail.smtp.port", "465");
		prop.put("mail.smtp.auth", "true");
		prop.put("mail.smtp.socketFactory.port", "465");
		prop.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");

		Session session = Session.getInstance(prop, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		});

		try {

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress("tfitzge134@gmail.com"));
			message.setRecipients(Message.RecipientType.TO,

					InternetAddress.parse(toEmail));
			message.setSubject(subject);
			// we need add the customer name.
			message.setText(text);

			Transport.send(message);

			System.out.println(" Your email was sent");

		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}

	private static String getInputString(String name) {
		String value;
		do {
			logger.info("Please enter " + name + ": ");
			value = scanner.nextLine();
			if (value.trim().equals("")) {
				logger.info("Please enter an appropriate non blank value.");
			} else {
				break;
			}
		} while (true);
		return value;
	}

	public static void sendReimbursementResolvedEmail(ErsUser employee, Integer reimbId, String status) {
		String text = "Dear " + employee.getErsUsername() + "," + "\n\n Your Reimbursement with id[" + reimbId
				+ "] has been " + status + ".";
		String subject = "Reimbursement " + status;
		EmailUtil.sendEmail(employee.getUserEmail(), subject , text);
	}
}
