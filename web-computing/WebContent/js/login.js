function checkLogin(){
	var username = $('#username').val();
	var password = $('#password').val();
	console.log("username: " + username + " password: " + password);
	if(username == "" || password == ""){
		alert("Credenziali vuote.");
		return;
	}
	$.ajax({
		url : 'Login',
		type : 'POST',
		data: {
			username : username,
			password : password
		},
		success : function(response) {
			if(response == "error"){
				alert("Credenziali errate.");
			} else{
				alert("Login Effettuato")
				window.location.replace("/web-computing/Login");
			}
		}
	})
}