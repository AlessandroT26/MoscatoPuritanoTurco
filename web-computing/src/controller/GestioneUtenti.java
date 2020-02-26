package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import model.Model;
import model.Utente;
import persistence.dao.DAOFactory;


@WebServlet(name = "/ListaUtenti", value = "/ListaUtenti")
public class GestioneUtenti extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		Object utente = req.getSession().getAttribute("user");
		String isLogout = req.getParameter("logout");
		if (isLogout != null && isLogout.equals("true")) {
			req.getSession().removeAttribute("user");
			RequestDispatcher rd = req.getRequestDispatcher("/login.jsp");
			rd.forward(req, resp);
		}else {
			if (utente == null) {
				RequestDispatcher rd = req.getRequestDispatcher("/login.jsp");
				rd.forward(req, resp);
			}
		}
		
		if(req.getParameter("key")!=null && req.getParameter("key").equalsIgnoreCase("2")) {
			RequestDispatcher rd = req.getRequestDispatcher("/modificaUtente.jsp");
			rd.forward(req, resp);
			return;
		}
		List<Model> utenti=DAOFactory.getUtenteDAO().findAll();
		if(!(utenti.isEmpty())) {
			req.getSession().setAttribute("utenti", utenti);			
		}
		RequestDispatcher rd = req.getRequestDispatcher("/listaUtenti.jsp");
		rd.forward(req, resp);
	}


	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {	
		if(req.getParameter("action").equalsIgnoreCase("registra")) {
			String username = req.getParameter("username");
			Model utenteDaControllare = DAOFactory.getUtenteDAO().findByUsername(username);
			if(utenteDaControllare != null) {
				resp.getWriter().print("error");
			}else {
			String email = req.getParameter("email");
			String password = req.getParameter("password");
			Utente utente = new Utente(username, email,password);
			DAOFactory.getUtenteDAO().save(utente);
			}
		}
		else if(req.getParameter("action").equalsIgnoreCase("modifica")) {
			int id= Integer.parseInt(req.getParameter("id"));
			Utente utente = (Utente) DAOFactory.getUtenteDAO().findByPrimaryKey(id);
			req.getSession().setAttribute("utente", utente);
		} 
		else if(req.getParameter("action").equalsIgnoreCase("elimina")) {
			int id= Integer.parseInt(req.getParameter("id"));
			Utente utente = (Utente) DAOFactory.getUtenteDAO().findByPrimaryKey(id);
			DAOFactory.getUtenteDAO().delete(utente.getId());
		}
		else if (req.getParameter("action").equalsIgnoreCase("confermaModifica"))
		{
			String username= req.getParameter("username");
			String email= req.getParameter("email");
			String password= req.getParameter("password");
			Utente utente = (Utente) req.getSession().getAttribute("utente");	
			utente.setUsername(username);
			utente.setEmail(email);
			utente.setPassword(password);
			DAOFactory.getUtenteDAO().update(utente);
			doGet(req, resp);
		}

	}

}
