package integrador_1;

public class Cliente {
	
	private int idCliente;
	
	private String nombre;
	
	private String email;
	
	private int cantidad;

	public Cliente(int idCliente,String nombre,String email,int cantidad) {
		this.idCliente = idCliente;
		this.nombre = nombre;
		this.email = email;
		this.cantidad = cantidad;
	}
	
	public String getNombre(){
		return this.nombre;
	}
	
	public String getEmail(){
		return this.email;
	}
	
	public int getID(){
		return this.idCliente;
	}
	
	@Override
	public String toString() {
		return this.idCliente + ", " + this.nombre + ", " + this.email + ", " + this.cantidad; 
	}
	

}
