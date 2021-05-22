function submitForm() {
	var username = document.getElementById("username").value;
	if(username == ""){
		alert("Username Required.");
		return;
	}
	var password = document.getElementById("password").value;
	if(password == ""){
		alert("Password Required.");
		return;
	}
	var form = document.getElementById("the-form");
  	form.submit();
}
