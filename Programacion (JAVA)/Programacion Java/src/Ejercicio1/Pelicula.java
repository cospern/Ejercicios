package Ejercicio1;

import java.time.LocalDate;

public class Pelicula extends DVD {

	String director;
	String genero;

	public Pelicula() {

	}
	
	public Pelicula(String codigo,String titulo,String director, String genero,int duracion) throws Exception {
		super(codigo,titulo,duracion);
		this.director = director;
		this.genero = genero;
	}

	public String getDirector() {
		return director;
	}

	public void setDirector(String director) {
		this.director = director;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	@Override
	public String toString() {
		return "Pelicula [Código = " +codigo+ ", título " + titulo + ", director=" + director + ", genero=" + genero + "]";
	}
	//Método para guardar la información en los ficheros con el formato indicado
	@Override
	public String toStringFichero() {
		String fechaAlquilerGuardar = fechaAlquiler != null ? fechaAlquiler.toString() : "0";
		String fechaDevolucionGuardar = fechaDevolucion != null ? fechaDevolucion.toString() : "0";
		return codigo + "," +titulo+ "," +duracion+ "," +director+ "," +genero+ "," +alquilado+ "," +fechaAlquilerGuardar+ "," +fechaDevolucionGuardar;
	}
	
	//Método para establecer el tiempo de préstamo
	public boolean alquilar() {
		if(this.getAlquilado()==false) {			
			this.setFechaAlquiler(LocalDate.now());
			this.setAlquilado(true);
			this.setFechaDevolucion(this.getFechaAlquiler().plusDays(3));
			return getAlquilado();
		}
		return false;
	}

}
