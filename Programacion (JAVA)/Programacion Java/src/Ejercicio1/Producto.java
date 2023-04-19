package Ejercicio1;

import java.time.LocalDate;
import java.time.LocalTime;

public class Producto implements Alquilable {

	String codigo;
	String titulo;
	boolean alquilado;
	int diasPrestamo;
	LocalDate fechaAlquiler;
	LocalDate fechaDevolucion;
//	LocalTime horaPrestamo;
//	LocalTime horaDevolucion;

	public Producto() {

	}
	//Configuramos el segundo constructor para que salte una excepción si el código de los productos del fichero no cumple con el formato indicado.
	public Producto(String codigo, String titulo) throws Exception {	
		super();		
		if(Character.isLetter(codigo.charAt(0)) && Character.isLetter(codigo.charAt(1)) && Character.isDigit(codigo.charAt(2)) && Character.isDigit(codigo.charAt(3)) && codigo.length()==4) { 
			this.codigo = codigo;
			this.titulo = titulo;
		} else {
			throw new Exception("Code is not matching");
		}
	}
	//Setters y getters
	public LocalDate getFechaAlquiler() {
		return fechaAlquiler;
	}

	public void setFechaAlquiler(LocalDate fechaAlquiler) {
		this.fechaAlquiler = fechaAlquiler;
	}

	public LocalDate getFechaDevolucion() {
		return fechaDevolucion;
	}

	public void setFechaDevolucion(LocalDate fechaDevolucion) {
		this.fechaDevolucion = fechaDevolucion;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public boolean getAlquilado() {
		return alquilado;
	}

	public void setAlquilado(boolean alquilado) {
		this.alquilado = alquilado;
	}

	public int getDiasPrestamo() {
		return diasPrestamo;
	}

	public void setDiasPrestamo(int diasPrestamo) {
		this.diasPrestamo = diasPrestamo;
	}


	public String toStringFichero() {
		return toString();
	}
    
	//Metodos
	
	@Override
	public String toString() {
		return "Producto []";
	}

	@Override
	public void devolver() {
		alquilado = false;
		fechaDevolucion = null;
		fechaAlquiler = null;
	}

	@Override
	public boolean alquilar() {

		return false;
	}


}
