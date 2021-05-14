package com.revature.delegates;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.revature.daos.ErsReimbursementDAO;
import com.revature.daos.impl.ErsReimbursementDAOImpl;
import com.revature.util.HtmlUtil;

public class SubmitReimbursmentDelegate {

	public static void handleTask(PrintWriter writer, HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HtmlUtil.writerHtmlHeader(writer);

		String reimbDescription = request.getParameter("reimbDescription");
		String reimbAmount = request.getParameter("reimbAmount");
		if (reimbDescription == null) {
			writer.print("Description required.<br>");
		}
		if (reimbAmount == null) {
			writer.print("Amount required.<br>");
		}
		if (reimbDescription != null || reimbAmount != null) {

			ErsReimbursementDAO userDao = new ErsReimbursementDAOImpl();
//			try {
//				ErsReimbursement reimb = userDao.verifyPassword(username, password);
//				if (reimb == null) {
//					writer.print("Invalid login credentials.<br>");
//				} else {
//					int userRoleId = reimb.getUserRoleId();
//					ErsReimbursementsRoleDAO roleDao = new ErsReimbursementsRoleDAOImpl();
//					ErsReimbursementRole role = roleDao.getById(userRoleId);
//
//					HttpSession session = request.getSession(true);
//					session.setAttribute("current-reimb", reimb);
//					session.setAttribute("current-reimb-role", role);
//
//					RequestDispatcher rd = request.getRequestDispatcher("reimb-home.jsp");
//					rd.forward(request, response);
//				}
//			} catch (BusinessException e) {
//				e.printStackTrace();
//				writer.print("Error occurred: " + e.getMessage());
//			}
		}
		HtmlUtil.writerHtmlFooter(writer);

	}

}
