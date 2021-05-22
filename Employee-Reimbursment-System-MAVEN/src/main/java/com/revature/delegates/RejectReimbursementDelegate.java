package com.revature.delegates;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.revature.BusinessException;
import com.revature.daos.ErsReimbursementDAO;
import com.revature.daos.impl.ErsReimbursementDAOImpl;
import com.revature.models.ErsUser;
import com.revature.util.HtmlUtil;

public class RejectReimbursementDelegate {
	private static final Logger logger = LogManager.getLogger(RejectReimbursementDelegate.class);

	public static void handleTask(PrintWriter writer, HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HtmlUtil.writerHtmlHeader(writer, request, response);

		String reimbIdStr = request.getParameter("reimbId");
		Integer reimbId = null;
		if (reimbIdStr == null) {
			writer.print("<p class=\"failure\">Id required.<br></p>");
		} else {
			try {
				reimbId = Integer.parseInt(reimbIdStr);
				if (reimbId <= 0) {
					writer.print("<p class=\"failure\"> Invalid Id. Must be a valid positive number.<br></p>");
				}
			} catch (Exception e) {
				writer.print("<p class=\"failure\"> Invalid Id. Must be a valid number.<br></p>");
			}
		}
		HttpSession session = request.getSession();

		if (reimbId != null) {
			if (session == null) {
				writer.print("<p class=\"failure\"> You must login first.<br></p>");
				writer.print("<a href=\"login.jsp\"> Login</p>");
			} else {
				ErsUser user = (ErsUser) session.getAttribute("current-user");

				ErsReimbursementDAO reimbDao = new ErsReimbursementDAOImpl();
				try {
					boolean result = reimbDao.rejectReimbursement(reimbId);
					if (result) {
						logger.info("Reimbursement Rejected, reimbId: " + reimbId);
						writer.print("<p class=\"success\"> Reimbursement Rejected.</p>");
					} else {
						writer.print("<p class=\"failure\"> NO record matched.</p>");
						logger.info("NO record matched, reimbId: " + reimbId);
					}
				} catch (BusinessException e) {
					logger.error("Error occurred: " + e.getMessage());
					e.printStackTrace();
					writer.print("<p class=\"failure\"> Error occurred: " + e.getMessage() + "</p>");
				}
			}
		}
		HtmlUtil.writerHtmlFooter(writer);

	}

}
