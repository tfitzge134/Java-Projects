package com.revature.util;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class HtmlUtil {

	public static void writerHtmlHeader(PrintWriter writer, HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		writer.print("<!DOCTYPE html>" + "<html>" + "<head>" + "<meta charset=\"UTF-8\">"
				+ " <link rel=\"stylesheet\" href=\"css/style.css\"/>" + "<title>Welcome to Reva</title>" + "</head>"
				+ "    <body>" + "        <div>" + "");

		RequestDispatcher rd = request.getRequestDispatcher("user-menu.jsp");
		rd.include(request, response);
	}

	public static void writerHtmlFooter(PrintWriter writer) {
		writer.print("</div>" + "    </body>" + "</html>");
	}
}
