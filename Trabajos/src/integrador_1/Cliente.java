package integrador_1;


import java.sql.Connection;
import java.sql.SQLException;

public class Cliente {
	
	public Cliente(Connection conn) throws SQLException {
		this.createTable(conn);
	}

	private void createTable(Connection conn) throws SQLException {
		String table = "CREATE TABLE cliente(" +
		"idCliente INT NOT NULL,"+
		"nombre VARCHAR(500),"+
		"email VARCHAR(150),"+
		"PRIMARY kEY(idCliente))";
		conn.prepareStatement(table).execute();
		conn.commit();
	}
}
