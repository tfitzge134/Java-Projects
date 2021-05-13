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

		<a href="front?task=logout">Logout</a>

		<%
		if (roleName.equalsIgnoreCase("employee")) {
		%>

		<a href="new-reimbursment.html">Submit-Reimbursment</a>
		<a href="front?task=my-pending-reimbursments">Pending reimbursements</a>
		<a href="front?task=my-resolved-reimbursments">Resolved reimbursements</a>
		<a href="front?task=my-information">My information</a>

		<%
		}
		
		else if (roleName.equalsIgnoreCase("manager")) {
		%>

		Manager stuff....

		<%
		}
		%>
	
</body>
</html>