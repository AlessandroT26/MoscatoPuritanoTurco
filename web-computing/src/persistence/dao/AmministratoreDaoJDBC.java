package persistence.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import model.Model;
import model.Utente;
public class AmministratoreDaoJDBC implements Dao{
	private DataSource dataSource;

	private AmministratoreDaoJDBC(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	private static AmministratoreDaoJDBC instance= null;
	public static AmministratoreDaoJDBC getInstance(DataSource dataSource) {
		if (instance==null)
			instance= new AmministratoreDaoJDBC(dataSource);
		return instance;
	}
	
	@Override
	public List<Model> findAll() {
		Connection connection = this.dataSource.getConnection();
		List<Model> amministratori = new LinkedList<>();
		try {
			Utente amministratore;
			PreparedStatement statement;
			String query = "select A.id, U.username, U.email, U.password from admin A, utente U where U.id=A.id ";
			statement = connection.prepareStatement(query);
			ResultSet result = statement.executeQuery();
			while (result.next()) {
				amministratore = new Utente();
				amministratore.setId(result.getInt("id"));
				amministratore.setUsername(result.getString("username"));
				amministratore.setEmail(result.getString("email"));
				amministratore.setPassword(result.getString("password"));
				amministratori.add(amministratore);
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
		return amministratori;
	}

	
	@Override
	public void save(Model amministratore) {
	}

	@Override
	public Model findByPrimaryKey(int id) {
		Connection connection = this.dataSource.getConnection();
		Utente amministratore = null;
		try {
			PreparedStatement statement;
			String query = "select A.id, U.username, U.email, U.password from admin A, utente U where A.Id = ? and U.id=A.id";
			statement = connection.prepareStatement(query);
			statement.setInt(1, id);
			ResultSet result = statement.executeQuery();
			if (result.next()) {
				amministratore = new Utente();
				amministratore.setId(result.getInt("id"));
				amministratore.setUsername(result.getString("username"));
				amministratore.setPassword(result.getString("password"));
				amministratore.setEmail(result.getString("email"));
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
		return amministratore;
	}
	public int getIdByUsername(String username)
	{
		int id=0;
		Connection connection = this.dataSource.getConnection();
		try {
			PreparedStatement statement;
			String query= "select A.id from admin A, utente U where A.id= U.id and U.username=?";
			statement=connection.prepareStatement(query);
			statement.setString(1, username);
			ResultSet result = statement.executeQuery();
			if (result.next()) {
				id =result.getInt(1);
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
		return id;
	}
	@Override
	public void update(Model amministratore) {
	}

	@Override
	public void delete(int amministratore) {
	}


}
