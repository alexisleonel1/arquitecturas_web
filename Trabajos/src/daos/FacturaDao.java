package daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import clases.Factura;
import factory.MySqlJDBC;
import interfaz.Dao;

public class FacturaDao implements Dao<Factura> {
	private MySqlJDBC conexion;

	public FacturaDao(MySqlJDBC conn) {
		this.conexion = conn;
	}

	@Override
	public void createTable() throws SQLException {
		Connection conn = conexion.getConnection();
		String table = "CREATE TABLE factura(" + "idFactura INT NOT NULL," + "idCliente INT NOT NULL,"
				+ "PRIMARY kEY(idFactura)," + "FOREIGN KEY (idCliente) REFERENCES cliente (idCliente))";
		conn.prepareStatement(table).execute();
		conn.commit();
		conn.close();
	}

	@Override
	public void save(CSVParser dataFactura) throws SQLException {
		Connection conn = conexion.getConnection();
		for(CSVRecord row: dataFactura) {
			this.insert(conn,Integer.parseInt(row.get("idFactura")),Integer.parseInt(row.get("idCliente")));
		}
		conn.commit();
		conn.close();
	}
	
	private void insert(Connection conn, int idFactura, int idCliente)  throws SQLException {
		String insert = "INSERT INTO factura (idFactura,idCliente) VALUES (?, ?)";
		PreparedStatement ps = conn.prepareStatement(insert);
		ps.setInt(1, idFactura);
		ps.setInt(2, idCliente);
		ps.executeUpdate();
		ps.close();
	}

}
