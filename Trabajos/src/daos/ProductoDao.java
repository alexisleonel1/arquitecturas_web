package daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import clases.Producto;
import factory.MySqlJDBC;
import interfaz.Dao;

public class ProductoDao implements Dao<Producto> {

	private MySqlJDBC conexion;

	public ProductoDao() throws SQLException {
		this.conexion = new MySqlJDBC();
	}

	@Override
	public void createTable() throws SQLException {
		Connection conn = conexion.getConnection();
		String table = "CREATE TABLE IF NOT EXIST producto(" + "idProducto INT," + "nombre VARCHAR(45),"
				+ "valor FLOAT," + "PRIMARY kEY(idProducto))";
		conn.prepareStatement(table).execute();
		conn.commit();
		conn.close();
	}

	@Override
	public void save(String[] t) throws SQLException {
		Connection conn = conexion.getConnection();
		String insert = "INSERT INTO producto (idProducto,nombre,valor) VALUES (?, ?, ?)";
		PreparedStatement ps = conn.prepareStatement(insert);
		ps.setInt(1, Integer.parseInt(t[0]));
		ps.setString(2, t[1]);
		ps.setFloat(3, Float.parseFloat(t[2]));
		ps.executeUpdate();
		ps.close();
		conn.commit();
		conn.close();

	}

	public Producto mayorRecaudacion() throws SQLException {
		Connection conn = conexion.getConnection();
		String select = "SELECT p.*, SUM(fp.cantidad * p.valor) " + "FROM producto p LEFT JOIN factura_producto fp "
				+ "ON (p.idProducto = fp.idProducto) " + "GROUP BY p.idProducto "
				+ "ORDER BY SUM(fp.cantidad * p.valor) DESC LIMIT 1";
		PreparedStatement ps = conn.prepareStatement(select);
		ResultSet rs = ps.executeQuery();
		rs.next();
		Producto p = new Producto(rs.getInt(1), rs.getString(2), rs.getFloat(3));
		conn.close();
		return p;
	}

}
