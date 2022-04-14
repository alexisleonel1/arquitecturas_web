package daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import clases.Factura_producto;
import factory.MySqlJDBC;
import interfaz.Dao;

public class Factura_productoDao implements Dao<Factura_producto> {
	private MySqlJDBC conexion;

	public Factura_productoDao() throws SQLException {
		this.conexion = new MySqlJDBC();
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
	public void save(String[] t) throws SQLException {
		Connection conn = conexion.getConnection();
		String insert = "INSERT INTO factura_producto (idFactura,idProducto,cantidad) VALUES (?, ?, ?)";
		PreparedStatement ps = conn.prepareStatement(insert);
		ps.setInt(1, Integer.parseInt(t[0]));
		ps.setInt(2, Integer.parseInt(t[1]));
		ps.setInt(3, Integer.parseInt(t[2]));
		ps.executeUpdate();
		ps.close();
		conn.commit();
		conn.close();
	}
}