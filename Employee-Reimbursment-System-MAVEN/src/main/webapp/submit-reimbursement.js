function submitForm() {
	var reimbDescription = document.getElementById("reimbDescription").value;
	if(reimbDescription == ""){
		alert("Description Required.");
		return;
	}
	var reimbAmount = document.getElementById("reimbAmount").value;
	if(reimbAmount == ""){
		alert("Amount Required.");
		return;
	}
	var typeId = document.getElementById("typeId").value;
	if(typeId == ""){
		alert("Type Required.");
		return;
	}
	var form = document.getElementById("the-form");
  	form.submit();
}