package daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import clases.Factura;
import factory.MySqlJDBC;
import interfaz.Dao;

public class FacturaDao implements Dao<Factura> {
	private MySqlJDBC conexion;

	public FacturaDao() throws SQLException {
		this.conexion = new MySqlJDBC();
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
	public void save(String[] t) throws SQLException {
		Connection conn = conexion.getConnection();
		String insert = "INSERT INTO factura (idFactura,idCliente) VALUES (?, ?)";
		PreparedStatement ps = conn.prepareStatement(insert);
		ps.setInt(1, Integer.parseInt(t[0]));
		ps.setInt(2, Integer.parseInt(t[1]));
		ps.executeUpdate();
		ps.close();
		conn.commit();
		conn.close();
	}

}
