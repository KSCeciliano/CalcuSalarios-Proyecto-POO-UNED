package CalcuSalariosPro;

import java.io.*; //proporciona clases para manejar entrada/salida de datos.
import java.time.LocalDate; //se utiliza para representar fechas
import java.io.BufferedReader; //se utiliza para leer texto de una secuencia de entrada de caracteres
import java.io.BufferedWriter;// se utiliza para escribir texto en una secuencia de salida de caracteres.
import java.io.FileReader;//para leer datos de un archivo de texto.
import java.io.FileWriter;//para escribir datos en un archivo de texto.
import java.io.IOException;//para manejar excepciones relacionadas con la entrada/salida de datos
import java.util.ArrayList;//para almacenar y manipular una lista de objetos. 

public class ArchivoEmpleados {

    private String nombreArchivo;

    public ArchivoEmpleados(String nombreArchivo) {
        this.nombreArchivo = nombreArchivo;
    }

    public boolean guardarEmpleado(cEmpleado empleado) { // Método para guardar un objeto de tipo cEmpleado en un archivo
        BufferedWriter bw = null; // Declaración de una variable para escribir en el archivo
        File archivo = new File(nombreArchivo); // Crear un nuevo objeto File con el nombre del archivo especificado
        boolean archivoVacio = (archivo.length() == 0); // Verificar si el archivo está vacío comparando su longitud con cero

        try {
            bw = new BufferedWriter(new FileWriter(nombreArchivo, true)); // Abrir el archivo en modo de escritura, el segundo parámetro indica que se agrega contenido al archivo en lugar de sobrescribirlo
            String linea = empleado.getIdEmpleado() + "," + empleado.getNombre() + "," + empleado.getApellidos() + "," + empleado.getEdad() + ","
                    + empleado.getPuestoDesempenado() + "," + empleado.getFechaIngreso(); // Construir una cadena con los datos del empleado separados por comas

            // Si el archivo está vacío, no agregamos una nueva línea
            if (archivoVacio) {
                bw.write(linea); // Escribir la línea en el archivo
            } else {
                // Si ya hay líneas en el archivo, agregamos una nueva línea antes de escribir
                bw.newLine(); // Agregar una nueva línea antes de escribir
                bw.write(linea); // Escribir la línea en el archivo
            }
            return true; // Devolver verdadero si el proceso de escritura fue exitoso
        } catch (IOException e) { // Manejo de excepciones en caso de que algo salga mal al escribir en el archivo
            e.printStackTrace(); // Imprimir el seguimiento de la pila para ayudar en la depuración
            return false; // Devolver falso si hubo un error al escribir en el archivo
        } finally {
            if (bw != null) { // Si la variable BufferedWriter no es nula, cerrar el archivo
                try {
                    bw.close(); // Cerrar el archivo
                } catch (IOException e) { // Manejo de excepciones en caso de que algo salga mal al cerrar el archivo
                    e.printStackTrace(); // Imprimir el seguimiento de la pila para ayudar en la depuración
                }
            }
        }
    }

    public ArrayList<cEmpleado> cargarEmpleados() { // Declaración del método para cargar empleados en una ArrayList
        ArrayList<cEmpleado> empleados = new ArrayList<cEmpleado>(); // Inicialización de una ArrayList vacía de empleados
        BufferedReader br = null; // Declaración de una variable de tipo BufferedReader
        try {
            br = new BufferedReader(new FileReader(nombreArchivo)); // Apertura del archivo para lectura y asignación al BufferedReader
            String linea;
            while ((linea = br.readLine()) != null) { // Mientras se leen líneas del archivo
                String[] datos = linea.split(","); // Se divide cada línea en sus datos
                int idEmpleado = Integer.parseInt(datos[0]); // Se obtiene el id del empleado de la línea actual
                String nombre = datos[1]; // Se obtiene el nombre del empleado de la línea actual
                String apellidos = datos[2]; // Se obtienen los apellidos del empleado de la línea actual
                int edad = Integer.parseInt(datos[3]); // Se obtiene la edad del empleado de la línea actual
                String puestoDesempenado = datos[4]; // Se obtiene el puesto desempeñado del empleado de la línea actual
                LocalDate fechaIngreso = LocalDate.parse(datos[5]); // Se obtiene la fecha de ingreso del empleado de la línea actual
                cEmpleado empleado = new cEmpleado(idEmpleado, nombre, apellidos, edad, puestoDesempenado, fechaIngreso); // Se crea un objeto de tipo cEmpleado con los datos obtenidos
                empleados.add(empleado); // Se agrega el objeto empleado a la ArrayList
            }
        } catch (IOException e) { // Captura de excepción en caso de error de entrada/salida
            e.printStackTrace(); // Impresión del stack trace del error
        } finally { // Bloque finally que se ejecuta siempre
            if (br != null) { // Si el BufferedReader fue inicializado correctamente
                try {
                    br.close(); // Se cierra el archivo
                } catch (IOException e) { // Captura de excepción en caso de error de entrada/salida al cerrar el archivo
                    e.printStackTrace(); // Impresión del stack trace del error
                }
            }
        }
        return empleados; // Devuelve la ArrayList de empleados leídos del archivo
    }

    public boolean eliminarEmpleado(int idEmpleado) {
        boolean eliminado = false; // bandera para indicar si se eliminó el empleado con éxito
        try {
            FileReader fr = new FileReader(nombreArchivo); // abrir archivo para leer
            BufferedReader br = new BufferedReader(fr); // buffer para lectura eficiente
            FileWriter fw = new FileWriter(nombreArchivo + ".temp"); // crear archivo temporal para escritura
            BufferedWriter bw = new BufferedWriter(fw); // buffer para escritura eficiente

            String linea;
            boolean primeraLinea = true; // indicador de primera línea escrita
            while ((linea = br.readLine()) != null) { // leer líneas del archivo original
                String[] campos = linea.split(","); // separar campos de la línea usando la coma como delimitador
                if (Integer.parseInt(campos[0]) == idEmpleado) { // si el id del empleado coincide, marcar como eliminado
                    eliminado = true;
                } else {
                    if (!primeraLinea) { // si no es la primera línea escrita, agregar salto de línea antes de escribir
                        bw.write("\n");
                    } else {
                        primeraLinea = false; // si es la primera línea, no agregar salto de línea
                    }
                    bw.write(linea); // escribir la línea al archivo temporal
                }
            }

            br.close(); // cerrar buffer de lectura
            bw.close(); // cerrar buffer de escritura

            File archivoOriginal = new File(nombreArchivo); // crear objeto File para el archivo original
            archivoOriginal.delete(); // borrar archivo original
            File archivoTemporal = new File(nombreArchivo + ".temp"); // crear objeto File para el archivo temporal
            archivoTemporal.renameTo(archivoOriginal); // renombrar archivo temporal como archivo original

        } catch (IOException e) { // manejo de excepciones en caso de error de entrada/salida
            e.printStackTrace();
        }
        return eliminado; // devolver valor de la bandera eliminado
    }

    public void modificarEmpleado(cEmpleado empleadoModificado) { // Define un método público llamado modificarEmpleado que recibe un objeto de tipo cEmpleado llamado empleadoModificado como parámetro.
        ArrayList<cEmpleado> empleados = cargarEmpleados(); // Carga la lista de empleados desde algún lugar y la almacena en una nueva variable llamada empleados.
        ArrayList<cEmpleado> nuevosEmpleados = new ArrayList<cEmpleado>(); // Crea una nueva lista vacía llamada nuevosEmpleados.
        boolean empleadoEncontrado = false; // Define una variable booleana llamada empleadoEncontrado y la inicializa como false.
        for (cEmpleado empleado : empleados) { // Inicia un ciclo for que recorre todos los empleados en la lista de empleados.
            if (empleado.getIdEmpleado() == empleadoModificado.getIdEmpleado()) { // Si el id del empleado actual es igual al id del empleado modificado...
                nuevosEmpleados.add(empleadoModificado); // Agrega el empleado modificado a la lista de nuevos empleados.
                empleadoEncontrado = true; // Cambia el valor de la variable empleadoEncontrado a true.
            } else { // Si el id del empleado actual no es igual al id del empleado modificado...
                nuevosEmpleados.add(empleado); // Agrega el empleado actual a la lista de nuevos empleados.
            }
        }
        if (empleadoEncontrado) { // Si se encontró un empleado con el id especificado...
            guardarEmpleadosTotal(nuevosEmpleados); // Llama a un método llamado guardarEmpleadosTotal y le pasa la lista de nuevos empleados como parámetro.
        } else { // Si no se encontró un empleado con el id especificado...
            System.out.println("No se encontró un empleado con el id especificado."); // Imprime un mensaje en la consola.
        }
    }

    // Esta función recibe una lista de empleados y un ID y devuelve el empleado que tenga ese ID
    public cEmpleado obtenerEmpleado(ArrayList<cEmpleado> listaEmpleados, int id) {

        // Itera a través de cada empleado en la lista de empleados
        for (cEmpleado emp : listaEmpleados) {

            // Si el ID del empleado actual coincide con el ID proporcionado, devuelve ese empleado
            if (emp.getIdEmpleado() == id) {
                return emp;
            }
        }

        // Si no se encuentra ningún empleado con el ID proporcionado, devuelve null
        return null;
    }

// Esta función recibe una lista de empleados y guarda cada empleado individualmente
    private void guardarEmpleados(ArrayList<cEmpleado> empleados) {

        // Itera a través de cada empleado en la lista de empleados
        for (cEmpleado cem : empleados) {

            // Guarda el empleado actual usando la función 'guardarEmpleado'
            this.guardarEmpleado(cem);
        }
    }

// Esta función recibe una lista de empleados y guarda cada empleado en un archivo
    private void guardarEmpleadosTotal(ArrayList<cEmpleado> empleados) {

        // Crea un objeto de archivo para el archivo de empleados
        File archivoEmpleados = new File(nombreArchivo);

        // Si el archivo de empleados existe, se elimina
        if (archivoEmpleados.exists()) {
            archivoEmpleados.delete();
        }

        // Itera a través de cada empleado en la lista de empleados
        for (cEmpleado cem : empleados) {

            // Guarda el empleado actual en el archivo de empleados usando la función 'guardarEmpleado'
            this.guardarEmpleado(cem);
        }
    }

    //Función de prueba
    public ArrayList<cEmpleado> obtenerEmpleadosDePrueba() {
        ArrayList<cEmpleado> empleados = new ArrayList<cEmpleado>();

        empleados.add(new cEmpleado(1, "Juan", "Pérez", 25, "Programador", LocalDate.of(2020, 1, 1)));
        empleados.add(new cEmpleado(2, "Ana", "García", 30, "Diseñadora", LocalDate.of(2018, 6, 1)));

        return empleados;
    }

}
