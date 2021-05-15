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
String message = (String)request.getAttribute("message");
String error = (String)request.getAttribute("error");
%>

	<%
	ErsUser user = (ErsUser) session.getAttribute("current-user");
	ErsUserRole role = (ErsUserRole) session.getAttribute("current-user-role");
	String roleName = role.getErsUserrole();
	%>
	<div>
		<h1>Employee-Reimbursment-System</h1>
		<h3>Submit Reimbursment-System</h3>

		<a href="ers?task=logout">Logout</a> <br>
		
		<h3> Welcome, <%=user.getErsUsername()%>! </h3>
		 <br> <br>
		 <br>
            <% 
            if(message !=null){
            %>
	<p class="success"> <%=message %> </p>
            <% 
            }
            if(error !=null){
            %>
	<p class="failure"> <%=error %> </p>
            <% 
            }
            %>
            <br>
             <form action="ers" method="POST">
                <input type="hidden" name="task" value="submit-reimbursment">
                <label>Description:</label>
                <input type="text" name="reimbDescription" class="form-control">
                <br>
                <label>Amount</label>
                <input type="text" name="reimbAmount" class="form-control">
                <br>         
                <label>Type</label>
                <select name="typeId" class="form-control">
                <option value="">SELECT</option>
                <option value="1">Food</option>
                <option value="2">Travel</option>
                <option value="3">Internet</option>
                </select>
                <br>         
                <input type="submit" value="Submit" class="button">
            </form>
	</div>
</body>
</html>