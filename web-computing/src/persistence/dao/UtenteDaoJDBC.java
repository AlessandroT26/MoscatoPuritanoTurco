package persistence.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import model.Model;
import model.Utente;
public class UtenteDaoJDBC implements Dao {
	private DataSource dataSource;

	public UtenteDaoJDBC(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	@Override
	public List<Model> findAll() {
		Connection connection = null;
		List<Model> utenti = new LinkedList<>();
		try {
			connection = this.dataSource.getConnection();
			Utente utente;
			PreparedStatement statement;
			String query = "select * from utente U, admin A where U.id != A.id";
			statement = connection.prepareStatement(query);
			ResultSet result = statement.executeQuery();
			while (result.next()) {
				utente = new Utente();
				utente.setId(result.getInt("id"));
				utente.setUsername(result.getString("username"));
				utente.setEmail(result.getString("email"));
				utente.setPassword(result.getString("password"));
				utenti.add(utente);
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
		return utenti;
	}

	
	@Override
	public void save(Model u) {
		Utente utente= (Utente) u;
		Connection connection = this.dataSource.getConnection();
		try {
			String insert = "INSERT INTO utente(username, email, password) values (?,?,?)";
			PreparedStatement statement = connection.prepareStatement(insert);
			statement.setString(1, utente.getUsername());
			statement.setString(2, utente.getEmail());
			statement.setString(3, utente.getPassword());
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

	public boolean loginAccess(String username, String password, Utente utente) {
		Connection connection = this.dataSource.getConnection();
		try {
			PreparedStatement statement;
			String query = "select * from utente U where U.username = ? and U.password = ?";
			statement = connection.prepareStatement(query);
			statement.setString(1, username);
			statement.setString(2, password);
			ResultSet result = statement.executeQuery();
			if (result.next()) {
				utente.setId(result.getInt("id"));
				return true;
			}
			return false;
		}catch (SQLException e) {
			throw new RuntimeException(e.getMessage());
		}
		finally {
		try {
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}		
		}	
	}
	
	public Model login(String username, String password) {
			Utente utente = new Utente();
		if(loginAccess(username, password, utente)) {			
			utente.setUsername(username);
			utente.setPassword(password);
			if(isAdmin(utente.getId()))
				utente.setAdmin(true);
			else
				utente.setAdmin(false);
			return utente;
		}
		else
			return null;
			
	}
	
	
	@Override
	public Model findByPrimaryKey(int id) {
		Connection connection = this.dataSource.getConnection();

		Utente utente = new Utente();
		try {
			PreparedStatement statement;
			String query = "select * from utente where id = ?";
			statement = connection.prepareStatement(query);
			statement.setInt(1, id);
			ResultSet result = statement.executeQuery();
			if (result.next()) {
				utente = new Utente();
				utente.setId(result.getInt("id"));
				utente.setUsername(result.getString("username"));
				utente.setEmail(result.getString("email"));
				utente.setPassword(result.getString("password"));
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
		return utente;
	}
	public boolean isAdmin(int id) {
		Connection connection = this.dataSource.getConnection();
		try {
			PreparedStatement statement;
			String query = "select * from admin where id = ?";
			statement = connection.prepareStatement(query);
			statement.setInt(1, id);
			ResultSet result = statement.executeQuery();
			if (result.next()) {
				return true;
			}
			else 
				return false;
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
	public void update(Model u) {
		Utente utente= (Utente) u;
		Connection connection = this.dataSource.getConnection();
		try {
			String update = "update utente SET username = ?, email = ?, password = ? where id=?";
			PreparedStatement statement = connection.prepareStatement(update);
			statement.setString(1, utente.getUsername());
			statement.setString(2, utente.getEmail());
			statement.setString(3, utente.getPassword());
			statement.setInt(4, utente.getId());
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

	@Override
	public void delete(int Id) {
		Connection connection = this.dataSource.getConnection();
		try {
			String delete = "DELETE FROM Utente WHERE id = ?";
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

	
	public Model findByUsername(String username) {
		Connection connection = this.dataSource.getConnection();
		Utente utente = null;
		try {
			PreparedStatement statement;
			String query = "select * from utente where username = ?";
			statement = connection.prepareStatement(query);
			statement.setString(1, username);
			ResultSet result = statement.executeQuery();
			if (result.next()) {
				utente = new Utente();
				utente.setUsername(result.getString("username"));
				utente.setEmail(result.getString("email"));
				utente.setPassword(result.getString("password"));
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
		return utente;
	}
	
	public String getUsernameFromId(int id)
	{
		Connection connection = this.dataSource.getConnection();
		String username= null;
		try {
			PreparedStatement statement;
			String query = "select username from utente where id = ?";
			statement = connection.prepareStatement(query);
			statement.setInt(1, id);
			ResultSet result = statement.executeQuery();
			if (result.next())
				username = result.getString("username");
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage());
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				throw new RuntimeException(e.getMessage());
			}
		}
		return username;
	}
}
