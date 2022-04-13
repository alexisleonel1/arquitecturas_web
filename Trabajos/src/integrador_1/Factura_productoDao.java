package integrador_1;

import java.sql.Connection;
import java.sql.SQLException;

public class Factura_productoDao {

	public Factura_productoDao(Connection conn) throws SQLException {
		this.createTable(conn);
	}
	
	private void createTable(Connection conn) throws SQLException {
		String table = "CREATE TABLE factura_producto(" +
		"idProducto INT,"+
		"idFactura INT,"+
		"cantidad INT,"+
		"PRIMARY kEY(idProducto,idFactura),"+
		"FOREIGN KEY (idProducto) REFERENCES producto (idProducto),"+
		"FOREIGN KEY (idFactura) REFERENCES factura (idFactura))";
		conn.prepareStatement(table).execute();
		conn.commit();
	}

}