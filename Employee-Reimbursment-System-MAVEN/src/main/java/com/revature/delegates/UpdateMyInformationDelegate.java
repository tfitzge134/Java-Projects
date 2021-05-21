package com.revature.delegates;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.revature.models.ErsUser;
import com.revature.util.HtmlUtil;

public class UpdateMyInformationDelegate {

	public static void handleTask(PrintWriter writer, HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HtmlUtil.writerHtmlHeader(writer, request, response);

		HttpSession session = request.getSession();
		if (session == null) {
			writer.print("<p class=\"failure\"> You must login first.<br></p>");
			writer.print("<a href=\"login.jsp\"> Login</p>");
		} else {
			writer.print("<p class=\"failure\"> PENDING <br></p>");
//			ErsUser user = (ErsUser) session.getAttribute("current-user");
//			request.setAttribute("current-user", user);
//			RequestDispatcher rd = request.getRequestDispatcher("my-information.jsp");
//			rd.forward(request, response);
		}
		HtmlUtil.writerHtmlFooter(writer);

	}

}
