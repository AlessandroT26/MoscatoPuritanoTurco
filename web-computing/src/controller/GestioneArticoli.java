package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Articolo;
import model.Commento;
import model.Model;
import model.Utente;
import persistence.dao.DAOFactory;


@WebServlet("/CreaArticolo")
public class GestioneArticoli extends HttpServlet {
	private static final long serialVersionUID = 2L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Object utente = request.getSession().getAttribute("user");
		String isLogout = request.getParameter("logout");
		if (isLogout != null && isLogout.equals("true")) {
			request.getSession().removeAttribute("user");
			RequestDispatcher rd = request.getRequestDispatcher("/login.jsp");
			rd.forward(request, response);
		}else {
			if (utente == null) {
				RequestDispatcher rd = request.getRequestDispatcher("/login.jsp");
				rd.forward(request, response);
			}
		}
		
		if(request.getParameter("key")!=null&&request.getParameter("key").equalsIgnoreCase("1"))
		{		RequestDispatcher rd = request.getRequestDispatcher("/modificaArticolo.jsp");		
				rd.forward(request, response);
				return ;
		}
		else if(request.getParameter("key")!=null&&request.getParameter("key").equalsIgnoreCase("2")) {
			RequestDispatcher rd = request.getRequestDispatcher("/creaArticolo.jsp");		
			rd.forward(request, response);
			return ;
		}
		else if(request.getParameter("key")!=null&&request.getParameter("key").equalsIgnoreCase("3")) {
			RequestDispatcher rd = request.getRequestDispatcher("/viewArticolo.jsp");		
			rd.forward(request, response);
			return;
		}
		List<Model> articoli=DAOFactory.makeArticoloDAO().findAll();
		if(!(articoli.isEmpty())) {
			request.getSession().setAttribute("articoli", articoli);
		}
		RequestDispatcher rd = request.getRequestDispatcher("/listaArticoli.jsp");
		
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getParameter("action").equalsIgnoreCase("1")) 
		{		
			Integer id= Integer.parseInt(request.getParameter("id"));
			DAOFactory.makeArticoloDAO().delete(id);
		}
		else if(request.getParameter("action").equalsIgnoreCase("2"))
		{
			Integer id= Integer.parseInt(request.getParameter("id"));
			Articolo articolo = (Articolo) DAOFactory.makeArticoloDAO().findByPrimaryKey(id);
			List <Model> commenti= DAOFactory.makeCommentoDAO().findAll(id);
			request.getSession().setAttribute("articolo", articolo);
			request.getSession().setAttribute("commenti", commenti);
		}
		else if (request.getParameter("action").equalsIgnoreCase("3"))
		{
			String titolo= request.getParameter("titolo");
			String contenuto= request.getParameter("contenuto");
			Articolo articolo = new Articolo();
			articolo.setTitolo(titolo);
			articolo.setContenuto(contenuto);
			articolo.setIdAutore(1);
			DAOFactory.makeArticoloDAO().save(articolo);
			doGet(request, response);
		}
		else if (request.getParameter("action").equalsIgnoreCase("4"))
		{
			String titolo= request.getParameter("titolo");
			String contenuto= request.getParameter("contenuto");
			Articolo articolo = (Articolo) request.getSession().getAttribute("articolo");			
			articolo.setId(articolo.getId());
			articolo.setTitolo(titolo);
			articolo.setContenuto(contenuto);
			articolo.setIdAutore(1);
			DAOFactory.makeArticoloDAO().update(articolo);
			doGet(request, response);
		}
		else if (request.getParameter("action").equalsIgnoreCase("5"))
		{
			Articolo articolo= (Articolo) request.getSession().getAttribute("articolo");
			Utente utente= (Utente) request.getSession().getAttribute("user");
			if(request.getParameter("bool").equalsIgnoreCase("0"))
				DAOFactory.makeArticoloDAO().addLikeDislike(articolo.getId(), utente.getId(), false);
			else
				DAOFactory.makeArticoloDAO().addLikeDislike(articolo.getId(), utente.getId(), true);
			articolo = (Articolo) DAOFactory.makeArticoloDAO().findByPrimaryKey(articolo.getId());
			request.getSession().setAttribute("articolo", articolo);
			doGet(request, response);		
		}
		else if (request.getParameter("action").equalsIgnoreCase("7"))
		{
			Articolo articolo= (Articolo) request.getSession().getAttribute("articolo");
			Utente utente= (Utente) request.getSession().getAttribute("user");
			Commento commento= new Commento();
			commento.setIdArticolo(articolo.getId());
			commento.setIdUtente(utente.getId());
			commento.setContenuto (request.getParameter("commento"));
			List <Model> commenti= DAOFactory.makeCommentoDAO().findAll(articolo.getId());
			commenti.add(commento);
			request.setAttribute("commenti", commenti);
			DAOFactory.makeCommentoDAO().save(commento);
			doGet(request,response);
		}
		else if (request.getParameter("action").equalsIgnoreCase("8"))
		{
			DAOFactory.makeCommentoDAO().delete(Integer.parseInt(request.getParameter("id")));
			Articolo articolo= (Articolo) request.getSession().getAttribute("articolo");
			List <Model> commenti= DAOFactory.makeCommentoDAO().findAll(articolo.getId());
			request.getSession().setAttribute("commenti", commenti);
		}
	}	
}