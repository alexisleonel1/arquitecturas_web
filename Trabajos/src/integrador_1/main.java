package integrador_1;


import java.sql.Connection;
import java.sql.SQLException;

public class main {

	public static void main(String[] args) {
		Conexion conexion = new Conexion();
		
		crearTablas(conexion);
	
	}
	
	public static void crearTablas(Conexion conexion) {
		try {
			Connection conn = conexion.getConnection();
			conn.setAutoCommit(false);
			Cliente cliente = new Cliente(conn);
			conexion.endConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		try {
			Connection conn = conexion.getConnection();
			conn.setAutoCommit(false);
			Factura factura = new Factura(conn);
			conexion.endConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		try {
			Connection conn = conexion.getConnection();
			conn.setAutoCommit(false);
			Producto producto = new Producto(conn);
			conexion.endConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}

