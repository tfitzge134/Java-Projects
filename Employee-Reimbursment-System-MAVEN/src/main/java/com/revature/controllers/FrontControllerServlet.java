package com.revature.controllers;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.revature.delegates.AllEmployeesDelegate;
import com.revature.delegates.ApproveReimbursementDelegate;
import com.revature.delegates.LoginDelegate;
import com.revature.delegates.LogoutDelegate;
import com.revature.delegates.ReimbursementsDelegate;
import com.revature.delegates.RejectReimbursementDelegate;
import com.revature.delegates.SubmitReimbursementDelegate;
import com.revature.delegates.UpdateMyInformationDelegate;
import com.revature.util.HtmlUtil;

/**
 * Servlet implementation class HelloServlet
 */
@WebServlet("/ers")
public class FrontControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static final Logger logger = LogManager.getLogger(FrontControllerServlet.class);

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
		logger.info("Request with task: " + task);
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
		// check for login session.
		if ("login-form".equals(task)) {
			rd = request.getRequestDispatcher("login.jsp");
			rd.forward(request, response);
			return true;
		}

		HttpSession session = request.getSession();
		if (session == null) {
			request.setAttribute("error", "You must login first.");
			rd = request.getRequestDispatcher("login.jsp");
			rd.forward(request, response);
			return true;
		}

		switch (task) {
		case "user-home":
			rd = request.getRequestDispatcher("user-home.jsp");
			rd.forward(request, response);
			return true;
		case "my-information":
			rd = request.getRequestDispatcher("my-information.jsp");
			rd.forward(request, response);
			return true;
		case "edit-my-information":
			rd = request.getRequestDispatcher("edit-my-information.jsp");
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
			case "submit-reimbursement":
				SubmitReimbursementDelegate.handleTask(writer, request, response);
				break;
			case "approve-reimbursement":
				ApproveReimbursementDelegate.handleTask(writer, request, response);
				break;
			case "reject-reimbursement":
				RejectReimbursementDelegate.handleTask(writer, request, response);
				break;
			case "my-pending-reimbursements":
				ReimbursementsDelegate.handleTask(writer, request, response);
				break;
			case "my-resolved-reimbursements":
				ReimbursementsDelegate.handleTask(writer, request, response);
				break;
			case "all-pending-reimbursements":
				ReimbursementsDelegate.handleTask(writer, request, response);
				break;
			case "all-resolved-reimbursements":
				ReimbursementsDelegate.handleTask(writer, request, response);
				break;
			case "employee-reimbursements":
				ReimbursementsDelegate.handleTask(writer, request, response);
				break;
			case "all-employees":
				AllEmployeesDelegate.handleTask(writer, request, response);
				break;
			case "update-my-information":
				UpdateMyInformationDelegate.handleTask(writer, request, response);
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
