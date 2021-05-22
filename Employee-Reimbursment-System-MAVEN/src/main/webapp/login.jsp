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
<div >
<%
String message = (String)request.getAttribute("message");
String error = (String)request.getAttribute("error");
%>
        <h1>Welcome to Employee-Reimbursement-System</h1>
            <h3 class="myGroup">Login</h3>
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
            <form id="the-form" action="ers" method="POST">
                <input type="hidden" name="task" value="login">
                <label>User name:</label>
                <input type="text" name="username" id="username" class="form-control">
                <br>
                <label>Password</label>
                <input type="password" name="password" id="password" class="form-control">
                <br>
                <input type="button" onclick="submitForm()" value="Sign In" class="button">
            </form>
        </div>
        
	
<script src="login-script.js">

</script>
	
</body>
</html>