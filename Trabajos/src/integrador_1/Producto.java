package integrador_1;


import java.sql.Connection;
import java.sql.SQLException;

public class Producto {

	public Producto(Connection conn) throws SQLException {
		this.createTable(conn);
	}

	private void createTable(Connection conn) throws SQLException {
		String table = "CREATE TABLE producto(" +
		"idProducto INT,"+
		"nombre VARCHAR(45),"+
		"valor FLOAT,"+
		"PRIMARY kEY(idProducto))";
		conn.prepareStatement(table).execute();
		conn.commit();
	}

}
