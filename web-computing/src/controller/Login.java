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
import persistence.dao.DAOFactory;

@WebServlet(value="/Login", name="/Login")
public class Login extends HttpServlet {
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
			else {
				List<Model> articoli=DAOFactory.makeArticoloDAO().findAll();
				if(!(articoli.isEmpty())) 
					req.getSession().setAttribute("articoli", articoli);
				RequestDispatcher rd = req.getRequestDispatcher("/homeAmm.jsp");
				rd.forward(req, resp);
			}
		}
	}

	
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		Model utente = DAOFactory.getUtenteDAO().login(username, password);
		if (utente != null) {
			req.getSession().setAttribute("user", utente);
			req.getSession().setAttribute("login", true);
		} else
			resp.getWriter().print("error");
	}
}


