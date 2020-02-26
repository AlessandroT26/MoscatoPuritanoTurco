<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">

<head>

  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <meta name="description" content="">
  <meta name="author" content="">

  <title>Clean Blog - Start Bootstrap Theme</title>

  <!-- Bootstrap core CSS -->
  <link href="vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">

  <!-- Custom fonts for this template -->
  <link href="vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
  <link href='https://fonts.googleapis.com/css?family=Lora:400,700,400italic,700italic' rel='stylesheet' type='text/css'>
  <link href='https://fonts.googleapis.com/css?family=Open+Sans:300italic,400italic,600italic,700italic,800italic,400,300,600,700,800' rel='stylesheet' type='text/css'>

  <!-- Custom styles for this template -->
  <link href="css/clean-blog.min.css" rel="stylesheet">

  <style>
  
  </style>

</head>

<body>

  <!-- Navigation -->
  <nav class="navbar navbar-expand-lg navbar-light fixed-top" id="mainNav">
    <div class="container">
      <a class="navbar-brand" href="index.html">Big Sister's Ear</a>
      <button class="navbar-toggler navbar-toggler-right" type="button" data-toggle="collapse" data-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
        Menu
        <i class="fas fa-bars"></i>
      </button>
      <div class="collapse navbar-collapse" id="navbarResponsive">
        <ul class="navbar-nav ml-auto">
          <li class="nav-item">
            <a class="nav-link" href="homeAmm.jsp">Home</a>
          </li>
          <c:if test="${user.isAdmin() == true}">			
          <li class="nav-item">
            <a class="nav-link" href="/web-computing/CreaArticolo?key=2">Create Post</a>
          </li>
          <li class="nav-item">
           <a class="nav-link" href="/web-computing/CreaArticolo">Gestione Articoli</a>
          </li>
           <li class="nav-item">
           <a class="nav-link" href="/web-computing/ListaUtenti">Gestione Utenti</a>
          </li>
          </c:if>
           <c:if test="${user != null}">
          			<li class="nav-item">
					<a class="nav-link">Ciao ${user.username}!</a></li>
					<li class="nav-item"><a class="nav-link" href="Login?logout=true">Logout</a></li>
			</c:if>
          </ul>
      </div>
    </div>
  </nav>

  <!-- Page Header -->
  <header class="masthead" style="background-image: url('img/post-bg.jpg')">
    <div class="overlay"></div>
    <div class="container">
      <div class="row">
        <div class="col-lg-8 col-md-10 mx-auto">
          <div class="post-heading" id="miao">
            <h1>${articolo.getTitolo()}</h1>
            <h2 class="subheading">Enjoy reading this Article</h2>
          </div>
        </div>
      </div>
    </div>
  </header>

  <!-- Post Content -->
  <article>
    <div class="container">
      <div class="row">
        <div class="card-body">
                  
            <div class="input-group form-group">
					<p id="contenuto" ><font size="10" color="purple"> ${articolo.getTitolo()}</font>
            </div>
              <div class="form-group">
                       <p id="contenuto" ><font size="4" color="black"> ${articolo.getContenuto()}</font>
                 </p>
              </div>
            <div class="form-group">
            		<button type="submit" class="fa fa-thumbs-up" onclick= like(); style="color: green; background-color: white;">MI PIACE <z id="like" >[${articolo.getMiPiace()}] </z></button>   
            		<button type="submit" class="fa fa-thumbs-down" onclick= dislike(); style="color: red; background-color: white;">NON MI PIACE <x id="dislike" >[${articolo.getNonMiPiace()}] </x></button>         
          </div>
            <hr>
            Aggiungi un Commento
             <form action="<%=request.getContextPath()+"/CreaArticolo?action=7&key=3" %>" method="post">
            <div class="form-group">
              <input type="text" class="form-control" id="commento" name="commento">
            </div>
            <div class="form-group">
            <input type="submit" class="btn btn-primary" id="commentoButton"  name="commentoButton" value="Invia Commento">
          </div>
          </form>
          <hr>
          <c:forEach var="commenti" items="${commenti}">
      	 	<p id="${commenti.getId()}" > <font size="6" style=bold id="${commenti.getId()}"> 
      	 		<strong> ${commenti.getUtente()} </strong> <font size="4" color="grey" > ha commentato... </font>
      			</font>
      			<br>
      			<font size= "3">
      			 &emsp; ${commenti.getContenuto()}
      			</font>
      			<c:if test="${user.isAdmin() == true}">			 
      			<button id="${commenti.getId()}" type="button" value="delete" onclick="del(this.id)"> Elimina
      			</button>
      		</c:if>
      		</p>
      	 </c:forEach>
        </div>
      </div>
    </div>
  </article>

  <hr>


  <!-- Footer -->
  <footer>
    <div class="container">
      <div class="row">
        <div class="col-lg-8 col-md-10 mx-auto">
          <ul class="list-inline text-center">
            <li class="list-inline-item">
              <a href="#">
                <span class="fa-stack fa-lg">
                  <i class="fas fa-circle fa-stack-2x"></i>
                  <i class="fab fa-twitter fa-stack-1x fa-inverse"></i>
                </span>
              </a>
            </li>
            <li class="list-inline-item">
              <a href="#">
                <span class="fa-stack fa-lg">
                  <i class="fas fa-circle fa-stack-2x"></i>
                  <i class="fab fa-facebook-f fa-stack-1x fa-inverse"></i>
                </span>
              </a>
            </li>
            <li class="list-inline-item">
              <a href="#">
                <span class="fa-stack fa-lg">
                  <i class="fas fa-circle fa-stack-2x"></i>
                  <i class="fab fa-github fa-stack-1x fa-inverse"></i>
                </span>
              </a>
            </li>
          </ul>
          <p class="copyright text-muted">Copyright &copy; Your Website 2019</p>
        </div>
      </div>
    </div>
  </footer>

  <!-- Bootstrap core JavaScript -->
  <script src="vendor/jquery/jquery.min.js"></script>
  <script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

  <!-- Custom scripts for this template -->
  <script src="js/clean-blog.min.js"></script>
  
  <script>
  function like(id){
		$.ajax({
			url : 'CreaArticolo?key=3',
			type : 'post',
			data : {
				id : id,
			  bool : "1",
			action : 5
			},
		success:function(){
			alert("like");
			document.getElementById("like").innerHTML= "[${articolo.getMiPiace()}]+1";
			location.reload(true);
		},
		error:function(){
			alert("Like non Effettuato");
		}
		});
	}
  function dislike(id){
		$.ajax({
			url : 'CreaArticolo?key=3',
			type : 'post',
			data : {
				id : id,
			   bool :"0",
				action : 5
			},
		success:function(){
			alert("dislike");
			document.getElementById("dislike").innerHTML= "[${articolo.getNonMiPiace()}]+1";
			location.reload(true);
		},
		error:function(){
			alert("Dislike non Effettuato");
		}
		});
	}
  function del(id){
		 $.ajax({
				url : 'CreaArticolo?key=3',
				type : 'post',
				data : {
					id : id,
				action : "8"
				},
			success:function(){
				alert(id);
				alert("Commento eliminato correttamentee");
				document.getElementById(id).innerHTML= "COMMENTO ELIMINATO";
				window.location.href = "/web-computing/CreaArticolo?key=3";
			},
			error:function(){
				alert("Errore");
			}
			});
	}
  </script>

</body>

</html>
