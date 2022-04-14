package integrador_1;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ClienteDao implements Dao {
	
	private Conexion conexion;
	
	public ClienteDao() throws SQLException {
		this.conexion = new Conexion();
	}

	@Override
	public void createTable() throws SQLException {
		Connection conn = conexion.getConnection();
		String table = "CREATE TABLE cliente(" +
		"idCliente INT NOT NULL,"+
		"nombre VARCHAR(500),"+
		"email VARCHAR(150),"+
		"PRIMARY kEY(idCliente))";
		conn.prepareStatement(table).execute();
		conn.commit();
		conn.close();
	}

	@Override
	public Optional<Cliente> get(int id) throws SQLException {
		Connection conn = conexion.getConnection();
		String select = "SELECT * FROM cliente WHERE idCliente = ?";
		PreparedStatement ps = conn.prepareStatement(select);
		ps.setInt(1, id);
		ResultSet rs = ps.executeQuery();
		rs.next();
		Cliente c = new Cliente(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getInt(4));
		conn.close();
		return Optional.ofNullable(c);
	}

	@Override
	public List<Cliente> getAll() throws SQLException {
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
	public void save(String[] t) throws SQLException {
		Connection conn = conexion.getConnection();
		String insert = "INSERT INTO cliente (idCliente,nombre,email) VALUES (?, ?, ?)";
		PreparedStatement ps = conn.prepareStatement(insert);
		ps.setInt(1, Integer.parseInt(t[0]));
		ps.setString(2, t[1]);
		ps.setString(3, t[2]);
		ps.executeUpdate();
		ps.close();
		conn.commit();
		conn.close();
	}
}
