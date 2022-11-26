package dam2.add.p12;

import java.util.ArrayList;
import java.util.Scanner;

public class MainP12 
{
    public static void main( String[] args )
    {
    	Scanner sc = new Scanner(System.in);
    	String opc;
    	String opcJugador;
    	String opcAdmin;
    	ArrayList<Integer> respuestas = new ArrayList<Integer>();
    	do {
    		opcAdmin = menuInicial();
    		// Acceso como jugador
    		if(opcAdmin.equals("1")) {
    			do {
    	    		opcJugador = menuJugador();
    	    		//Opción jugar
    	    		if(opcJugador.equals("1")) {
    	    			respuestas = Pregunta.jugar();
    	    			System.out.println("¿Imprimir detalle pdf? (s/n)");
    	    			String imprimir = sc.nextLine();
    	    			if (imprimir.equalsIgnoreCase("s")) {
    	    				PDF.Imprimir(respuestas);
    	    			}
    	    			//Opción mostrar por pantalla records
    	    		} else if (opcJugador.contentEquals("2")) {
    	    			Jugador.mostrarRecords();
    	    			//Opción mostrar por pantalla instrucciones
    	    		} else if (opcJugador.contentEquals("3")) {
    	    			instrucciones();
    	    		} else {
    	    			System.out.println("Introduce una opción del 0 al 3.");
    	    		}
    	    	}
    	    	while(!opcJugador.equals("0")); 
    			// Acceso como administrador
    		} else if(opcAdmin.equals("2")) {
    			do {
    	    		opc = menuAdmin();
    	    		//Opción jugar
    	    		if(opc.equals("1")) {
    	    			respuestas = Pregunta.jugar();
    	    			System.out.println("¿Imprimir detalle pdf? (s/n)");
    	    			String imprimir = sc.nextLine();
    	    			if (imprimir.equalsIgnoreCase("s")) {
    	    				PDF.Imprimir(respuestas);
    	    			}
    	    		
    	    		//Opción añadir a mano	
    	    		} else if (opc.contentEquals("2")) {
    	    			Pregunta.anyadirLibre();
    	    			
    	    		//Opción importar desde excel
    	    		} else if (opc.contentEquals("3")) {
    	    			Pregunta.importarExcel();
    	    			
    	    		//Opción mostrar por pantalla records
    	    		} else if(opc.contentEquals("4")) {
    	    			Jugador.mostrarRecords();
    	    			
    	    		//Opción mostrar por pantalla instrucciones
    	    		} else if(opc.contentEquals("5")) {
    	    			instrucciones();
    	    		} else {
    	    			System.out.println("Introduce una opción del 0 al 5.");
    	    		}
    	    	}
    	    	while(!opc.equals("0")); 
    		}
    	}while(!opcAdmin.equals("0"));
    }

	private static String menuInicial() {
		Scanner sc = new Scanner(System.in);
		System.out.println("\nLOG-IN");
		System.out.println("1 - Jugador ");
		System.out.println("2 - Administrador ");
		System.out.println("0 - Salir ");
		System.out.println("Introducir opción: ");
		String opcion = sc.nextLine();
		String pass;
		String claveAdministrador = "admin";
		if (opcion.equals("2")) {
			System.out.println("Introduce la contraseña de administrador: ");
			pass = sc.nextLine();
			if(pass.equals(claveAdministrador)) {
				return opcion;
			}else {
				System.out.println("Constraseña incorrecta.");
				return "-1";
			}
		}		
		return opcion;
	}
    
	private static String menuAdmin() {
		Scanner sc = new Scanner(System.in);
		System.out.println("\nMENÚ");
		System.out.println("1 - Jugar ");
		System.out.println("2 - Añadir pregunta ");
		System.out.println("3 - Importar preguntas ");
		System.out.println("4 - Ver records ");
		System.out.println("5 - Instrucciones ");
		System.out.println("0 - Salir ");
		System.out.println("Introducir opción: ");
		return sc.nextLine();
	}
	
	private static String menuJugador() {
		Scanner sc = new Scanner(System.in);
		System.out.println("\nMENÚ");
		System.out.println("1 - Jugar ");
		System.out.println("2 - Ver records ");
		System.out.println("3 - Instrucciones ");
		System.out.println("0 - Salir ");
		System.out.println("Introducir opción: ");
		return sc.nextLine();
	}
	
	private static void instrucciones() {
		System.out.println("\nEl juego consiste en acertar preguntas de 3 opciones.");
		System.out.println("Se debe responder con un número del 1 al 3 cada pregunta.");
		System.out.println("Cada pregunta acertada sumará un punto.");
	}
}
