package com.revature.delegates;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.revature.BusinessException;
import com.revature.daos.ErsUsersDAO;
import com.revature.daos.impl.ErsUsersDAOImpl;
import com.revature.models.ErsUser;

public class LoginDelegate {

	public static void writeReponse(PrintWriter writer, HttpServletRequest request, HttpServletResponse response) {
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

		ErsUsersDAO dao = new ErsUsersDAOImpl();
		try {
			ErsUser user = dao.verifyPassword(username, password);
			if (user == null) {
				writer.print("Invalid login credentials.<br>");
			} else {
				writer.print("Login Success<br>");
				writer.print(user.toString());
			}
		} catch (BusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			writer.print("Error occurred: " + e.getMessage());
		}

	}

}
