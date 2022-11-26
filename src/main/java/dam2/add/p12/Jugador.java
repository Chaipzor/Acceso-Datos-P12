package dam2.add.p12;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Jugador {
	private String nombre;
	private String apellido1;
	private String apellido2;
	private int puntos;
	

	public Jugador(String nombre, String apellido1, String apellido2, int puntos) {
		super();
		this.nombre = nombre;
		this.apellido1 = apellido1;
		this.apellido2 = apellido2;
		this.puntos = puntos;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido1() {
		return apellido1;
	}

	public void setApellido1(String apellido1) {
		this.apellido1 = apellido1;
	}

	public String getApellido2() {
		return apellido2;
	}

	public void setApellido2(String apellido2) {
		this.apellido2 = apellido2;
	}

	public int getPuntos() {
		return puntos;
	}

	public void setPuntos(int puntos) {
		this.puntos = puntos;
	}

	//Guardamos los datos del jugador una vez terminada la partida.
	public static void grabarDatos(int puntuacion) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Introduce tu nombre: ");
		String nombre = sc.nextLine();
		System.out.println("Introduce tu primer apellido: ");
		String apellido1 = sc.nextLine();
		System.out.println("Introduce tu segundo apellido: ");
		String apellido2 = sc.nextLine();
		Jugador nuevoJugador = new Jugador(nombre, apellido1, apellido2, puntuacion);
		int contador = 0;		
		int puntuacionMayor = -1;
		
		ArrayList<Jugador> listaJugadores = leerFichero();

		//Si la lista está vacía se añade directamente al nuevo jugador.
		if(listaJugadores.size()==0) {
			listaJugadores.add(nuevoJugador);
			contador++;
		//Si ya estaba en la lista el mismo nombre y apellidos, se actualizan los puntos (si ha mejorado el resultado)
		} else {
			for (Jugador jugador : listaJugadores) {
				if(jugador.getNombre().equalsIgnoreCase(nuevoJugador.getNombre()) && 
						jugador.getApellido1().equalsIgnoreCase(nuevoJugador.getApellido1()) && 
						jugador.getApellido2().equalsIgnoreCase(nuevoJugador.getApellido2())) 
				{
					if(nuevoJugador.getPuntos() > jugador.getPuntos()) {
						jugador.setPuntos(nuevoJugador.getPuntos());
					}
					contador++;
				}
			}
			
		//Se añade el nuevo jugador según su puntuación
		} if (contador == 0) {

			for (int i = 0; i<listaJugadores.size(); i++) {
				if(listaJugadores.get(i).getPuntos()<nuevoJugador.getPuntos() && puntuacionMayor == -1) {
					puntuacionMayor = i;
				}
			}
			//Si es el de menor puntuación se añade el último.
			if (puntuacionMayor== -1) {
				listaJugadores.add(nuevoJugador);
			} else {
				listaJugadores.add(puntuacionMayor, nuevoJugador);
			}
		}
		String separador = File.separator;
		File f = new File("." + separador + "ficheros" + separador + "records.txt");

		try {
			FileWriter fw = new FileWriter(f);
			BufferedWriter bw = new BufferedWriter(fw);
			for (Jugador jugadores : listaJugadores) {
				// Escribimos los datos en el nuevo archivo
				bw.write(jugadores.getNombre() + ":" + jugadores.getApellido1() + ":" 
						+ jugadores.getApellido2() + ":" + jugadores.getPuntos());
				bw.newLine();
			}

			bw.close();
		} catch (FileNotFoundException e) {
			System.out.println("Error al abrir archivo. Grabar");
		} catch (IOException e) {
			System.out.println("Error E/S.");
		}
	}	

	//Se recuperan los datos del archivo
	public static ArrayList<Jugador> leerFichero() {
		String separador = File.separator;
		File f = new File("." + separador + "ficheros" + separador + "records.txt");
		String datos;
		ArrayList<Jugador> listaJugadores = new ArrayList<Jugador>();

		try {
			if (f.exists()) {
				FileReader fr = new FileReader(f);
				BufferedReader br = new BufferedReader(fr);
				datos = br.readLine();

				while (datos != null) {
					if(!(datos.length()<4)){

						String[] partes = datos.split(":");
						String nombre = partes[0];
						String apellido1 = partes[1];
						String apellido2 = partes[2];
						int puntuacion = Integer.parseInt(partes[3]);

						Jugador jugador = new Jugador(nombre, apellido1, apellido2, puntuacion);

						listaJugadores.add(jugador);
					}
					datos = br.readLine();
				}
				br.close();
			}
		} catch (FileNotFoundException e) {
			System.out.println("Error al abrir archivo. Leer");
		} catch (IOException e) {
			System.out.println("Error E/S.");
		}
		return listaJugadores;
	}
	
	// Se muestran los datos de records por pantalla.
	public static void mostrarRecords() {
		ArrayList<Jugador> listaJugadores = leerFichero();
		int i = 0;
		System.out.println("CLASIFICACIÓN GENERAL: ");
		System.out.println("Pos. \t Record \t Nombre");
		for (Jugador jugador : listaJugadores) {
			i++;
			String nombre = jugador.getNombre().substring(0, 3);
			
			System.out.println(i + ". \t" + jugador.getPuntos() + " Pts. \t\t" + nombre);
			
		}
	}
}
