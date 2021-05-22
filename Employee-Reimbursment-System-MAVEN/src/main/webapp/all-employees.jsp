<%@page import="java.util.ArrayList"%>
<%@page import="com.revature.models.ErsUser"%>
<%@page import="java.util.List"%>
<%@page import="com.revature.models.ErsUserRole"%>
<%@page import="com.revature.models.ErsUser"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="css/style.css"/>
<title>Welcome to Employee-Reimbursement-System</title>
</head>
<body>
	<%@ include file="user-menu.jsp"%>
	<%@ include file="message.jsp"%>


	<%
	String task = request.getParameter("task");
	// if task is null forward to home screen.
	if (task == null) {
		RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
		rd.forward(request, response);
		return;
	}

	List<ErsUser> list = (List<ErsUser>) request.getAttribute("list");
	if (list == null) {
		list = new ArrayList<ErsUser>();
	}
	%>
	<div>
		<h3>All Employees</h3>

		<br> <br>

		<table border=1>
			<tr>
				<th class=blanchedalmond>Id</th>
				<th>User Name</th>
				<th>First Name</th>
				<th>Last Name</th>
				<th>Email</th>
			</tr>

			<%
			for (ErsUser emp : list) {
			%>
			<tr>
				<td><%=emp.getErsUserId() + ""%></td>
				<td><%=emp.getErsUsername()%></td>
				<td><%=emp.getUserFirstname()%></td>
				<td><%=emp.getUserLastname()%></td>
				<td><%=emp.getUserEmail()%></td>
				<td><a
					href="ers?task=employee-reimbursements&userId=<%=emp.getErsUserId()%>">View
						reimbursement</a></td>
			</tr>
			<%
			}
			%>
		</table>

	</div>
</body>
</html>