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

public class AllReimbursementsDelegate {

	public static void handleTask(PrintWriter writer, HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, BusinessException {
		String task = request.getParameter("task");
		// if task is null forward to home screen.
		if (task == null) {
			RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
			rd.forward(request, response);
			return;
		}

		String status = null;
		int statusId = 0;
		if ("all-pending-reimbursments".equals(task)) {
			statusId = Constants.PENDING_STATUS_ID;
		} else if ("all-resolved-reimbursments".equals(task)) {
			statusId = Constants.RESOLVED_STATUS_ID;
		}
		
		ErsReimbursementDAO dao = new ErsReimbursementDAOImpl();
		List<ErsReimbursement> list = dao.getAllByStatus(statusId);
		if(list != null) {
			request.setAttribute("list", list);
		}
		RequestDispatcher rd = request.getRequestDispatcher("all-reimbursments.jsp");
		rd.forward(request, response);
		return;
	}

}
