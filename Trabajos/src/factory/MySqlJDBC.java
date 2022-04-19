package factory;


import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import daos.ClienteDao;
import daos.FacturaDao;
import daos.Factura_productoDao;
import daos.ProductoDao;
import interfaz.Dao;

public class MySqlJDBC extends DaoFactory {
	
	public static final String CLIENTE = "cliente";
	public static final String FACTURA = "factura";
	public static final String FACTURA_PRODUCTO = "factura_producto";
	public static final String PRODUCTO = "producto";
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
	
	public Connection getConnection() throws SQLException {
		this.startConnection();
		return this.conn;
	}
	
	public Dao getCustomerDAO(String customer) {
		switch (customer) {
			case CLIENTE : return new ClienteDao(this);
			case FACTURA : return new FacturaDao(this);
			case FACTURA_PRODUCTO : return new Factura_productoDao(this);
			case PRODUCTO : return new ProductoDao(this);
			default : return null;
		}
	}
}
