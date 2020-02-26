package persistence.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import model.Commento;
import model.Model;

public class CommentoDaoJDBC extends ArticoloDaoJDBC{


	private CommentoDaoJDBC(DataSource dataSource) {
		super(dataSource);
	}
	private static CommentoDaoJDBC instance= null;
	public static CommentoDaoJDBC getInstance(DataSource dataSource) {
		if (instance==null)
			instance= new CommentoDaoJDBC(dataSource);
		return instance;
	}

	public List<Model> findAll(int id) {
		Connection connection = null;
		List<Model> commenti = new LinkedList<>();
		try {
			connection = this.dataSource.getConnection();
			Commento commento;
			PreparedStatement statement;
			String query = "select * from commento where articolo = ?";
			statement = connection.prepareStatement(query);
			statement.setInt(1, id);
			ResultSet result = statement.executeQuery();
			while (result.next()) {
				commento = new Commento();
				commento.setId(result.getInt("id"));
				commento.setIdArticolo(result.getInt("articolo"));
				commento.setIdUtente(result.getInt("utente"));
				commento.setContenuto(result.getString("contenuto"));
				commenti.add(commento);
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
		return commenti;
	}

	@Override
	public void save(Model c) {
		Commento commento = (Commento) c;
		Connection connection = this.dataSource.getConnection();
		try {
			String insert = "INSERT INTO commento( articolo, utente, contenuto) values (?,?,?)";
			PreparedStatement statement = connection.prepareStatement(insert);
			statement.setInt(1, commento.getIdArticolo());
			statement.setInt(2, commento.getIdUtente());
			statement.setString(3,commento.getContenuto());
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
	public void delete(int Id) {
		Connection connection = this.dataSource.getConnection();
		try {
			String delete = "DELETE FROM commento WHERE id = ?";
			PreparedStatement statement = connection.prepareStatement(delete);
			statement.setInt(1, Id);
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
	
	
}
