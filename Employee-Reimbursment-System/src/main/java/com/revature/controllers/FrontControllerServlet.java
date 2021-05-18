package com.revature.controllers;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.revature.delegates.AllReimbursementsDelegate;
import com.revature.delegates.ApproveReimbursmentDelegate;
import com.revature.delegates.LoginDelegate;
import com.revature.delegates.LogoutDelegate;
import com.revature.delegates.MyReimbursementsDelegate;
import com.revature.delegates.SubmitReimbursmentDelegate;
import com.revature.util.HtmlUtil;

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
			RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
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
		RequestDispatcher rd;
		switch (task) {
		case "login-form":
			rd = request.getRequestDispatcher("login.jsp");
			rd.forward(request, response);
			return true;
		case "user-home":
			rd = request.getRequestDispatcher("user-home.jsp");
			rd.forward(request, response);
			return true;
		}
		return false;
	}

	private void handleTask(String task, HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		response.setContentType("text/html");

		PrintWriter writer = response.getWriter();

		try {
			switch (task) {
			case "login":
				LoginDelegate.handleTask(writer, request, response);
				break;
			case "logout":
				LogoutDelegate.handleTask(writer, request, response);
				break;
			case "submit-reimbursment":
				SubmitReimbursmentDelegate.handleTask(writer, request, response);
				break;
			case "approve-reimbursment":
				ApproveReimbursmentDelegate.handleTask(writer, request, response);
				break;
			case "my-pending-reimbursments":
				MyReimbursementsDelegate.handleTask(writer, request, response);
				break;
			case "my-resolved-reimbursments":
				MyReimbursementsDelegate.handleTask(writer, request, response);
				break;
			case "all-pending-reimbursments":
				AllReimbursementsDelegate.handleTask(writer, request, response);
				break;
			case "all-resolved-reimbursments":
				AllReimbursementsDelegate.handleTask(writer, request, response);
				break;
			default:
				HtmlUtil.writerHtmlHeader(writer, request, response);
				writer.print("<div>");
				writer.print("Unsupported task: " + task);
				writer.print("</div>");
				HtmlUtil.writerHtmlFooter(writer);
			}// switch
		} catch (Exception e) {
			e.printStackTrace();
			String error = "ERROR: " + e.getMessage();
			HtmlUtil.writerHtmlHeader(writer, request, response);
			writer.print("<div>");
			writer.print(error);
			writer.print("</div>");
			HtmlUtil.writerHtmlFooter(writer);

		}
	}

}
