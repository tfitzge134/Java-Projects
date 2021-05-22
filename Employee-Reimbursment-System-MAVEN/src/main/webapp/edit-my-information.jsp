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
		<h3>Edit My Information</h3>
		 <br>
            <br>
             <form id="the-form" action="ers" method="POST">
                <input type="hidden" name="task" value="update-my-information">
                <label>User Name:</label>
                <%=ersUsername %>
                <br>
                <label>First Name:</label>
                <input type="text" id="userFirstname" name="userFirstname" value="<%=userFirstname %>" class="form-control">
                <br>
                <label>Last Name:</label>
                <input type="text" id="userLastname" name="userLastname" value="<%=userLastname %>" class="form-control">
                <br>
                <label>Email</label>
                <input type="text" id="userEmail" name="userEmail" value="<%=userEmail %>" class="form-control">
                <br>         
                 <input type="button" onclick="submitForm()" value="Update" class="button">
            </form>
        </div>
	<%
	}
	%>
	
		
<script src="edit-my-information-script.js">

</script>
	
</body>
</html>
