package dam2.add.p12;

import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

public class PDF {

	public static void Imprimir(ArrayList<Integer> respuestas) {
		PdfWriter writer = null;
		Document documento = new Document(PageSize.A4, 20, 20, 70, 50);
		Integer puntuacion = 0;
		float spacBefore = 3;

		List<org.jdom2.Element> lista_preguntas = Pregunta.leer();	

		try {

			//Obtenemos la instancia del archivo a utilizar
			writer = PdfWriter.getInstance(documento, new FileOutputStream("./ficheros/partida.pdf"));

			//Abrimos el documento para edici�n
			documento.open();

			for (int i=0; i<lista_preguntas.size(); i++) {
				//Se obtiene el elemento 'enunciado'
				org.jdom2.Element pregunta = (org.jdom2.Element) lista_preguntas.get(i);

				org.jdom2.Element elemento_enunciado = pregunta.getChild("texto");
				Font bold = new Font(Font.FontFamily.HELVETICA, 9, Font.BOLD);
				Paragraph paragraph = new Paragraph();
				paragraph.setSpacingBefore(spacBefore);
				paragraph.setAlignment(Element.ALIGN_CENTER);
				String enunciado = elemento_enunciado.getText();

				//Se obtiene el valor que esta entre los tags '<respuesta1></respuesta1>'
				String respuesta1 = pregunta.getChildText("respuesta1");

				//Se obtiene el valor que esta entre los tags '<respuesta2></respuesta2>'
				String respuesta2 = pregunta.getChildText("respuesta2");

				//Se obtiene el valor que esta entre los tags '<respuesta3></respuesta3>'
				String respuesta3 = pregunta.getChildText("respuesta3");

				//Se obtiene el valor que esta entre los tags '<correcta></correcta>'
				String solucion = pregunta.getChildText("correcta");				
				
				//Se añaden el enunciado y las preguntas, aquella que es correcta, en negrita.
				paragraph.add(enunciado + "\n");
				documento.add(paragraph);
				if(solucion.equals("1")) {
					paragraph = new Paragraph("1. " + respuesta1+ "\n",bold);
					documento.add(paragraph);
				} else {
					paragraph = new Paragraph("1. " + respuesta1+ "\n");
					documento.add(paragraph);
				} if(solucion.equals("2")) {
					paragraph = new Paragraph("2. " + respuesta2+ "\n",bold);
					documento.add(paragraph);
				} else {
					paragraph = new Paragraph("2. " + respuesta2+ "\n");
					documento.add(paragraph);
				}if(solucion.equals("3")) {
					paragraph = new Paragraph("3. " + respuesta3 + "\n",bold);
					documento.add(paragraph);
				} else {
					paragraph = new Paragraph("3. " + respuesta3+ "\n");
					documento.add(paragraph);
				}
				paragraph = new Paragraph();
				paragraph.add("Respuesta correcta: " + solucion + "\n" + "Tu respuesta fue: " + respuestas.get(i).toString() +"\n");
				documento.add(paragraph);
				if(solucion.equals(respuestas.get(i).toString())) {
					puntuacion++;
				}
			}
			//Se añade la puntuación en un párrafo a parte con el formato "X/Y"
			Paragraph parrafoPuntuacion = new Paragraph();
			parrafoPuntuacion.add("Resultado: " + puntuacion + "/" + lista_preguntas.size());
			documento.add(parrafoPuntuacion);

			documento.close(); 
			writer.close(); 	

			try {
				File path = new File("./ficheros/partida.pdf");
				Desktop.getDesktop().open(path);
			} catch (IOException ex) {
				ex.printStackTrace();
			}

		} catch (Exception ex) {
			ex.getMessage();
		}

	}
}

