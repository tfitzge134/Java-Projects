package com.revature.delegates;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.revature.BusinessException;
import com.revature.daos.ErsUsersDAO;
import com.revature.daos.ErsUsersRoleDAO;
import com.revature.daos.impl.ErsUsersDAOImpl;
import com.revature.daos.impl.ErsUsersRoleDAOImpl;
import com.revature.models.ErsUser;
import com.revature.models.ErsUserRole;
import com.revature.util.HtmlUtil;

public class LoginDelegate {

	public static void handleTask(PrintWriter writer, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HtmlUtil.writerHtmlHeader(writer);
		
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		if (username == null) {
			writer.print("Username required.<br>");
		}
		if (password == null) {
			writer.print("Password required.<br>");
		}
		if (username == null && password == null) {
			return;
		}

		ErsUsersDAO userDao = new ErsUsersDAOImpl();
		try {
			ErsUser user = userDao.verifyPassword(username, password);
			if (user == null) {
				writer.print("Invalid login credentials.<br>");
			} else {
				int userRoleId = user.getUserRoleId();
				ErsUsersRoleDAO roleDao = new ErsUsersRoleDAOImpl();
				ErsUserRole role = roleDao.getById(userRoleId);
				
				HttpSession session = request.getSession(true);
				session.setAttribute("current-user", user);
				session.setAttribute("current-user-role", role);

				RequestDispatcher rd = request.getRequestDispatcher("user-home.jsp");
				rd.forward(request, response);
			}
		} catch (BusinessException e) {
			e.printStackTrace();
			writer.print("Error occurred: " + e.getMessage());
		}
		HtmlUtil.writerHtmlFooter(writer);
		
	}

	

}
