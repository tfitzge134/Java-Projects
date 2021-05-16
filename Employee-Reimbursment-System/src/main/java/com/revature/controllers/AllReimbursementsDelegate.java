package com.revature.controllers;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.revature.daos.ReimbursementDAO;
import com.revature.daos.impl.ReimbursementDAOImpl;

public class AllReimbursementsDelegate {

	public static void handleTask(PrintWriter writer, HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String task = request.getParameter("task");
		// if task is null forward to home screen.
		if (task == null) {
			RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
			rd.forward(request, response);
			return;
		}

		String status = null;
		if ("all-pending-reimbursments".equals(task)) {
			status = "Pending";
		} else if ("all-resolved-reimbursments".equals(task)) {
			status = "Resolved";
		}
		
		ReimbursementDAO dao = new ReimbursementDAOImpl();
	}

}
