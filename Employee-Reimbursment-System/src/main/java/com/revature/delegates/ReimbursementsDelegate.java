package com.revature.delegates;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.revature.BusinessException;
import com.revature.Constants;
import com.revature.daos.ErsReimbursementDAO;
import com.revature.daos.impl.ErsReimbursementDAOImpl;
import com.revature.models.ErsReimbursement;

public class ReimbursementsDelegate {

	public static void handleTask(PrintWriter writer, HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, BusinessException {
		String task = request.getParameter("task");
		// if task is null forward to home screen.
		if (task == null) {
			RequestDispatcher rd = request.getRequestDispatcher("user-home.jsp");
			rd.forward(request, response);
			return;
		}

		ErsReimbursementDAO dao = new ErsReimbursementDAOImpl();
		String status = null;
		int statusId = 0;
		if ("all-pending-reimbursements".equals(task)) {
			statusId = Constants.PENDING_STATUS_ID;
		} else if ("all-resolved-reimbursements".equals(task)) {
			statusId = Constants.RESOLVED_STATUS_ID;
		}
		List<ErsReimbursement> list = null;
		if (statusId > 0) {
			list = dao.getAllByStatus(statusId);
		} else {
			if ("employee-reimbursements".equals(task)) {
				String userIdStr = request.getParameter("userId");
				// if userIdStr is null forward to home screen.
				if (userIdStr == null) {
					RequestDispatcher rd = request.getRequestDispatcher("user-home.jsp");
					rd.forward(request, response);
					return;
				}
				int userId = Integer.parseInt(userIdStr);
				list = dao.getByUserId(userId);
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
