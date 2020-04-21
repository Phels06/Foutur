package Project20200421.context;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Context {
	// encapsule et gere la connection
	private Connection connection = null;

	private static Context singleton = null;

	static {// executer qu'une fois, lors de la mise en mémoire
		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}
	}

	private Context() {
		try {
			connection = DriverManager.getConnection("jdbc:postgresql://127.0.0.1:5432/videoclub", "postgres", "postgres");
			connection.setAutoCommit(false);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		// on ferme pas car le but c'est de la garder ouvert
	}

	public Connection getConnection() {
		return connection;

	}

	public static Context getInstance() {// singleton
		if (singleton == null) {
			singleton = new Context();
		}
		return singleton;
	}
	
	public static void destroy() {
		if(singleton!=null) {
			try {
				singleton.connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			singleton=null;
		}
	}
}
