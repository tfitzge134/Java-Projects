<%@page import="com.revature.Constants"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.revature.models.ErsReimbursement"%>
<%@page import="java.util.List"%>
<%@page import="com.revature.models.ErsUserRole"%>
<%@page import="com.revature.models.ErsUser"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
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

	String status = null;
	if ("all-pending-reimbursements".equals(task)) {
		status = "PENDING";
	} else if ("all-resolved-reimbursements".equals(task)) {
		status = "RESOLVED";
	}
	else{
		status = "Employee";
	}
	List<ErsReimbursement> list = (List<ErsReimbursement>) request.getAttribute("list");
	if (list == null) {
		list = new ArrayList<ErsReimbursement>();
	}
	%>
	<div>
		<h3>
			<%=status%>
			Reimbursements
		</h3>

		<br> <br>

		<table border=1>
			<tr>
				<th class=blanchedalmond>Id</th>
				<th>Author</th>
				<th>Type</th>
				<th>Description</th>
				<th>Amount</th>
				<th>Submitted</th>
			</tr>

			<%
			for (ErsReimbursement reimb : list) {
				String description = reimb.getReimbDescription();
				description = (description == null) ? "" : description;
			%>
			<tr>
				<td><%=reimb.getReimbId()%></td>
				<td><%=reimb.getReimbAuthor()%></td>
				<td><%=reimb.getReimbTypeId()%></td>
				<td><%=description%></td>
				<td><%=reimb.getReimbAmount()%></td>
				<td><%=reimb.getReimbSubmitted()%></td>
				<%
				if(reimb.getReimbStatusId() == Constants.PENDING_STATUS_ID){
				%>
				<td><a
					href="ers?task=approve-reimbursement&reimbId=<%=reimb.getReimbId()%>">Approve</a> | 
					<a
					href="ers?task=deny-reimbursement&reimbId=<%=reimb.getReimbId()%>">Deny</a></td>
				<%
				}
				else{
				%>
				<td>
					Resolved</td>
				<%} %>
			</tr>
			<%
			}
			%>
		</table>

	</div>
</body>
</html>