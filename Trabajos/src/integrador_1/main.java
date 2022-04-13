package integrador_1;


import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

public class main {

	public static void main(String[] args) {
		Conexion conexion = new Conexion();
		
		//crearTablas(conexion);
		
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

