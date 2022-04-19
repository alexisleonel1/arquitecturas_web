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
import clases.Factura;
import clases.Factura_producto;
import clases.Producto;
import daos.ClienteDao;
import daos.FacturaDao;
import daos.Factura_productoDao;
import daos.ProductoDao;
import factory.DaoFactory;
import factory.MySqlJDBC;
import interfaz.Dao;

public class main {

	public static ClienteDao c = null;
	
	public static void main(String[] args) {
		DaoFactory msqlFactory = DaoFactory.getDaoFactory(1);
		
		ClienteDao cliente = (ClienteDao) msqlFactory.getCustomerDAO("cliente");
		Dao<Factura> factura = msqlFactory.getCustomerDAO("factura");
		Dao<Factura_producto> factura_producto = msqlFactory.getCustomerDAO("factura_producto");
		ProductoDao producto = (ProductoDao) msqlFactory.getCustomerDAO("producto");
		
		try {
			cliente.createTable();
			producto.createTable();
			factura.createTable();
			factura_producto.createTable();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		CSVParser dataCliente = null;
		CSVParser dataProducto = null;
		CSVParser dataFactura = null;
		CSVParser dataFactura_producto = null;
		
		try {
			dataCliente = CSVFormat.DEFAULT.withHeader().parse(new
					FileReader("src/integrador_1/csv/clientes.csv"));
			
			dataProducto = CSVFormat.DEFAULT.withHeader().parse(new
					FileReader("src/integrador_1/csv/productos.csv"));
			
			dataFactura = CSVFormat.DEFAULT.withHeader().parse(new
					FileReader("src/integrador_1/csv/facturas.csv"));
			
			dataFactura_producto = CSVFormat.DEFAULT.withHeader().parse(new
					FileReader("src/integrador_1/csv/facturas-productos.csv"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		
		try {
			cliente.save(dataCliente);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		try {
			factura.save(dataFactura);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		try {
			producto.save(dataProducto);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		try {
			factura_producto.save(dataFactura_producto);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		try {
			List<Cliente> mc = cliente.mejoresClientes();
			System.out.println("Mejores clientes --------------------------");
			for(Cliente row: mc) {
				System.out.println("Cantidad: " + row.getCantidad() + ", ID: " + row.getID() + ", Nombre: " + row.getNombre() + ", Email: " + row.getEmail());
			}
			System.out.println("Fin mejores clientes --------------------------");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		try {
			Producto mayorRecaudacion = producto.mayorRecaudacion();
			System.out.println("Mayor recaudacion -------------------------------------");
			System.out.println("ID: " + mayorRecaudacion.getIdProducto()+ ", Nombre: " + mayorRecaudacion.getNombre() + ", Valor: " + mayorRecaudacion.getValor());
			System.out.println("Fin mayor recaudacion -------------------------------------");
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}

