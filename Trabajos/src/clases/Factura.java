package clases;

public class Factura {
	
	private int idFactura;
	private int idCliente;
	
	public Factura(int idFactura, int idCliente) {
		this.idFactura = idFactura;
		this.idCliente = idCliente;
	}
	
	public int getIdFactura() {
		return this.idFactura;
	}
	
	public int setIdFactura(int idFactura) {
	 this.idFactura = idFactura;
	}
	
	public int getIdCliente() {
		return this.idCliente;
	}

	public int setIdCliente(int idCliente) {
		 this.idCliente = idCliente;
		}
	
	@Override
	public String toString() {
		return this.idFactura + ", " + this.idCliente;
	}
}
