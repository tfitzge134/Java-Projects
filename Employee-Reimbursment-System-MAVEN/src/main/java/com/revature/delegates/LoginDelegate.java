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
import com.revature.daos.ErsUsersRoleDAO;
import com.revature.daos.impl.ErsUserDAOImpl;
import com.revature.daos.impl.ErsUsersRoleDAOImpl;
import com.revature.models.ErsUser;
import com.revature.models.ErsUserRole;
import com.revature.util.HtmlUtil;

public class LoginDelegate {
	private static final Logger logger = LogManager.getLogger(LoginDelegate.class);

	public static void handleTask(PrintWriter writer, HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HtmlUtil.writerHtmlHeader(writer, request, response);

		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String error = "";
				
		if (username == null) {
			error += "Username required.<br>";
		}
		if (password == null) {
			error += "Password required.<br>";
		}
		if (username != null || password != null) {
			username = username.trim().toLowerCase();
			ErsUserDAO userDao = new ErsUserDAOImpl();
			try {
				ErsUser user = userDao.verifyPassword(username, password);
				if (user == null) {
					error += "Invalid LOGIN credentials.<br>";
					logger.info("Login failed for user: " + username);
				} else {
					logger.info("Login SUCCESS for user: " + username);
					int userRoleId = user.getUserRoleId();
					ErsUsersRoleDAO roleDao = new ErsUsersRoleDAOImpl();
					ErsUserRole role = roleDao.getById(userRoleId);

					HttpSession session = request.getSession(true);
					session.setAttribute("current-user", user);
					session.setAttribute("current-user-role", role);

					RequestDispatcher rd = request.getRequestDispatcher("user-home.jsp");
					rd.forward(request, response);
					return;
				}
			} catch (BusinessException e) {
				logger.error("Error occurred: " + e.getMessage());
				e.printStackTrace();
				error += "Error occurred: " + e.getMessage();
			}
			request.setAttribute("error", error);
			RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
			rd.forward(request, response);
		}
		HtmlUtil.writerHtmlFooter(writer);

	}

}
