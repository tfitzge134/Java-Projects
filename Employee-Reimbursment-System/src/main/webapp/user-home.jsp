<%@page import="com.revature.models.ErsUserRole"%>
<%@page import="com.revature.models.ErsUser"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Welcome to Employee-Reimbursment-System</title>
</head>
<body>

	<%
	ErsUser user = (ErsUser) session.getAttribute("current-user");
	ErsUserRole role = (ErsUserRole) session.getAttribute("current-user-role");
	String roleName = role.getErsUserrole();
	%>
	<div>
		<h1>Welcome to Employee-Reimbursment-System</h1>

		<a href="ers?task=logout">Logout</a> <br>
		
		<h3> Welcome, <%=user.getErsUsername()%>! </h3>
		 <br> <br>
		<%
		if (roleName.equalsIgnoreCase("employee")) {
		%>

		Employee Menu:  <br> <br>
		<a href="submit-reimbursment.jsp">Submit Reimbursment</a> <br>
		<a href="ers?task=my-pending-reimbursments">Pending reimbursements</a> <br>
		<a href="ers?task=my-resolved-reimbursments">Resolved reimbursements</a> <br>
		<a href="ers?task=my-information">My information</a> <br>

		<%
		}
		
		else if (roleName.equalsIgnoreCase("manager")) {
		%>

		Manager Menu:  <br> <br>
		<a href="ers?task=all-pending-reimbursments">All Pending reimbursements</a> <br>
		<a href="ers?task=all-resolved-reimbursments">All Resolved reimbursements</a> <br>
		<a href="ers?task=all-employees">All Employees</a> <br>

		<%
		}
		%>
	</div>
</body>
</html>