
<%
String message = (String) request.getAttribute("message");
String error = (String) request.getAttribute("error");
%>
<%
if (message != null) {
%>
<p class="success">
	<%=message%>
</p>
<%
}
if (error != null) {
%>
<p class="failure">
	<%=error%>
</p>
<%
}
%>
