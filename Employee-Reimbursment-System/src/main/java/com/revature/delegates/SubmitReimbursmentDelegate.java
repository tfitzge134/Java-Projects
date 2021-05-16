package com.revature.delegates;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.revature.BusinessException;
import com.revature.ErsReimbursementStatusID;
import com.revature.daos.ErsReimbursementDAO;
import com.revature.daos.impl.ErsReimbursementDAOImpl;
import com.revature.models.ErsReimbursement;
import com.revature.models.ErsUser;
import com.revature.util.HtmlUtil;

public class SubmitReimbursmentDelegate {

	public static void handleTask(PrintWriter writer, HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		HtmlUtil.writerHtmlHeader(writer, request, response);

		String reimbDescription = request.getParameter("reimbDescription");
		if (reimbDescription == null) {
			writer.print("<p class=\"failure\">Description required.<br></p>");
		}

		String reimbAmountStr = request.getParameter("reimbAmount");
		Double amount = null;
		if (reimbAmountStr == null) {
			writer.print("<p class=\"failure\">Amount required.<br></p>");
		} else {
			try {
				amount = Double.parseDouble(reimbAmountStr);
				if (amount <= 0) {
					writer.print("<p class=\"failure\">Invalid Amount. Must be a non-zero positive number.<br></p>");
				}
			} catch (Exception e) {
				writer.print("<p class=\"failure\"> Invalid Amount. Must be a valid number.<br></p>");
			}
		}

		String typeIdStr = request.getParameter("typeId");
		Integer typeId = null;
		if (typeIdStr == null) {
			writer.print("<p class=\"failure\">Type required.<br></p>");
		} else {
			try {
				typeId = Integer.parseInt(typeIdStr);
				if (typeId <= 0) {
					writer.print("<p class=\"failure\"> Invalid Type. Must be a valid positive number.<br></p>");
				}
			} catch (Exception e) {
				writer.print("<p class=\"failure\"> Invalid Type. Must be a valid number.<br></p>");
			}
		}
		HttpSession session = request.getSession();

		if (reimbDescription != null && amount != null && typeId != null) {
			if (session == null) {
				writer.print("<p class=\"failure\"> You must login first.<br></p>");
				writer.print("<a href=\"login.jsp\"> Login</p>");
			} else {
				ErsUser user = (ErsUser) session.getAttribute("current-user");

				ErsReimbursement reimb = new ErsReimbursement();
				reimb.setReimbAmount(amount);
				reimb.setReimbDescription(reimbDescription);
				reimb.setReimbSubmitted(new Date(System.currentTimeMillis()));
				reimb.setReimbAuthor(user.getErsUserId());
				reimb.setReimbStatusId(ErsReimbursementStatusID.PENDING_STATUS_ID);
				reimb.setReimbTypeId(typeId);

				ErsReimbursementDAO reimbDao = new ErsReimbursementDAOImpl();
				try {
					boolean result = reimbDao.add(reimb);
					if (result) {

						writer.print("<p class=\"success\"> ErsReimbursement Added.</p>");
					} else {
						writer.print("<p class=\"failure\"> Error occurred.</p>");
					}
				} catch (BusinessException e) {
					e.printStackTrace();
					writer.print("<p class=\"failure\"> Error occurred: " + e.getMessage() + "</p>");
				}
			}
		}
		HtmlUtil.writerHtmlFooter(writer);

	}

}
