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
		#tes{
			
			}
	</style>
</head>

<body>

  <!-- Navigation -->
  <nav class="navbar navbar-expand-lg navbar-light fixed-top" id="mainNav">
    <div class="container">
      <button class="navbar-toggler navbar-toggler-right" type="button" data-toggle="collapse" data-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
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
  <header class="masthead" style="background-image: url('img/home-bg.jpg')">
    <div class="overlay"></div>
    <div class="container">
      <div class="row">
        <div class="col-lg-8 col-md-10 mx-auto">
          <div class="site-heading">
            <h1>Lista Articoli</h1>
            <span class="subheading">Seleziona un Articolo </span>
          </div>
        </div>
      </div>
    </div>
  </header>

  <!-- Main Content -->
  <div class="container">
    <div class="row">
      <div class="col-lg-12 col-md-10 mx-auto">
      	   <div class="input-group form-group">
              <label for="comment">Titolo Articolo:</label>
              <input type="text" class="form-control" name="titolo" id="titotlo" value="${articolo.getNome()}">
            </div>
              <div class="form-group">
                <label for="exampleFormControlTextarea1" id="tes">Inserire qui il testo dell'articolo</label>
                 <textarea class="form-control" id="contenuto" name="contenuto" >${articolo.getEmail()}</textarea>
              </div>
            <div class="form-group">
              <label for="exampleFormControlFile1">Inserisci le immagini</label>
              <input type="file" class="form-control-file" id="immagine">
             </div>
            <div class="form-group">
            <input type="submit" class="btn btn-succes" id="submitButton" value="Invia Articolo">
          </div>
        </div>
        <hr>
        <!-- Pager -->
      </div>
    </div>

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
    
</body>

</html>