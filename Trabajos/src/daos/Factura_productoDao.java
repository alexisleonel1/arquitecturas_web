package daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import clases.Factura_producto;
import factory.MySqlJDBC;
import interfaz.Dao;

public class Factura_productoDao implements Dao<Factura_producto> {
	private MySqlJDBC conexion;

	public Factura_productoDao(MySqlJDBC conn) {
		this.conexion = conn;
	}

	@Override
	public void createTable() throws SQLException {
		Connection conn = conexion.getConnection();
		String table = "CREATE TABLE factura_producto(" + "idProducto INT," + "idFactura INT," + "cantidad INT,"
				+ "PRIMARY kEY(idProducto,idFactura)," + "FOREIGN KEY (idProducto) REFERENCES producto (idProducto),"
				+ "FOREIGN KEY (idFactura) REFERENCES factura (idFactura))";
		conn.prepareStatement(table).execute();
		conn.commit();
		conn.close();
	}

	@Override
	public void save(CSVParser dataFactura_producto) throws SQLException {
		Connection conn = conexion.getConnection();
		for(CSVRecord row: dataFactura_producto) {
			this.insert(conn, Integer.parseInt(row.get("idProducto")), Integer.parseInt(row.get("idFactura")), Integer.parseInt(row.get("cantidad")));
		}
		conn.commit();
		conn.close();
	}
	
	private void insert(Connection conn, int idProducto, int idFactura, int cantidad)  throws SQLException {
		String insert = "INSERT INTO factura_producto (idFactura,idProducto,cantidad) VALUES (?, ?, ?)";
		PreparedStatement ps = conn.prepareStatement(insert);
		ps.setInt(1, idFactura);
		ps.setInt(2, idProducto);
		ps.setInt(3, cantidad);
		ps.executeUpdate();
		ps.close();
	}
}