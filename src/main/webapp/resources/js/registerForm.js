/**
 * 
 */
function validateRegisterForm(){
	var fName = document.getElementById("form-first-name").value;
	var lName = document.getElementById("form-last-name").value;
	var contact = document.getElementById("form-contact").value;
	
	if(!/^[a-zA-Z]+$/.test(fName) || !/^[a-zA-Z]+$/.test(lName)){
		alert("Invalid!!");
		return false;
	}
	if(contact.length !== 10 && !$.isNumeric(contact)){
		alert("Contact Invalid");
		return false;
	}
	return true;
}