package com.revature.controllers;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.revature.delegates.LoginDelegate;

/**
 * Servlet implementation class HelloServlet
 */
@WebServlet("/ers")
public class FrontControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public FrontControllerServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Every request must provide value for a parameter called 'task
		String task = request.getParameter("task");
		// if task is null forward to home screen.
		if (task == null) {
			RequestDispatcher rd = request.getRequestDispatcher("login.html");
			rd.forward(request, response);
			return;
		}
		boolean forwarded = forwardTask(task, request, response);
		if (forwarded) {
			return;
		} else {
			handleTask(task, request, response);
		}
	}

	private boolean forwardTask(String task, HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		switch (task) {
		case "login-form":
			RequestDispatcher rd = request.getRequestDispatcher("login.html");
			rd.forward(request, response);
			return true;
		}
		return false;
	}

	private void handleTask(String task, HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.setContentType("text/html");

		PrintWriter writer = response.getWriter();
		writer.print("<!DOCTYPE html>" + "<html>" + "<head>" + "<meta charset=\"UTF-8\">"
				+ " <link rel=\"stylesheet\" href=\"style.css\"/>" + "<title>Welcome to Reva</title>" + "</head>"
				+ "</head>" + "    <body>" + "        <div>" + "");

		writeReponseForTask(task, writer, request, response);

		writer.print("</div>" + "    </body>" + "</html>");
	}

	private void writeReponseForTask(String task, PrintWriter writer, HttpServletRequest request,
			HttpServletResponse response) {
		switch (task) {
		case "login":
			LoginDelegate.writeReponse(writer, request, response);
			break;
		default:
			writer.print("<div>");
			writer.print("Unsupported task: " + task);
			writer.print("</div>");
		}
	}

}