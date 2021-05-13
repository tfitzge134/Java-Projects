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
<div >
<%
String message = (String)request.getAttribute("message");
String error = (String)request.getAttribute("error");
%>
        <h1>Welcome to Employee-Reimbursment-System</h1>
            <h3 class="myGroup">Login</h3>
            <br>
            <% 
            if(message !=null){
            %>
	<p class="success"> <%=message %> </p>
            <% 
            }
            %>
            <% 
            if(error !=null){
            %>
	<p class="failure"> <%=error %> </p>
            <% 
            }
            %>
            
            <br>
            <form action="front" method="POST">
                <input type="hidden" name="task" value="login">
                <label>User name:</label>
                <input type="text" name="username" class="form-control">
                <br>
                <label>Password</label>
                <input type="password" name="password" class="form-control">
                <br>
                <input type="submit" value="Sign In" class="button">
            </form>
        </div>
        
	
	
</body>
</html>