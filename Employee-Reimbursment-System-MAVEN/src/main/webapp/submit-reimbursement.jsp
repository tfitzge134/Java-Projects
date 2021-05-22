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

	
	<div>
		<h3>Submit Reimbursement-System</h3>

		 <br>
             <form id="the-form" action="ers" method="POST">
                <input type="hidden" name="task" value="submit-reimbursement">
                <label>Description:</label>
                <input type="text" name="reimbDescription" id="reimbDescription" class="form-control">
                <br>
                <label>Amount</label>
                <input type="text" name="reimbAmount" id="reimbAmount" class="form-control">
                <br>         
                <label>Type</label>
                <select name="typeId" id="typeId" class="form-control">
                <option value="">SELECT</option>
                <option value="1">Food</option>
                <option value="2">Travel</option>
                <option value="3">Internet</option>
                </select>
                <br>         
                <input type="button" onclick="submitForm()" value="Submit" class="button">
            </form>
	</div>
	
	
<script src="submit-reimbursement.js">

</script>
</body>
</html>