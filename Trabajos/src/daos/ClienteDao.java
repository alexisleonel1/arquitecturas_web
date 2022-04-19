package daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import clases.Cliente;
import factory.MySqlJDBC;
import interfaz.Dao;

public class ClienteDao implements Dao<Cliente> {
	
	private MySqlJDBC conexion;
	
	public ClienteDao(MySqlJDBC conn) {
		this.conexion = conn;
	}

	@Override
	public void createTable() throws SQLException {
		Connection conn = conexion.getConnection();
		String table = "CREATE TABLE IF NOT EXISTS cliente(" +
		"idCliente INT NOT NULL,"+
		"nombre VARCHAR(500),"+
		"email VARCHAR(150),"+
		"PRIMARY kEY(idCliente))";
		conn.prepareStatement(table).execute();
		conn.commit();
		conn.close();
	}


	public List<Cliente> mejoresClientes() throws SQLException {
		List<Cliente> clientes = new ArrayList<Cliente>();
		Connection conn = conexion.getConnection();
		String select = "SELECT c.*, COUNT(f.idFactura)"
				+ "FROM cliente c LEFT JOIN factura f "
				+ "ON (c.idCliente = f.idCliente) "
				+ "GROUP BY c.idCliente "
				+ "ORDER BY COUNT(f.idFactura) DESC";
		PreparedStatement ps = conn.prepareStatement(select);
		ResultSet rs = ps.executeQuery();
		while(rs.next()) {
			Cliente c = new Cliente(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getInt(4));
			clientes.add(c);
		}
		conn.close();
		return clientes;
	}

	@Override
	public void save(CSVParser dataCliente) throws SQLException {
		Connection conn = conexion.getConnection();
		for(CSVRecord row: dataCliente) {
			this.insert(conn,Integer.parseInt(row.get("idCliente")),row.get("nombre"),row.get("email"));
		}
		conn.commit();
		conn.close();
	}
	
	private void insert(Connection conn, int id, String nombre, String email)  throws SQLException {
		String insert = "INSERT INTO cliente (idCliente,nombre,email) VALUES (?, ?, ?)";
		PreparedStatement ps = conn.prepareStatement(insert);
		ps.setInt(1, id);
		ps.setString(2, nombre);
		ps.setString(3, email);
		ps.executeUpdate();
		ps.close();
	}
}
