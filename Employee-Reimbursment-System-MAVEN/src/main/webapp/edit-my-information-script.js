function submitForm() {
	var userFirstname = document.getElementById("userFirstname").value;
	if(userFirstname == ""){
		alert("Firstname Required.");
		return;
	}
	var userLastname = document.getElementById("userLastname").value;
	if(userLastname == ""){
		alert("Lastname Required.");
		return;
	}
	var userEmail = document.getElementById("userEmail").value;
	if(userEmail == ""){
		alert("Email Required.");
		return;
	}
	var form = document.getElementById("the-form");
  	form.submit();
}
