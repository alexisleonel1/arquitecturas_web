package main;


import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import clases.Cliente;
import daos.ClienteDao;
import daos.FacturaDao;
import daos.Factura_productoDao;
import daos.ProductoDao;
import factory.MySqlJDBC;

public class main {

	public static ClienteDao c = null;
	
	public static void main(String[] args) {
		MySqlJDBC conexion = new MySqlJDBC();
		
		crearTablas(conexion);
		
		try {
			Connection conn = conexion.getConnection();
			c = new ClienteDao();
			//c.get(1);
			List<Cliente> rs = c.getAll();
			//Optional rs2 = c.get(1);
			System.out.println(rs);
			//System.out.println(rs2);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		CSVParser parser;
		try {
			parser = CSVFormat.DEFAULT.withHeader().parse(new
					FileReader("src/integrador_1/csv/clientes.csv"));
			for(CSVRecord row: parser) {
				String[] data = {row.get("idCliente"),row.get("nombre"),row.get("email")};
				c.save(data);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}
	
	public static void crearTablas(MySqlJDBC conexion) {
		try {
			Connection conn = conexion.getConnection();
			conn.setAutoCommit(false);
			ClienteDao cliente = new ClienteDao();
			cliente.createTable();
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

