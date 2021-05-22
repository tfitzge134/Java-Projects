<%@page import="com.revature.util.ErsUserUtil"%>
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

	String status = null;
	String title = null;
	ErsUser employee = null;
	if ("all-pending-reimbursements".equals(task)) {
		title = "ALL PENDING Reimbursements";
	} else if ("all-resolved-reimbursements".equals(task)) {
		title = "ALL RESOLVED Reimbursements";
	}
	else if ("my-pending-reimbursements".equals(task)) {
		title = "MY PENDING Reimbursements";
	} else if ("my-resolved-reimbursements".equals(task)) {
		title = "MY RESOLVED Reimbursements";
	}
	else{
		employee = (ErsUser) request.getAttribute("employee");
		title = "Reimbursements for EMPLOYEE: "  + employee.getErsUsername();
	}
	List<ErsReimbursement> list = (List<ErsReimbursement>) request.getAttribute("list");
	if (list == null) {
		list = new ArrayList<ErsReimbursement>();
	}
	%>
	<div>
		<h3>
			<%=title%>
		</h3>

		<br>

		<table border=1>
			<tr>
				<th class=blanchedalmond>Id</th>
				<th>Author</th>
				<th>Type</th>
				<th>Description</th>
				<th>Amount</th>
				<th>Submitted</th>
				<th>Status</th>
			</tr>

			<%if((list == null) || list.isEmpty()) { %>
			<h4> NO Records found.</h4>
			<%
			}
			%>
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
					if(ErsUserUtil.getCurrentUserRoleId(request) == Constants.MANAGER_ROLE_ID){
				%>
				<td><a
					href="ers?task=approve-reimbursement&userId=<%=reimb.getReimbAuthor()%>&reimbId=<%=reimb.getReimbId()%>">Approve</a> | 
					<a
					href="ers?task=reject-reimbursement&userId=<%=reimb.getReimbAuthor()%>&reimbId=<%=reimb.getReimbId()%>">Reject</a></td>
				<%
						}
						else{
							%>
							<td>Pending</td>
							<%
						}
				}
				else{
					String resolvedStatus = "";
					if(reimb.getReimbStatusId() == Constants.APPROVED_STATUS_ID){
						resolvedStatus = "Approved";
					}
					else if(reimb.getReimbStatusId() == Constants.REJECTED_STATUS_ID){
						resolvedStatus = "Rejected";
					}
				%>
				<td><%=resolvedStatus %></td>
				<%
				} 
				%>
			</tr>
			<%
			}//for
			%>
		</table>

	</div>
</body>
</html>