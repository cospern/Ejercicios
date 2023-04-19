package Ejercicio1;

import java.time.LocalDate;

public class Libro extends Producto {

	String autor;
	int numPaginas;
    
	//constructores
	public Libro() {

	}
	
	public Libro(String codigo,String titulo,String autor, int numPaginas) throws Exception {
		super(codigo,titulo);
		this.autor = autor;
		this.numPaginas = numPaginas;
		
	}	
	
	//Setters y getters
	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

	public int getNumPaginas() {
		return numPaginas;
	}

	public void setNumPaginas(int numPaginas) {
		this.numPaginas = numPaginas;
	}

	@Override
	public String toString() {
		return "Libro [codigo=" + codigo + ", titulo=" + titulo + ", autor=" + autor + ", numPaginas=" + numPaginas + ", fechaAlquiler=" + fechaAlquiler
				+ ", fechaDevolucion=" + fechaDevolucion + ", alquilado=" + alquilado +"]";
	}
	
	//Método para establecer el tiempo de préstamo
	public boolean alquilar() {
		if (this.getAlquilado() == true) {
			return false;
		}

		else {
			this.setAlquilado(true);
			this.setFechaAlquiler(LocalDate.now());
			this.setFechaDevolucion(this.getFechaAlquiler().plusDays(10));
			return true;
		}
	}
	//Método para guardar la información en los ficheros con el formato indicado
	@Override
	public String toStringFichero() {
		String fechaAlquilerGuardar = fechaAlquiler != null ? fechaAlquiler.toString() : "0";
		String fechaDevolucionGuardar = fechaDevolucion != null ? fechaDevolucion.toString() : "0";
		return codigo + "," +titulo + "," +autor+ "," + numPaginas + "," +alquilado+ "," +fechaAlquilerGuardar+ "," +fechaDevolucionGuardar;

	}

}
