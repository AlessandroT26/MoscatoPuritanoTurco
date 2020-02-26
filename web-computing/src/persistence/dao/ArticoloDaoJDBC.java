package persistence.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import model.Articolo;
import model.Model;
public class ArticoloDaoJDBC implements Dao {
	protected DataSource dataSource;

	protected ArticoloDaoJDBC(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	private static ArticoloDaoJDBC instance= null;
	public static ArticoloDaoJDBC getInstance(DataSource dataSource) {
		if (instance==null)
			instance= new ArticoloDaoJDBC(dataSource);
		return instance;
	}
	@Override
	public List<Model> findAll() {
		Connection connection = null;
		List<Model> articoli = new LinkedList<>();
		try {
			connection = this.dataSource.getConnection();
			Articolo articolo;
			PreparedStatement statement;
			String query = "select * from articolo";
			statement = connection.prepareStatement(query);
			ResultSet result = statement.executeQuery();
			while (result.next()) {
				articolo = new Articolo();
				articolo.setId(result.getInt("id"));
				articolo.setTitolo(result.getString("titolo"));
				articolo.setContenuto(result.getString("contenuto"));
				articolo.setIdAutore(result.getInt("autore"));
				articolo.setMiPiace(result.getInt("mipiace"));
				articolo.setNonMiPiace(result.getInt("nonmipiace"));
				articoli.add(articolo);
			}
		} catch (SQLException e) {
			throw new PersistenceException(e.getMessage());
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				throw new PersistenceException(e.getMessage());
			}
		}
		return articoli;
	}

	
	@Override
	public void save(Model a) {
		Articolo articolo = (Articolo) a;
		Connection connection = this.dataSource.getConnection();
		try {
			String insert = "INSERT INTO articolo( titolo, contenuto, autore) values (?,?,?)";
			PreparedStatement statement = connection.prepareStatement(insert);
			statement.setString(1, articolo.getTitolo());
			statement.setString(2, articolo.getContenuto());
			statement.setInt(3,articolo.getIdAutore());
			statement.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage());
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				throw new RuntimeException(e.getMessage());
			}
		}
	}

	@Override
	public Model findByPrimaryKey(int id) {
		Connection connection = this.dataSource.getConnection();
		Articolo a = new Articolo();
		try {
			PreparedStatement statement;
			String query = "select * from articolo where id = ?";
			statement = connection.prepareStatement(query);
			statement.setInt(1, id);
			ResultSet result = statement.executeQuery();
			if (result.next()) {
				a.setId(result.getInt("id"));
				a.setTitolo(result.getString("titolo"));
				a.setContenuto(result.getString("contenuto"));
				a.setIdAutore(result.getInt("autore"));
				a.setMiPiace(result.getInt("mipiace"));
				a.setNonMiPiace(result.getInt("nonmipiace"));
			}
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage());
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				throw new RuntimeException(e.getMessage());
			}
		}
		Model articolo = a;
		return articolo;
	}

	@Override
	public void update(Model a) {
		Articolo articolo = (Articolo) a;
		Connection connection = this.dataSource.getConnection();
		try {
			String update = "update articolo  SET titolo = ?, contenuto = ?, autore = ? WHERE id= ?";
			PreparedStatement statement = connection.prepareStatement(update);
			statement.setString(1, articolo.getTitolo());
			statement.setString(2, articolo.getContenuto());
			statement.setInt(3, articolo.getIdAutore());
			statement.setInt(4,  articolo.getId());
			statement.executeUpdate();
		} catch (SQLException e) {
			throw new PersistenceException(e.getMessage());
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				throw new PersistenceException(e.getMessage());
			}
		}
	}
	public void addLikeDislike(int idArticolo, int idUtente, boolean bool)
	{
		
		Connection connection = this.dataSource.getConnection();
		if(checkIfExists(idUtente,idArticolo)) 
			try {
				String like = "update vsual SET piace = ? where articolo=? and utente=? and piace != ?";
				PreparedStatement statement = connection.prepareStatement(like);
				statement.setInt(2, idArticolo);
				statement.setInt(3, idUtente);
				statement.setBoolean(1, bool);
				statement.setBoolean(4, bool);
				statement.executeUpdate();
			} catch (SQLException e) {
				throw new PersistenceException(e.getMessage());
			}
		else
			try {
				String like = "Insert into vsual (articolo, utente, piace) values (?,?,?)";
				PreparedStatement statement = connection.prepareStatement(like);
				statement.setInt(1, idArticolo);
				statement.setInt(2, idUtente);
				statement.setBoolean(3, bool);
				statement.executeUpdate();
			} catch (SQLException e) {
				throw new PersistenceException(e.getMessage());
			} 
		finally {
			try {
				connection.close();
			} catch (SQLException e) {
				throw new PersistenceException(e.getMessage());
			}
		}
	}
	public boolean checkIfExists(int idUtente, int idArticolo)
	{
		Connection connection = this.dataSource.getConnection();
		try {
			String query = "select * from vsual where utente = ? and articolo = ?";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setInt(1, idUtente);
			statement.setInt(2, idArticolo);
			ResultSet result = statement.executeQuery();
			if (result.next()) {
				return true;
			}
			else
				return false;
		} catch (SQLException e) {
			throw new PersistenceException(e.getMessage());
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				throw new PersistenceException(e.getMessage());
			}
		}
	}
	@Override
	public void delete(int Id) {
		Connection connection = this.dataSource.getConnection();
		try {
			String delete = "delete from vsual where articolo= ?;DELETE FROM articolo WHERE id = ?; ";
			PreparedStatement statement = connection.prepareStatement(delete);
			statement.setInt(1, Id);
			statement.setInt(2, Id);
			statement.executeUpdate();
		} catch (SQLException e) {
			throw new PersistenceException(e.getMessage());
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				throw new PersistenceException(e.getMessage());
			}
		}
	}
	public int getLikeDislikeFromDatabase(int idArticolo, boolean like)
	{
		int num = 0;
		Connection connection = this.dataSource.getConnection();
		try {
			PreparedStatement statement;
			String query = "select * from vsual where articolo = ? and piace= ?";
			statement = connection.prepareStatement(query);
			statement.setInt(1, idArticolo);
			statement.setBoolean(2, like);
			ResultSet result = statement.executeQuery();
			while (result.next())
				num++;
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage());
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				throw new RuntimeException(e.getMessage());
			}
		}
		return num;
	}
}
