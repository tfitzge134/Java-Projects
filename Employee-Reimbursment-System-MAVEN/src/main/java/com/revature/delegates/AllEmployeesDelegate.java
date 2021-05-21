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
import com.revature.daos.ErsUserDAO;
import com.revature.daos.impl.ErsUserDAOImpl;
import com.revature.models.ErsUser;

public class AllEmployeesDelegate {

	public static void handleTask(PrintWriter writer, HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, BusinessException {
		String task = request.getParameter("task");
		// if task is null forward to home screen.
		if (task == null) {
			RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
			rd.forward(request, response);
			return;
		}

		int roleId = Constants.EMPLOYEE_ROLE_ID;

		ErsUserDAO dao = new ErsUserDAOImpl();
		List<ErsUser> list = dao.getByRole(roleId);
		if (list != null) {
			request.setAttribute("list", list);
		}
		RequestDispatcher rd = request.getRequestDispatcher("all-employees.jsp");
		rd.forward(request, response);
		return;
	}

}
