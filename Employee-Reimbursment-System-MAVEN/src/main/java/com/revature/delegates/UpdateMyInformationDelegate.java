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

import com.revature.BusinessException;
import com.revature.daos.ErsUserDAO;
import com.revature.daos.impl.ErsUserDAOImpl;
import com.revature.models.ErsUser;

public class UpdateMyInformationDelegate {
	private static final Logger logger = LogManager.getLogger(UpdateMyInformationDelegate.class);

	public static void handleTask(PrintWriter writer, HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		if (session == null) {
			request.setAttribute("error", "You must login first.");
			RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
			rd.forward(request, response);
			return;
		} else {
			ErsUser currentUser = (ErsUser) session.getAttribute("current-user");
			if (currentUser == null) {
				request.setAttribute("error", "You must login first.");
				RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
				rd.forward(request, response);
				return;
			}

			String userFirstname = request.getParameter("userFirstname");
			String userLastname = request.getParameter("userLastname");
			String userEmail = request.getParameter("userEmail");

			currentUser.setUserFirstname(userFirstname);
			currentUser.setUserLastname(userLastname);
			currentUser.setUserEmail(userEmail);

			ErsUserDAO dao = new ErsUserDAOImpl();
			try {
				boolean result = dao.update(currentUser);
				if (result) {
					logger.info("Employee Details updated successfully, username: " + currentUser.getErsUsername());
					request.setAttribute("message", "Details updated successfully.");
					RequestDispatcher rd = request.getRequestDispatcher("my-information.jsp");
					rd.forward(request, response);
					return;
				} else {
					logger.error("ERROR: Could NOT update employee Details, username: " + currentUser.getErsUsername());
					request.setAttribute("error", "Could NOT update Details.");
					RequestDispatcher rd = request.getRequestDispatcher("edit-my-information.jsp");
					rd.forward(request, response);
					return;
				}
			} catch (BusinessException e) {
				logger.error("Error occurred: " + e.getMessage());
				e.printStackTrace();
				request.setAttribute("error", "Error occurred: " + e.getMessage());
				RequestDispatcher rd = request.getRequestDispatcher("edit-my-information.jsp");
				rd.forward(request, response);
				return;
			}

		}

	}

}
