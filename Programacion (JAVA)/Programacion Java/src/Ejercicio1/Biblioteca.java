package Ejercicio1;

import java.io.*;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.*;

public class Biblioteca {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		// Creo un Array de objetos de tipo alquilable. Esto me permite guardar todos
		// los productos en el mismo array.
		ArrayList<Alquilable> stock = new ArrayList<Alquilable>();

		// Accedemos a la ruta de los ficheros de sistema mediante las siguientes
		// funciones:
		String rutaDiscos = System.getProperty("user.dir") + "/Discos.txt";
		String rutaLibros = System.getProperty("user.dir") + "/Libros.txt";
		String rutaPeliculas = System.getProperty("user.dir") + "/Peliculas.txt";

		// Accedo a los m�todos desde el main para rellenar el ArrayList con todos los
		// productos alquilables.
		
		try {
			cargarPeliculas(rutaPeliculas, stock);
			cargarDiscos(rutaDiscos, stock);
			cargarLibros(rutaLibros, stock);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// Accedemos al m�todo para mostrar el men�
		mostrarMenu(stock);

	}

	// Leemos los archivos de texto. Para ello, creo m�todos para acceder a cada uno
	// de los archivos. Para poder ejecutar una expeci�n en caso de c�digo erroneo, varios de los atributos los he pasado con el constructor predefinido. Otros llamando al los setters.
	// Dentro de cada m�todo, a�adimos al array list la lista de productos
	// alquilables.
	private static void cargarDiscos(String rutaDiscos, ArrayList<Alquilable> stock) throws Exception {

		try {
			FileReader lectorDiscos = new FileReader(rutaDiscos);
			BufferedReader bfLectorDiscos = new BufferedReader(lectorDiscos);

			String linea = "";

			while (linea != null) {
				linea = bfLectorDiscos.readLine();				
				if (linea != null) {
					String[] matriz = linea.split(",");
					String codigo = matriz[0];
					String titulo = matriz[1];
					int duracion = Integer.parseInt(matriz[2]);
					DVDMusica disco = new DVDMusica(codigo,titulo,duracion);
					disco.setGrupo(matriz[3]);
					disco.setAlquilado((Boolean.parseBoolean(matriz[4])));
					try {
					disco.setFechaAlquiler(LocalDate.parse(matriz[5]));
					disco.setFechaDevolucion(LocalDate.parse(matriz[6]));
					} catch(DateTimeException e) {
					}
					stock.add(disco);
				}

			}

			lectorDiscos.close();
			bfLectorDiscos.close();

		} catch (FileNotFoundException e) {
			System.out.println("El fichero no est� disponible");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static void cargarLibros(String rutaLibros, ArrayList<Alquilable> stock) throws Exception {

		try {
			FileReader lectorLibros = new FileReader(rutaLibros);
			BufferedReader bfLectorLibros = new BufferedReader(lectorLibros);

			String linea = "";

			while (linea != null) {
				try {
					linea = bfLectorLibros.readLine();

				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if (linea != null) {
					String[] matriz = linea.split(",");
					String codigo = matriz[0];
					String titulo = matriz[1];
					String autor = matriz[2];
					int numPaginas = Integer.parseInt(matriz[3]);
					Libro libro = new Libro(codigo,titulo,autor,numPaginas);					
					libro.setAlquilado(Boolean.parseBoolean(matriz[4]));
					try {
						libro.setFechaAlquiler(LocalDate.parse(matriz[5]));
						libro.setFechaDevolucion(LocalDate.parse(matriz[6]));	
					} catch(DateTimeException e) {
					}
					stock.add(libro);
					
				}

			}
			lectorLibros.close();
			bfLectorLibros.close();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("El fichero no est� disponible");
		}
	}

	private static void cargarPeliculas(String rutaPeliculas, ArrayList<Alquilable> stock) throws Exception {
		try {
			FileReader lectorPeliculas = new FileReader(rutaPeliculas);
			BufferedReader bfLectorPeliculas = new BufferedReader(lectorPeliculas);

			String linea = "";

			while (linea != null) {
				try {
					linea = bfLectorPeliculas.readLine();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if (linea != null) {
					String[] matriz = linea.split(",");
					String codigo = matriz[0];
					String titulo = matriz[1];
					int duracion = Integer.parseInt(matriz[2]);
					String director = matriz[3];
					String genero = matriz[4];				    
					Pelicula pelicula = new Pelicula(codigo,titulo,director,genero,duracion);																	
					pelicula.setAlquilado(Boolean.parseBoolean(matriz[5]));
					try {
					pelicula.setFechaAlquiler(LocalDate.parse(matriz[6]));
					pelicula.setFechaDevolucion(LocalDate.parse(matriz[7]));
					} catch(DateTimeException e) {						
					}
					stock.add(pelicula);
					
				}

			}
			lectorPeliculas.close();
			bfLectorPeliculas.close();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("El fichero no est� disponible");
		}

	}

	private static void guardarLibros(ArrayList<Alquilable> stock) {
		String rutaLibro = System.getProperty("user.dir") + "/Libros.txt";
		FileWriter escritor;
		try {
			escritor = new FileWriter(rutaLibro);
			BufferedWriter bufferEscritor = new BufferedWriter(escritor);
			for (Alquilable l : stock) {
				if (l instanceof Libro) {
				bufferEscritor.write(((Libro) l).toStringFichero());
				bufferEscritor.newLine();
				}
			}
			bufferEscritor.close();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	private static void guardarDiscos(ArrayList<Alquilable> stock) {
		String rutaDisco = System.getProperty("user.dir") + "/Discos.txt";
		FileWriter escritor;
		try {
			escritor = new FileWriter(rutaDisco);
			BufferedWriter bufferEscritor = new BufferedWriter(escritor);
			for (Alquilable l : stock) {
				if (l instanceof DVDMusica) {
				bufferEscritor.write(((DVDMusica) l).toStringFichero());
				bufferEscritor.newLine();
				}
			}
			bufferEscritor.close();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	private static void guardarPeliculas(ArrayList<Alquilable> stock) {
		String rutaPelicula = System.getProperty("user.dir") + "/Peliculas.txt";
		FileWriter escritor;
		try {
			escritor = new FileWriter(rutaPelicula);
			BufferedWriter bufferEscritor = new BufferedWriter(escritor);
			for (Alquilable l : stock) {
				if (l instanceof Pelicula) {
				bufferEscritor.write(((Pelicula) l).toStringFichero());
				bufferEscritor.newLine();
				}
			}
			bufferEscritor.close();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	// Creamos el men�
	private static void mostrarMenu(ArrayList<Alquilable> stock) {
		int opcion;
		do {

			Scanner lector = new Scanner(System.in);

			System.out.println("1. ALquilar un libro");
			System.out.println("2. Alquilar una pel�cula");
			System.out.println("3. ALquilar unh DVD de m�sica");
			System.out.println("4. Devolver un producto");
			System.out.println("5. Ver todos los productos disponibles");
			System.out.println("6. Guardar la informaci�n en ficheros");
			System.out.println("10. Teclee 10 para salir");

			String codigo;
			opcion = lector.nextInt();

			switch (opcion) {

			case 1:

				for (Alquilable l : stock) {
					if (l != null && l instanceof Libro) {
						Libro lb = (Libro) l;
						if (lb.alquilado == false) {
							System.out.println(lb);
						}
					}
				}

				System.out.println("Introduzca el c�digo del libro que desea.");
				System.out.println();
				codigo = lector.next();

				for (Alquilable l : stock) {
					if (l != null && l instanceof Libro) {
						Libro lb = (Libro) l;
						if (lb.getCodigo().equals(codigo.toUpperCase())) {
							boolean alquilado = lb.alquilar();
							if (alquilado) {
								System.out.println("Perfecto, has alquilado el libro " + lb.getTitulo());
							} else {
								System.out.println("Lo sentimos, este libro ya esta alquilado");
							}
						}
					}

				}

				break;

			case 2:
				for (Alquilable l : stock) {
					if (l != null && l instanceof Pelicula) {
						Pelicula pl = (Pelicula) l;
						if (pl.alquilado == false) {
							System.out.println(pl);
						}
					}
				}

				System.out.println("Introduzca el c�digo de la pel�cula que desea.");
				System.out.println();
				codigo = lector.next();

				for (Alquilable l : stock) {
					if (l != null && l instanceof Pelicula) {
						Pelicula pl = (Pelicula) l;
						if (pl.getCodigo().equals(codigo.toUpperCase())) {
							boolean alquilado = pl.alquilar();
							if (alquilado) {
								System.out.println("Perfecto, has alquilado la pel�cula " + pl.getTitulo());
							} else {
								System.out.println("Lo sentimos, esta pel�cula ya est� alquilado");
							}
						}
					}

				}

				break;

			case 3:
				for (Alquilable l : stock) {
					if (l != null && l instanceof DVDMusica) {
						DVDMusica dm = (DVDMusica) l;
						if (dm.alquilado == false) {
							System.out.println(dm);
						}
					}
				}

				System.out.println("Introduzca el c�digo del DVD de m�sica que desea.");
				System.out.println();
				codigo = lector.next();

				for (Alquilable l : stock) {
					if (l != null && l instanceof DVDMusica) {
						DVDMusica dm = (DVDMusica) l;
						if (dm.getCodigo().equals(codigo.toUpperCase())) {
							boolean alquilado = dm.alquilar();
							if (alquilado) {
								System.out.println("Perfecto, has alquilado el CD " + dm.getTitulo());
							} else {
								System.out.println("Lo sentimos, este CD ya est� alquilado");
							}
						}
					}

				}
				break;

			case 4:
				System.out.println("Introduzca el c�digo del producto a devolver: ");
				
				for (Alquilable l : stock) {				
					if (l != null && ((Producto)l).getAlquilado()) {
						System.out.println(l);
					}
				}
				codigo = lector.next();
				boolean encontrado = false;
				for (Alquilable l : stock) {
					if (((Producto) l).getCodigo().equals(codigo.toUpperCase())) {
						encontrado = true;
						l.devolver();
						System.out.println("Cambios guardados");
						break;
					} 
				}
				if (encontrado == false) {
					System.out.println("C�digo no encontrado");
				}
					

				break;

			case 5:

				for (Alquilable l : stock) {
					if (l != null && ((Producto)l).getAlquilado() == false) {
						System.out.println(l);
					}
				}
				break;

			case 6:
				
				guardarLibros(stock);
				guardarDiscos(stock);
				guardarPeliculas(stock);

			}

		} while (opcion != 10);
		System.out.println("Hasta luego");
	}

}
