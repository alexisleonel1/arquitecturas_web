package integrador_1;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class ClienteDao implements Dao {
	
	private Conexion conexion;
	
	public ClienteDao() throws SQLException {
		this.conexion = new Conexion();
		//this.createTable();
	}

	private void createTable() throws SQLException {
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
	public Optional get(int id) throws SQLException {
		Connection conn = conexion.getConnection();
		String select = "SELECT * FROM cliente WHERE idCliente = ?";
		PreparedStatement ps = conn.prepareStatement(select);
		ps.setInt(1, id);
		ResultSet rs = ps.executeQuery();
		rs.next();
		String r = rs.getInt(1)+", "+rs.getString(2)+", "+rs.getString(3);
		conn.close();
		return Optional.ofNullable(r);
	}

	@Override
	public List getAll() throws SQLException {
		Connection conn = conexion.getConnection();
		String select = "SELECT * FROM cliente";
		PreparedStatement ps = conn.prepareStatement(select);
		ResultSet rs = ps.executeQuery();
		conn.close();
		return null;
	}

	@Override
	public void save(Object t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Object t, String[] params) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Object t) {
		// TODO Auto-generated method stub
		
	}
}
