function checkSignUp() {
	var username = $('#username').val();
	var password = $('#password').val();
	var email = $('#email').val();
	console.log("username: " + username + " password: " + password + " email: " + email);
	if(username == "" || password == "" || email == ""){
		alert("Credenziali vuote.");
		return;
	}
	$.ajax({
		url : 'ListaUtenti',
		type : 'POST',
		data: {
			username : username,
			password : password,
			email : email,
			action : 'registra'
		},
		success : function(response) {
			if(response == "error"){
				alert("Utente già esistente");
			} else{
				alert("Registrazione effettuata, accedi")
				window.location.replace("/web-computing/Login");
			}
		}
	})
}

function isEmail(){
	var regex = new RegExp (/^([a-zA-Z0-9_.+-])+\@(([a-zA-Z0-9-])+\.)+([a-zA-Z0-9]{2,4})+$/);
	
	return regex.test($("#email").val());
}

function isUserName(){
	var regex = new RegExp (/^([a-zA-Z0-9]{5,})+$/);
	return regex.test($("#username").val());
}

function isPassword(){
	alert("inizio func");
	var regex = new RegExp (/^(((?=.*[a-z])(?=.*[A-Z])(?=.*[^a-z^A-Z^0-9]))|((?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])))(?=.{1,})/);
	alert("fine");
	return regex.test($("#password").val());
}

function correctForm(){
	
	var errorMessage = "";
	var fieldsMissing = "";
    
    if ($("#email").val() == "") {      
        fieldsMissing += "<br>Email";
        
    }
    
    if ($("#nomeUtente").val() == "") {
        fieldsMissing += "<br>Nome Utente";
        
    }
    
    if ($("#password").val() == "") {
        fieldsMissing += "<br>Password";
        
    }
    
    if ($("#passwordConfirm").val() == "") {
        fieldsMissing += "<br>Conferma Password";
        
    }
    
    if (fieldsMissing != "") {
        errorMessage += "<p>The following field(s) are missing:" + fieldsMissing;
        
    }
    
    if (isEmail($("#email").val()) == false) {//non funziona(?)
        errorMessage += "<p>Il tuo indirizzo email non è valido</p>";
        
    }
    
    if(isUserName($("#username").val()) ==false){
    	errorMessage += "<p>Il tuo username non rispetta gli standard del sistema</p>";
    }
    
    if(isPassword($("#password").val()) ==false){
    	errorMessage += "<p>La tua password non rispetta gli standard del sistema</p>";
    }
    
    if ($("#password").val() != $("#passwordConfirm").val()) {
        errorMessage += "<p>Your passwords don't match</p>";
        
    }
    
    if (errorMessage != "") {
        
        $("#errorMessage").html(errorMessage);
        
    } else {
        
        $("#successMessage").show();
        $("#errorMessage").hide();
        checkSignUp()
        
    }
	
}