package com.revature.delegates;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.revature.BusinessException;
import com.revature.daos.ErsReimbursementDAO;
import com.revature.daos.ErsUserDAO;
import com.revature.daos.impl.ErsReimbursementDAOImpl;
import com.revature.daos.impl.ErsUserDAOImpl;
import com.revature.models.ErsUser;
import com.revature.util.EmailUtil;
import com.revature.util.HtmlUtil;

public class ApproveReimbursementDelegate {
	private static final Logger logger = LogManager.getLogger(ApproveReimbursementDelegate.class);

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
		
		String userIdStr = request.getParameter("userId");
		Integer userId = null;
		// if userIdStr is null forward to home screen.
		if (userIdStr == null) {
			writer.print("<p class=\"failure\">User Id required.<br></p>");
		} else {
			try {
				userId = Integer.parseInt(userIdStr);
				if (userId <= 0) {
					writer.print("<p class=\"failure\"> Invalid User Id. Must be a valid positive number.<br></p>");
				}
			} catch (Exception e) {
				writer.print("<p class=\"failure\"> Invalid User Id. Must be a valid number.<br></p>");
			}
		}
		
		HttpSession session = request.getSession();

		if ((reimbId != null) && (userId != null)) {
			if (session == null) {
				writer.print("<p class=\"failure\"> You must login first.<br></p>");
				writer.print("<a href=\"login.jsp\"> Login</p>");
			} else {
				ErsReimbursementDAO reimbDao = new ErsReimbursementDAOImpl();
				try {
					boolean result = reimbDao.approveReimbursement(reimbId);
					if (result) {
						logger.info("Reimbursement Approved, reimbId: " + reimbId);
						writer.print("<p class=\"success\"> Reimbursement Approved. Email has been sent.</p>");
						ErsUserDAO userDao = new ErsUserDAOImpl();
						ErsUser employee = userDao.getById(userId);
						EmailUtil.sendReimbursementResolvedEmail(employee, reimbId, "Approved");
						logger.info("Email has been sent.");
					} else {
						logger.info("NO record matched, reimbId: " + reimbId);
						writer.print("<p class=\"failure\"> NO record matched.</p>");
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
