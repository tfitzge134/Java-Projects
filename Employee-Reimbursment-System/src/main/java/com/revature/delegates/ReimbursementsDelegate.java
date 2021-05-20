package com.revature.delegates;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.revature.BusinessException;
import com.revature.daos.ErsReimbursementDAO;
import com.revature.daos.impl.ErsReimbursementDAOImpl;
import com.revature.models.ErsReimbursement;

public class ReimbursementsDelegate {

	public static void handleTask(PrintWriter writer, HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, BusinessException {
		String task = request.getParameter("task");
		// if task is null forward to home screen.
		if (task == null) {
			request.setAttribute("error", "task is required.");
			RequestDispatcher rd = request.getRequestDispatcher("user-home.jsp");
			rd.forward(request, response);
			return;
		}

		ErsReimbursementDAO dao = new ErsReimbursementDAOImpl();
		List<ErsReimbursement> list = null;
		if ("all-pending-reimbursements".equals(task)) {
			list = dao.getAllPending();
		} else if ("all-resolved-reimbursements".equals(task)) {
			list = dao.getAllResolved();
		} else {
			String userIdStr = request.getParameter("userId");
			// if userIdStr is null forward to home screen.
			if (userIdStr == null) {
				request.setAttribute("error", "userId is required.");
				RequestDispatcher rd = request.getRequestDispatcher("user-home.jsp");
				rd.forward(request, response);
				return;
			}
			int userId = Integer.parseInt(userIdStr);
			if ("employee-reimbursements".equals(task)) {
				list = dao.getByUserId(userId);
			} else if ("my-pending-reimbursements".equals(task)) {
				list = dao.getAllPendingForUser(userId);
			} else if ("my-resolved-reimbursements".equals(task)) {
				list = dao.getAllResolvedForUser(userId);
			}
		}

		if (list != null) {
			request.setAttribute("list", list);
		}
		RequestDispatcher rd = request.getRequestDispatcher("all-reimbursements.jsp");
		rd.forward(request, response);
		return;
	}

}