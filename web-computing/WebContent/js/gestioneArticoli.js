function initMap() {
  var mapOptions = {
    zoom: 15,
    scrollwheel: false,
    center: new google.maps.LatLng(31.993072, 35.862211)
  };

  var map = new google.maps.Map(document.getElementById('googleMap'),
    mapOptions);


  var marker = new google.maps.Marker({
    position: map.getCenter(),
    animation: google.maps.Animation.BOUNCE,
    //icon: 'img/map-marker.png',
    map: map
  });
}
function del(id){
	 $.ajax({
			url : 'CreaArticolo',
			type : 'post',
			data : {
				id : id,
			action : "1"
			},
		success:function(){
			 document.getElementById(id).innerHTML= "ARTICOLO ELIMINATO";
			
		},
		error:function(){
			alert("Errore");
		}
		});
	}
 function modify(id){
	 $.ajax({
			url : 'CreaArticolo',
			type : 'post',
			data : {
				id : id,
			action : "2"
			},
		success:function(){
			alert("Sarai reindirizzato alla sezione della modifica");
			 window.location.href = "/web-computing/CreaArticolo?key=1";
			
		},
		error:function(){
			alert("Erroe");
		}
		});
 }
	 function view(id){
		 $.ajax({
				url : 'CreaArticolo',
				type : 'post',
				data : {
					id : id,
				action : "2"
				},
			success:function(){
				 window.location.href = "/web-computing/CreaArticolo?key=3";
				
			},
			error:function(){
				alert("Errore");
			}
			});
		}  