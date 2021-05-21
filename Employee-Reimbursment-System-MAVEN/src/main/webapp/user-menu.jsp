<%@page import="com.revature.models.ErsUserRole"%>
<%@page import="com.revature.models.ErsUser"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

	<%
	ErsUser user = (ErsUser) session.getAttribute("current-user");
	if(user != null){
		ErsUserRole role = (ErsUserRole) session.getAttribute("current-user-role");
		String roleName = role.getErsUserrole();
	%>
	<div>
		<h1>Welcome to Employee-Reimbursement-System</h1>

		<a href="ers?task=logout">Logout</a> | 
		<a href="ers?task=user-home">Home</a> | 
		
		<h3> Welcome, <%=user.getErsUsername()%>! </h3>
		 <br> <br>
		<%
		if (roleName.equalsIgnoreCase("employee")) {
		%>

		Employee Menu:  <br> <br>
		<a href="submit-reimbursement.jsp">Submit Reimbursement</a> | 
		<a href="ers?task=my-pending-reimbursements&userId=<%=user.getErsUserId()%>">Pending reimbursements</a> | 
		<a href="ers?task=my-resolved-reimbursements&userId=<%=user.getErsUserId()%>">Resolved reimbursements</a> | 
		<a href="ers?task=my-information">My information</a> | 

		<%
		}
		
		else if (roleName.equalsIgnoreCase("manager")) {
		%>

		Manager Menu:  <br> <br>
		<a href="ers?task=all-pending-reimbursements">All Pending reimbursements</a> | 
		<a href="ers?task=all-resolved-reimbursements">All Resolved reimbursements</a> | 
		<a href="ers?task=all-employees">All Employees</a> | 

		<%
		}
	}
	%>
	</div>
