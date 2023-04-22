package CalcuSalariosPro;

import java.io.*; //proporciona clases para manejar entrada/salida de datos.
import java.time.LocalDate; //se utiliza para representar fechas
import java.io.BufferedReader; //se utiliza para leer texto de una secuencia de entrada de caracteres
import java.io.BufferedWriter;// se utiliza para escribir texto en una secuencia de salida de caracteres.
import java.io.FileReader;//para leer datos de un archivo de texto.
import java.io.FileWriter;//para escribir datos en un archivo de texto.
import java.io.IOException;//para manejar excepciones relacionadas con la entrada/salida de datos
import java.util.ArrayList;//para almacenar y manipular una lista de objetos. 

public class ArchivoSalarios {

    private String nombreArchivo;

    public ArchivoSalarios(String nombreArchivo) {
        this.nombreArchivo = nombreArchivo;

    }

// Método para guardar los datos de un salario en un archivo de texto
    public boolean guardarSalario(Salario salario) {

        // Inicializamos un BufferedWriter a null
        BufferedWriter bw = null;

        // Creamos un nuevo objeto File a partir del nombre del archivo proporcionado
        File archivo = new File(nombreArchivo);

        // Verificamos si el archivo está vacío
        boolean archivoVacio = (archivo.length() == 0);

        try {
            // Creamos un nuevo BufferedWriter que agregue texto al archivo existente, si es que hay uno
            bw = new BufferedWriter(new FileWriter(nombreArchivo, true));

            // Concatenamos los datos del salario en una cadena de texto separada por comas
            String linea = salario.getIdEmpleado() + "," + salario.getNumPlanilla() + "," + salario.getMes() + "," + salario.getAnio() + ","
                    + salario.getObjSalario().getSalarioBruto() + "," + salario.getObjSalario().isAsociacionSolidarista() + ","
                    + salario.getObjSalario().isCooperativa() + "," + salario.getObjSalario().porcientoAhorroNavideno() + "," + salario.getObjSalario().getOtrosRebajos();

            // Si el archivo está vacío, no agregamos una nueva línea antes de escribir
            if (archivoVacio) {
                bw.write(linea);
            } else {
                // Si ya hay líneas en el archivo, agregamos una nueva línea antes de escribir
                bw.newLine();
                bw.write(linea);
            }

            // Indicamos que la operación de escritura ha sido exitosa
            return true;
        } catch (IOException e) {
            // Imprimimos el mensaje de error en la consola
            e.printStackTrace();
            // Indicamos que ha ocurrido un error al escribir en el archivo
            return false;
        } finally {
            // Si el BufferedWriter no es null, lo cerramos
            if (bw != null) {
                try {
                    bw.close();
                } catch (IOException e) {
                    // Si ocurre un error al cerrar el BufferedWriter, lo imprimimos en la consola
                    e.printStackTrace();
                }
            }
        }
    }

    public ArrayList<Salario> cargarSalarios() {
        // Creamos una lista vacía de Salarios
        ArrayList<Salario> salarios = new ArrayList<Salario>();

        // Declaramos un BufferedReader y lo inicializamos en null
        BufferedReader br = null;

        try {
            // Inicializamos el BufferedReader con un FileReader que lee el archivo "nombreArchivo"
            br = new BufferedReader(new FileReader(nombreArchivo));
            String linea;

            // Leemos cada línea del archivo
            while ((linea = br.readLine()) != null) {
                // Separamos los datos de cada línea por comas
                String[] datos = linea.split(",");

                // Convertimos los datos en variables numéricas
                int idEmpleado = Integer.parseInt(datos[0]);
                int numPlanilla = Integer.parseInt(datos[1]);
                int mes = Integer.parseInt(datos[2]);
                int anio = Integer.parseInt(datos[3]);

                // Convertimos los datos en variables booleanas y numéricas
                double salarioBruto = Double.parseDouble(datos[4]);
                boolean asociacionSolidarista = Boolean.parseBoolean(datos[5]);
                boolean cooperativa = Boolean.parseBoolean(datos[6]);
                double ahorroNavideno = Double.parseDouble(datos[7]);
                double otrosRebajos = Double.parseDouble(datos[8]);

                // Creamos un objeto Salario con los datos obtenidos
                Salario salario = new Salario(idEmpleado, new cSalario_Deducciones_Voluntarias(salarioBruto, asociacionSolidarista, cooperativa, ahorroNavideno, otrosRebajos), numPlanilla, mes, anio);

                // Agregamos el objeto Salario a la lista de Salarios
                salarios.add(salario);
            }
        } catch (IOException e) {
            // Si hay una excepción, imprimimos la pila de llamadas para debuggear
            e.printStackTrace();
        } finally {
            // Cerramos el BufferedReader para liberar recursos
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    // Si hay una excepción, imprimimos la pila de llamadas para debuggear
                    e.printStackTrace();
                }
            }
        }

        // Devolvemos la lista de Salarios
        return salarios;
    }

    // Método para eliminar los salarios de un empleado dado su id
    public boolean eliminarSalarios(int idEmpleado) {
        boolean eliminado = false; // variable que indicará si se eliminaron salarios

        try {
            // Abrimos el archivo para leer los datos
            FileReader fr = new FileReader(nombreArchivo);
            BufferedReader br = new BufferedReader(fr);

            // Abrimos un archivo temporal para escribir los datos actualizados
            FileWriter fw = new FileWriter(nombreArchivo + ".temp");
            BufferedWriter bw = new BufferedWriter(fw);

            String linea;
            boolean primeraLinea = true; // indicador de primera línea escrita

            // Recorremos todas las líneas del archivo original
            while ((linea = br.readLine()) != null) {
                // Separamos los campos de cada línea por comas
                String[] campos = linea.split(",");

                // Si el id del empleado coincide con el id buscado, marcamos que se ha eliminado
                if (Integer.parseInt(campos[0]) == idEmpleado) {
                    eliminado = true;
                } else {
                    // Si no coincide, escribimos la línea en el archivo temporal
                    if (!primeraLinea) {
                        bw.write("\n"); // solo agregamos salto de línea después de la primera línea escrita
                    } else {
                        primeraLinea = false;
                    }
                    bw.write(linea);
                }
            }

            // Cerramos los archivos
            br.close();
            bw.close();

            // Eliminamos el archivo original y renombramos el archivo temporal con su nombre original
            File archivoOriginal = new File(nombreArchivo);
            archivoOriginal.delete();
            File archivoTemporal = new File(nombreArchivo + ".temp");
            archivoTemporal.renameTo(archivoOriginal);

        } catch (IOException e) {
            e.printStackTrace();
        }

        return eliminado; // retornamos el valor de eliminado
    }

    // Esta función recibe como parámetros un número de planilla, un mes y un año, y devuelve
// una lista de salarios que coincidan con los valores de dichos parámetros.
    public ArrayList<Salario> obtenerSalariosPlanilla(int planilla, int mes, int anio) {
        ArrayList<Salario> salariosPlanilla = new ArrayList<Salario>();

        // Se carga la lista completa de salarios del sistema.
        ArrayList<Salario> salarios = cargarSalarios();

        // Se recorre la lista de salarios, y se agregan a la lista "salariosPlanilla"
        // aquellos que coincidan con los parámetros de planilla, mes y año recibidos.
        for (Salario salario : salarios) {
            if (salario.getNumPlanilla() == planilla && salario.getMes() == mes && salario.getAnio() == anio) {
                salariosPlanilla.add(salario);
            }
        }

        // Se devuelve la lista de salarios que coinciden con los parámetros recibidos.
        return salariosPlanilla;
    }

// Esta función recibe como parámetro un identificador de empleado, y devuelve una lista de
// salarios correspondientes a dicho empleado.
    public ArrayList<Salario> obtenerSalariosEmpleado(int idEmpleado) {
        ArrayList<Salario> salariosEmpleado = new ArrayList<Salario>();

        // Se carga la lista completa de salarios del sistema.
        ArrayList<Salario> salarios = cargarSalarios();

        // Se recorre la lista de salarios, y se agregan a la lista "salariosEmpleado"
        // aquellos que corresponden al identificador de empleado recibido.
        for (Salario salario : salarios) {
            if (salario.getIdEmpleado() == idEmpleado) {
                salariosEmpleado.add(salario);
            }
        }

        // Se devuelve la lista de salarios correspondientes al empleado recibido.
        return salariosEmpleado;
    }

// Esta función devuelve una lista de salarios de prueba, que pueden ser utilizados
// para hacer pruebas de funcionalidad del sistema.
    public ArrayList<Salario> obtenerSalariosDePrueba() {
        ArrayList<Salario> salarios = new ArrayList<Salario>();

        // Se crean dos objetos "cSalario_Deducciones_Voluntarias" que representan
        // salarios con deducciones voluntarias, y se agregan a la lista "salarios".
        cSalario_Deducciones_Voluntarias salario1 = new cSalario_Deducciones_Voluntarias(2000000, true, false, 2, 0);
        cSalario_Deducciones_Voluntarias salario2 = new cSalario_Deducciones_Voluntarias(1000000, true, true, 5, 10000);

        salarios.add(new Salario(101010, salario1, 1, 2, 2023));
        salarios.add(new Salario(212121, salario2, 2, 2, 2023));
        salarios.add(new Salario(212121, salario2, 1, 1, 2023));

        // Se devuelve la lista de salarios de prueba creada.
        return salarios;
    }

    public ArrayList<Salario> cargarPlanilla(int intPlanilla, int intMes, int intAnio) {
        // Se crea un ArrayList de Salario vacío.
        ArrayList<Salario> salarios = new ArrayList<Salario>();
        // Se crea un objeto BufferedReader inicialmente sin valor.
        BufferedReader br = null;
        try {
            // Se crea un objeto BufferedReader a partir de un objeto FileReader con el nombre del archivo.
            br = new BufferedReader(new FileReader(nombreArchivo));
            String linea;
            // Se lee el archivo línea por línea y se asigna a la variable 'linea'.
            while ((linea = br.readLine()) != null) {
                // Se separa cada línea por comas y se almacena en el arreglo 'datos'.
                String[] datos = linea.split(",");
                // Se convierte cada dato en su respectivo tipo.
                int idEmpleado = Integer.parseInt(datos[0]);
                int numPlanilla = Integer.parseInt(datos[1]);
                int mes = Integer.parseInt(datos[2]);
                int anio = Integer.parseInt(datos[3]);
                double salarioBruto = Double.parseDouble(datos[4]);
                boolean asociacionSolidarista = Boolean.parseBoolean(datos[5]);
                boolean cooperativa = Boolean.parseBoolean(datos[6]);
                double ahorroNavideno = Double.parseDouble(datos[7]);
                double otrosRebajos = Double.parseDouble(datos[8]);
                // Se crea un objeto Salario a partir de los datos leídos y se almacena en la variable 'salario'.
                Salario salario = new Salario(idEmpleado, new cSalario_Deducciones_Voluntarias(salarioBruto, asociacionSolidarista, cooperativa, ahorroNavideno, otrosRebajos), numPlanilla, mes, anio);
                // Si el número de planilla, mes y año coinciden con los parámetros ingresados, se agrega el objeto 'salario' al ArrayList 'salarios'.
                if (numPlanilla == intPlanilla && mes == intMes && anio == intAnio) {
                    salarios.add(salario);
                }
            }
        } catch (IOException e) {
            // Si ocurre una excepción de tipo IOException, se imprime la pila de errores.
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    // Si el objeto BufferedReader no es nulo, se cierra el archivo.
                    br.close();
                } catch (IOException e) {
                    // Si ocurre una excepción de tipo IOException al cerrar el archivo, se imprime la pila de errores.
                    e.printStackTrace();
                }
            }
        }
        // Se devuelve el ArrayList de Salario que contiene los objetos que coinciden con los parámetros ingresados.
        return salarios;
    }

}
