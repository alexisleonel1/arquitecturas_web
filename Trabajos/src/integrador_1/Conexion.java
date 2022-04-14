package integrador_1;


import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
	
	private Connection conn;
	
	private void startConnection() throws SQLException {
		String driver = "com.mysql.cj.jdbc.Driver";
		
		try {
			Class.forName(driver).getDeclaredConstructor().newInstance();
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
				| NoSuchMethodException | SecurityException | ClassNotFoundException e) {
			e.printStackTrace();
			System.exit(1);
		}
	
		String uri = "jdbc:mysql://localhost:3306/exampleDB";
		
		this.conn = DriverManager.getConnection(uri,"root","");
		this.conn.setAutoCommit(false);
	}
	
	public void endConnection() throws SQLException {
		if(this.conn != null) {
			this.conn.close();
		}
	}
	
	public Connection getConnection() throws SQLException {
		this.startConnection();
		return this.conn;
	}
}
