package Ejercicio1;

public abstract class DVD extends Producto {

	int duracion;

	public DVD() {

	}
	
	public DVD(String codigo, String titulo,int duracion) throws Exception {
		super(codigo,titulo);
		this.duracion = duracion;
	}
	//Getters y Setters
	public DVD(int duracion) {
		super();
		this.duracion = duracion;
	}

	public int getDuracion() {
		return duracion;
	}

	public void setDuracion(int duracion) {
		this.duracion = duracion;
	}

	@Override
	public String toString() {
		return "DVD [duracion=" + duracion + "]";
	}

	@Override
	public String toStringFichero() {
		return toString();

	}

}
