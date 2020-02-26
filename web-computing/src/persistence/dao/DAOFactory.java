package persistence.dao;
public class DAOFactory {
	public static DataSource dataSource;
	static {
		try {
			Class.forName("org.postgresql.Driver").newInstance();
			dataSource=new DataSource("jdbc:postgresql://manny.db.elephantsql.com:5432/vqvfdgbz","vqvfdgbz","WUUJDXj2WBU9DXvZkrPR30vz0z1839cY");
		} catch (Exception e) {
			System.out.println("MySQLDAOFactory.class: failed to load MySQL JDBC driver\n" + e);
			e.printStackTrace();
		}
	}

	public static UtenteDaoJDBC getUtenteDAO() {
		return new UtenteDaoJDBC(dataSource);
	}
	
	public static Dao getAmministratoreDAO() {
		return AmministratoreDaoJDBC.getInstance(dataSource);
	}

	public static ArticoloDaoJDBC makeArticoloDAO() {
		return ArticoloDaoJDBC.getInstance(dataSource);
	}
	
	public static CommentoDaoJDBC makeCommentoDAO() {
		return CommentoDaoJDBC.getInstance(dataSource);
	}
	
}
