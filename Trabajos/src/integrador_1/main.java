package integrador_1;


import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Optional;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

public class main {

	public static void main(String[] args) {
		Conexion conexion = new Conexion();
		
		//crearTablas(conexion);
		
		try {
			Connection conn = conexion.getConnection();
			ClienteDao c = new ClienteDao();
			//c.get(1);
			Optional rs = c.get(0);
			Optional rs2 = c.get(1);
			System.out.println(rs);
			System.out.println(rs2);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		/*CSVParser parser;
		try {
			parser = CSVFormat.DEFAULT.withHeader().parse(new
					FileReader("src/integrador_1/csv/productos.csv"));
			for(CSVRecord row: parser) {
				System.out.println(row.get("idProducto"));
				System.out.println(row.get("nombre"));
				System.out.println(row.get("valor"));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}*/
	
	}
	
	public static void crearTablas(Conexion conexion) {
		try {
			Connection conn = conexion.getConnection();
			conn.setAutoCommit(false);
			ClienteDao cliente = new ClienteDao(conn);
			conexion.endConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		try {
			Connection conn = conexion.getConnection();
			conn.setAutoCommit(false);
			FacturaDao factura = new FacturaDao(conn);
			conexion.endConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		try {
			Connection conn = conexion.getConnection();
			conn.setAutoCommit(false);
			ProductoDao producto = new ProductoDao(conn);
			conexion.endConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		try {
			Connection conn = conexion.getConnection();
			conn.setAutoCommit(false);
			Factura_productoDao factura_producto = new Factura_productoDao(conn);
			conexion.endConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}

