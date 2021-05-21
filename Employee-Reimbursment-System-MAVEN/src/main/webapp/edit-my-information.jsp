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
<%@ include file="user-menu.jsp" %>
<%@ include file="message.jsp" %>


	<%
	ErsUser currentUser = (ErsUser) session.getAttribute("current-user");
	if(currentUser == null){
		RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
		rd.forward(request, response);
		return;
	}
	else{
		String ersUsername = (currentUser.getErsUsername() == null) ? "" : currentUser.getErsUsername();
		String userFirstname = (currentUser.getUserFirstname() == null) ? "" : currentUser.getUserFirstname();
		String userLastname = (currentUser.getUserLastname() == null) ? "" : currentUser.getUserLastname();
		String userEmail = (currentUser.getUserEmail() == null) ? "" : currentUser.getUserEmail();
	%>	
	<div>
		<h3>My Information</h3>

		 <br>
            <br>
             <form action="ers" method="POST">
                <input type="hidden" name="task" value="update-my-information">
                <label>User Name:</label>
                <%=ersUsername %>
                <br>
                <label>First Name:</label>
                <input type="text" name="userFirstname" value="<%=userFirstname %>" class="form-control">
                <br>
                <label>Last Name:</label>
                <input type="text" name="userLastname" value="<%=userLastname %>" class="form-control">
                <br>
                <label>Email</label>
                <input type="text" name="userEmail" value="<%=userEmail %>" class="form-control">
                <br>         
                <br>         
                <input type="submit" value="Submit" class="button">
            </form>
	</div>
	<%
	}
	%>
</body>
</html>
