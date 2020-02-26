package model;

import persistence.dao.DAOFactory;

public class Commento implements Model{
	
	private int id;
	private int idArticolo;
	private int idUtente;
	private String contenuto;
	
	
	@Override
	public int getId() {
		return id;
	}

	@Override
	public void setId(int id) {
		this.id=id;
	}
	
	public int getIdArticolo() {
		return idArticolo;
	}

	public void setIdArticolo(int idArticolo) {
		this.idArticolo = idArticolo;
	}

	public int getIdUtente() {
		return idUtente;
	}

	public void setIdUtente(int idUtente) {
		this.idUtente = idUtente;
	}

	public String getContenuto() {
		return contenuto;
	}

	public void setContenuto(String contenuto) {
		this.contenuto = contenuto;
	}
	
	public String getUtente()
	{
		return DAOFactory.getUtenteDAO().getUsernameFromId(idUtente);
	}


}
