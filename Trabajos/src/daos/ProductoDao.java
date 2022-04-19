package daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import clases.Producto;
import factory.MySqlJDBC;
import interfaz.Dao;

public class ProductoDao implements Dao<Producto> {

	private MySqlJDBC conexion;

	public ProductoDao(MySqlJDBC conn) {
		this.conexion = conn;
	}

	@Override
	public void createTable() throws SQLException {
		Connection conn = conexion.getConnection();
		String table = "CREATE TABLE IF NOT EXISTS producto(" + "idProducto INT," + "nombre VARCHAR(45),"
				+ "valor FLOAT," + "PRIMARY kEY(idProducto))";
		conn.prepareStatement(table).execute();
		conn.commit();
		conn.close();
	}

	@Override
	public void save(CSVParser dataProducto) throws SQLException {
		Connection conn = conexion.getConnection();
		for(CSVRecord row: dataProducto) {
			this.insert(conn,Integer.parseInt(row.get("idProducto")),row.get("nombre"),Float.parseFloat(row.get("valor")));
		}
		conn.commit();
		conn.close();

	}
	
	private void insert(Connection conn, int idProducto, String nombre, float valor)  throws SQLException {
		String insert = "INSERT INTO producto (idProducto,nombre,valor) VALUES (?, ?, ?)";
		PreparedStatement ps = conn.prepareStatement(insert);
		ps.setInt(1, idProducto);
		ps.setString(2, nombre);
		ps.setFloat(3, valor);
		ps.executeUpdate();
		ps.close();
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
