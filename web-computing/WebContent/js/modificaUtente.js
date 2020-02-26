	function elimina(id){
  		$.ajax({
  			url : 'ListaUtenti?action=elimina',
  			type : 'post',
  			data : {
  				id : id
  			},
  		success:function(){
  			alert("Utente eliminato correttamentee");
  			window.location.replace("/web-computing/ListaUtenti")
  		},
  		error:function(){
  			alert("Errore");
  		}
  		});
  	}
	
	function modifica(id) {
		$.ajax({
			url : 'ListaUtenti',
			type : 'POST',
			data :{
				id : id,
			action : 'modifica'
			},
			success:function(){
	  			window.location.replace("/web-computing/ListaUtenti?key=2")
			}
		});
	}