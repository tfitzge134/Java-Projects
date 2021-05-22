package com.revature.delegates;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LogoutDelegate {
	private static final Logger logger = LogManager.getLogger(LogoutDelegate.class);

	public static void handleTask(PrintWriter writer, HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		if (session != null) {
			session.invalidate();
		}
		logger.info("User logged out.");
		request.setAttribute("message", "You're Logged out!");

		RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
		rd.forward(request, response);

	}

}
