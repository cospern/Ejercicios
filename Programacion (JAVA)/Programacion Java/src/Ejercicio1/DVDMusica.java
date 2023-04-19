package Ejercicio1;

import java.time.LocalDate;

public class DVDMusica extends DVD {

	String grupo;

	public DVDMusica() {

	}


	public DVDMusica (String codigo,String titulo,int duracion) throws Exception {
		super(codigo,titulo,duracion);
	}
	
	public String getGrupo() {
		return grupo;
	}
	

	public void setGrupo(String grupo) {
		this.grupo = grupo;
	}

	@Override
	public String toString() {
		return "DVDMusica [codigo=" + codigo + ", titulo=" + titulo + ", duracion=" + duracion + ", grupo=" + grupo
				+ "]";
	}
	//M�todo para guardar la informaci�n en los ficheros con el formato indicado
	@Override
	public String toStringFichero() {
		String fechaAlquilerGuardar = fechaAlquiler != null ? fechaAlquiler.toString() : "0";
		String fechaDevolucionGuardar = fechaDevolucion != null ? fechaDevolucion.toString() : "0";
		return codigo+ "," +titulo+ "," +duracion+ "," +grupo+ "," +alquilado+ "," +fechaAlquilerGuardar+ "," +fechaDevolucionGuardar;
	}
	//M�todo para establecer el tiempo de pr�stamo
	public boolean alquilar() {
		if(this.getAlquilado()==false) {			
			this.setFechaAlquiler(LocalDate.now());
			this.setAlquilado(true);
			this.setFechaDevolucion(this.getFechaAlquiler().plusDays(5));
			return getAlquilado();
		}
		return false;
	}

}
