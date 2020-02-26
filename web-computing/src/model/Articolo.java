package model;

public class Articolo implements Model{
	
	private int id;
	private String titolo;
	private String contenuto;
	private int idAutore;
	private int miPiace;
	private int nonMiPiace;
	
	public int getIdAutore() {
		return idAutore;
	}

	public void setIdAutore(int idAutore) {
		this.idAutore = idAutore;
	}

	@Override
	public int getId() {
		return id;
	}
	
	@Override
	public void setId(int id) {
		this.id=id;
	}

	public String getTitolo() {
		return titolo;
	}

	public void setTitolo(String titolo) {
		this.titolo = titolo;
	}

	public String getContenuto() {
		return contenuto;
	}

	public void setContenuto(String contenuto) {
		this.contenuto = contenuto;
	}

	public int getMiPiace() {
		return miPiace;
	}

	public int getNonMiPiace() {
		return this.nonMiPiace;	
		}

	public void setMiPiace(int miPiace) {
		this.miPiace = miPiace;
	}

	public void setNonMiPiace(int nonMiPiace) {
		this.nonMiPiace = nonMiPiace;
	}

}