# Acceso-Datos-P12
## ADDP1.2

### 1. Enunciado
Desarrolla un juego de preguntas tipo Trivial con varias respuestas posibles.
Realizar una aplicación que permita:
1. Jugar. Las preguntas y respuestas se leerán de un fichero **preguntas.xml** con un
formato determinado y serán mostradas al usuario secuencialmente, a las que irá
respondiendo. De cada pregunta se almacenarán 3 posibles respuestas (que
serán mostradas al usuario) y la respuesta correcta, para que cuando éste elija
una, se pueda autocorregir, informando al usuario si dicha respuesta es correcta o
no.
2. Se implementará una clase Pregunta.java (POJO) para ir creando objetos de ese
tipo a medida que se va leyendo el fichero XML de preguntas.
3. Se implementará un sistema de puntos para contabilizar las preguntas acertadas
y las que no.
4. Añadir preguntas. El usuario podrá añadir más preguntas, escribiendo en el fichero
XML de preguntas.
5. Importar preguntas. El usuario podrá importar preguntas desde un archivo EXCEL
(**preguntas.xls**), con formato libre, añadiéndolas al fichero de preguntas, estando
así disponibles para jugar.
6. Una vez acabe cada partida, se pedirá al usuario si quiere que se muestre un
informe en PDF (**partida.pdf**) con el resultado de la misma con la puntuación
obtenida, la corrección de las preguntas, etc.
7. Una vez acabada la partida, se pedirá al usuario que introduzca su nombre y
apellidos (el sistema almacenará y manejará las 3 iniciales en mayúsculas, a
modo de arcade). Si no existe el fichero de récords (un fichero de texto) se creará
con el nombre **records.txt**, registrando así en cada línea el nombre del usuario y
los puntos obtenidos. Si el fichero de récords existe, se comprobará si existe un
registro para el usuario ingresado, si no existe se creará una nueva línea en el
fichero y si ya existe, se comprobará la puntuación actual obtenida con la
registrada, almacenándose la nueva puntuación en el caso de ser mayor que la
registrada.
8. Permitir ver los récords almacenados ordenados por puntuación, visualizándose
de mayor a menor.
9. Diseñar un menú para poder elegir las opciones para ejecutar la aplicación. El
menú debe tener las opciones siguientes:

MENÚ
1- Jugar
2- Añadir pregunta
3- Importar preguntas
4- Ver records
5- Instrucciones (se mostrará por pantalla las instrucciones del juego así
como el sistema de puntuación)
0- Salir (mientras el usuario no marque esta opción, se permanecerá en el
menú principal)
Introducir opción:

Además, todos los ficheros a manejar se almacenarán en una carpeta **/ficheros**
dentro de la raíz del propio proyecto.

Como ampliación, realiza el desarrollo de una zona administrativa de la aplicación
que permita realizar funciones auxiliares. Si el usuario se loga como
administrador se le mostrará un menú desde donde podrá realizar las operaciones
de ‘Añadir pregunta’ e ‘Importar preguntas’ (el usuario no administrador no tendrá
disponibles estas funcionalidades).


### Solución
Se han realizado todos los puntos del enunciado. Icluida la ampliación.
La parte relacionada con la lectura/escritura xml y memoria se me ha atragantado
un poco, no he tenido tiempo de mejorarlo y seguro que hay código que se podía 
haber simplificado a través de funciones.