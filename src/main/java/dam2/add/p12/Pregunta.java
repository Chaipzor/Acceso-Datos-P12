package dam2.add.p12;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;
import jxl.Sheet;
import jxl.Workbook;


public class Pregunta {

	public static ArrayList<Integer> jugar() {
		Scanner sc = new Scanner(System.in);
		int puntuacion = 0;
		List<Element> lista_preguntas = leer();			
		ArrayList<Integer> respuestas = new ArrayList<Integer>(); 
		
		//Se recorre la lista de hijos del nodo
		for (int i=0; i<lista_preguntas.size(); i++) {
			//Se obtiene el elemento 'enunciado'
			Element pregunta = (Element) lista_preguntas.get(i);

			Element elemento_enunciado = pregunta.getChild("texto");
			String enunciado = elemento_enunciado.getText();

			//Se obtiene el valor que esta entre los tags '<respuesta1></respuesta1>'
			String respuesta1 = pregunta.getChildText("respuesta1");

			//Se obtiene el valor que esta entre los tags '<respuesta2></respuesta2>'
			String respuesta2 = pregunta.getChildText("respuesta2");

			//Se obtiene el valor que esta entre los tags '<respuesta3></respuesta3>'
			String respuesta3 = pregunta.getChildText("respuesta3");

			//Se obtiene el valor que esta entre los tags '<correcta></correcta>'
			int solucion = Integer.parseInt(pregunta.getChildText("correcta"));

			System.out.println(enunciado);
			System.out.println("1) " + respuesta1);
			System.out.println("2) " + respuesta2);
			System.out.println("3) " + respuesta3);
			System.out.println("Introduce la respuesta: ");
			int respuesta = sc.nextInt();
			respuestas.add(respuesta);
			if(solucion == respuesta) {
				System.out.println("Respuesta correcta!");
				puntuacion++;
			} else {
				System.out.println("Incorrecto, la respuesta correcta era la " + solucion);
			}
		}
		Jugador.grabarDatos(puntuacion);
		return respuestas;
	}

	public static List<Element> leer() {
		//Se crea un SAXBuilder para poder parsear el archivo
		SAXBuilder builder = new SAXBuilder();

		File xmlFile = new File("./ficheros/preguntas.xml");
		List<Element> lista_preguntas = null;
		try {
			//Se crea el documento a traves del archivo
			Document document = builder.build(xmlFile);

			//Se obtiene la raiz 'ciudades'
			Element nodoRaiz = document.getRootElement();

			//Se obtiene el nombre del nodo raiz
			String nombreNodo = nodoRaiz.getName();

			//Se obtiene la lista de hijos del nodo raiz
			lista_preguntas = nodoRaiz.getChildren("pregunta");
			return lista_preguntas;
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return lista_preguntas;
	}

	public static void anyadirLibre() {
		Scanner sc = new Scanner(System.in);
		String docNuevoStr = "";
		List<Element> lista_preguntas = leer();		
		try {
			//Se crea un nuevo documento
			Document newDocument = new Document();
			Element newNodoPregunta = null;
			//Se obtiene la raiz 'juego'
			Element nodoPrueba = lista_preguntas.get(0);
			Element nodoRaiz = nodoPrueba.getParentElement();

			//Se obtiene el nombre del nodo raiz
			String nombreNodo = nodoRaiz.getName();

			//Escribimos el nombre raiz 'juego'
			Element newNodoRaiz = new Element(nombreNodo);
			newDocument.addContent(newNodoRaiz);

			//Se copia el archivo previo en el nuevo
			for(int i = 0 ; i<lista_preguntas.size(); i++) {
				//Escribimos el nombre 'pregunta'
				newNodoPregunta = new Element("pregunta");
				newNodoRaiz.addContent(newNodoPregunta);
				Element nodoPregunta = (Element) lista_preguntas.get(i);

				Element elemento_enunciado = nodoPregunta.getChild("texto");
				String enunciado = elemento_enunciado.getText();
				Element nodoTexto = new Element("texto");
				newNodoPregunta.addContent(nodoTexto);
				nodoTexto.setText(enunciado);

				//Se obtiene el valor que esta entre los tags '<respuesta1></respuesta1>'
				String respuesta1 = nodoPregunta.getChildText("respuesta1");
				Element nodoRespuesta1 = new Element("respuesta1");
				newNodoPregunta.addContent(nodoRespuesta1);
				nodoRespuesta1.setText(respuesta1);

				//Se obtiene el valor que esta entre los tags '<respuesta2></respuesta2>'
				String respuesta2 = nodoPregunta.getChildText("respuesta2");
				Element nodoRespuesta2 = new Element("respuesta2");
				newNodoPregunta.addContent(nodoRespuesta2);
				nodoRespuesta2.setText(respuesta2);

				//Se obtiene el valor que esta entre los tags '<respuesta3></respuesta3>'
				String respuesta3 = nodoPregunta.getChildText("respuesta3");
				Element nodoRespuesta3 = new Element("respuesta3");
				newNodoPregunta.addContent(nodoRespuesta3);
				nodoRespuesta3.setText(respuesta3);

				//Se obtiene el valor que esta entre los tags '<correcta></correcta>
				String solucion = nodoPregunta.getChildText("correcta");
				Element nodoSolucion = new Element("correcta");
				newNodoPregunta.addContent(nodoSolucion);
				nodoSolucion.setText(solucion);

			}

			lista_preguntas = nodoRaiz.getChildren("pregunta");

			Element nodoEnunciado = new Element("texto");
			newNodoPregunta = new Element("pregunta");
			newNodoRaiz.addContent(newNodoPregunta);
			System.out.println("Introduce el enunciado de la pregunta: ");
			nodoEnunciado.setText(sc.nextLine());
			newNodoPregunta.addContent(nodoEnunciado);

			//Se introducen manualmente las nuevas preguntas y respuestas
			for(int i = 1; i<4; i++) {
				Element nodoRespuesta = new Element("respuesta" + i);
				System.out.println("Introduce la respuesta " + i + " : ");
				nodoRespuesta.setText(sc.nextLine());
				newNodoPregunta.addContent(nodoRespuesta);
			}

			Element nodoCorrecta = new Element("correcta");
			System.out.println("Introduce el número de la solución: ");
			nodoCorrecta.setText(sc.nextLine());
			newNodoPregunta.addContent(nodoCorrecta);

			// Se procesa y edita el documento y se guarda en un nuevo archivo
			XMLOutputter xmlOutputter = new XMLOutputter(Format.getPrettyFormat());
			docNuevoStr = xmlOutputter.outputString(newDocument);

		} catch (Exception e) {
			e.printStackTrace();
		} 

		FileWriter fichero = null;
		try {
			fichero = new FileWriter("./ficheros/preguntas.xml");
			PrintWriter pw = new PrintWriter(fichero);
			pw.println(docNuevoStr);
			fichero.close();
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("No existe el fichero");
		}
	}

	public static void importarExcel() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Introduce el nombre del archivo "
				+ "donde están almacenadas las preguntas a importar. "
				+ "(sin la extensión xls)");
		String nombre = sc.nextLine();
		File archivoLectura = new File("./ficheros/" + nombre + ".xls");
		String docNuevoStr = "";
		List<Element> lista_preguntas = leer();		

		try {
			Document newDocument = new Document();
			Element newNodoPregunta = null;
			//Se obtiene la raiz 'juego'
			Element nodoPrueba = lista_preguntas.get(0);
			Element nodoRaiz = nodoPrueba.getParentElement();

			//Se obtiene el nombre del nodo raiz
			String nombreNodo = nodoRaiz.getName();

			//Escribimos el nombre raiz 'juego'
			Element newNodoRaiz = new Element(nombreNodo);
			newDocument.addContent(newNodoRaiz);

			//Se copia el archivo previo en el nuevo
			for(int i = 0 ; i<lista_preguntas.size(); i++) {
				//Escribimos el nombre 'pregunta'
				newNodoPregunta = new Element("pregunta");
				newNodoRaiz.addContent(newNodoPregunta);
				Element nodoPregunta = (Element) lista_preguntas.get(i);

				Element elemento_enunciado = nodoPregunta.getChild("texto");
				String enunciado = elemento_enunciado.getText();
				Element nodoTexto = new Element("texto");
				newNodoPregunta.addContent(nodoTexto);
				nodoTexto.setText(enunciado);

				//Se obtiene el valor que esta entre los tags '<respuesta1></respuesta1>'
				String respuesta1 = nodoPregunta.getChildText("respuesta1");
				Element nodoRespuesta1 = new Element("respuesta1");
				newNodoPregunta.addContent(nodoRespuesta1);
				nodoRespuesta1.setText(respuesta1);

				//Se obtiene el valor que esta entre los tags '<respuesta2></respuesta2>'
				String respuesta2 = nodoPregunta.getChildText("respuesta2");
				Element nodoRespuesta2 = new Element("respuesta2");
				newNodoPregunta.addContent(nodoRespuesta2);
				nodoRespuesta2.setText(respuesta2);

				//Se obtiene el valor que esta entre los tags '<respuesta3></respuesta3>'
				String respuesta3 = nodoPregunta.getChildText("respuesta3");
				Element nodoRespuesta3 = new Element("respuesta3");
				newNodoPregunta.addContent(nodoRespuesta3);
				nodoRespuesta3.setText(respuesta3);

				//Se obtiene el valor que esta entre los tags '<correcta></correcta>
				String solucion = nodoPregunta.getChildText("correcta");
				Element nodoSolucion = new Element("correcta");
				newNodoPregunta.addContent(nodoSolucion);
				nodoSolucion.setText(solucion);

			}			
			
			lista_preguntas = nodoRaiz.getChildren("pregunta");

			Workbook w = Workbook.getWorkbook(archivoLectura);

			//Se lee la primera hoja del excel
			Sheet sheet = w.getSheet(0);

			//Se leen los datos del excel y se añaden en memoria
			for (int f=0; f<sheet.getRows(); f += 5) {
				Element nodoEnunciado = new Element("texto");
				newNodoPregunta = new Element("pregunta");
				newNodoRaiz.addContent(newNodoPregunta);
				
				nodoEnunciado.setText(sheet.getCell(0,f).getContents());
				newNodoPregunta.addContent(nodoEnunciado);
				for(int i = 1; i<4; i++) {
					Element nodoRespuesta = new Element("respuesta" + i);
					nodoRespuesta.setText(sheet.getCell(0,f+i).getContents());
					newNodoPregunta.addContent(nodoRespuesta);
				}
				Element nodoCorrecta = new Element("correcta");
				nodoCorrecta.setText(sheet.getCell(0,f+4).getContents());
				newNodoPregunta.addContent(nodoCorrecta);
			}
			// Se procesa y edita el documento y se guarda en un nuevo archivo
			XMLOutputter xmlOutputter = new XMLOutputter(Format.getPrettyFormat());
			docNuevoStr = xmlOutputter.outputString(newDocument);
		} catch (Exception e) {
			e.printStackTrace();
		}

		FileWriter archivoEscritura = null;
		try {
			archivoEscritura = new FileWriter("./ficheros/preguntas.xml");
			PrintWriter pw = new PrintWriter(archivoEscritura);
			pw.println(docNuevoStr);
			archivoEscritura.close();

		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("No existe el fichero");
		}
	}
	
}