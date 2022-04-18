package clases;

public class Factura_producto {
	
	private int idFactura;
	private int idProducto;
	private int cantidad;
	
	public Factura_producto(int idFactura, int idProducto, int cantidad) {
		this.idFactura = idFactura;
		this.idProducto = idProducto;
		this.cantidad = cantidad;
	}
	
	public int getIdFactura() {
		return this.idFactura;
	}
	
	public int setIdFactura(int idFactura) {
	 this.idFactura = idFactura;
	}
	
	public int getIdProducto() {
		return this.idProducto;
	}
	
	public int setIdProducto(int idProducto) {
	 this.idProducto = idProducto;
	}
	
	public int getCantidad() {
		return this.idCantidad;
	}
	
	public int setCantidad(int Cantidad) {
	 this.cantidad = cantidad;
	}
	
	@Override
	public String toString() {
		return this.idFactura + ", " this.idProducto + ", " + this.cantidad; 
	}
}
