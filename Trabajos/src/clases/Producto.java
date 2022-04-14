package clases;

public class Producto {
	
	private int idProducto;
	
	private String nombre;
	
	private float valor;
	
	public Producto(int idProducto, String nombre, float valor) {
		this.setIdProducto(idProducto);
		this.setNombre(nombre);
		this.setValor(valor);		
	}

	public float getValor() {
		return valor;
	}

	public void setValor(float valor) {
		this.valor = valor;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getIdProducto() {
		return idProducto;
	}

	public void setIdProducto(int idProducto) {
		this.idProducto = idProducto;
	}
	
	@Override
	public String toString() {
		return this.idProducto + ", " + this.nombre + ", " + this.valor; 
	}
}
