package integrador_1;


import java.sql.Connection;
import java.sql.SQLException;

public class Factura {
	
	public Factura(Connection conn) throws SQLException {
		this.createTable(conn);
	}
	
	private void createTable(Connection conn) throws SQLException {
		String table = "CREATE TABLE factura(" +
		"idFactura INT NOT NULL,"+
		"idCliente INT NOT NULL,"+
		"PRIMARY kEY(idFactura))";
		/*"FOREIGN KEY (idCliente) REFERENCES cliente (idCliente)";*/
		conn.prepareStatement(table).execute();
		conn.commit();
	}

}
