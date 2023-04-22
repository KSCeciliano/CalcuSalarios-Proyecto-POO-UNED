package CalcuSalariosPro;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

// Clase que contiene métodos para validar datos ingresados por el usuario
public class Validaciones {

    // Método que valida si la edad es mayor a 18 años
    public static boolean validarEdad(int edad) {
        return edad > 18;
    }

// Método que valida si un id de empleado no está repetido en una lista de empleados
    public static boolean validarIdUnico(ArrayList<cEmpleado> listaEmpleados, int id) {
        for (cEmpleado emp : listaEmpleados) {
            if (emp.getIdEmpleado() == id) {
                return false;
            }
        }
        return true;
    }

// Método que verifica si un id de empleado ya existe en una lista de salarios
    public static boolean existeSalario(ArrayList<Salario> listaSalarios, int id) {
        for (Salario salario : listaSalarios) {
            if (salario.getIdEmpleado() == id) {
                return true;
            }
        }
        return false;
    }

// Método que verifica si un id de empleado ya existe en una lista de empleados
    public static boolean existeEmpleado(ArrayList<cEmpleado> listaEmpleados, int id) {
        for (cEmpleado emp : listaEmpleados) {
            if (emp.getIdEmpleado() == id) {
                return true;
            }
        }
        return false;
    }

// Método que verifica si un id de empleado ya existe en una lista de empleados
    public static boolean existeEmpleado(ArrayList<cEmpleado> listaEmpleados, String id) {
        for (cEmpleado emp : listaEmpleados) {
            if (emp.getIdEmpleado() == Integer.parseInt(id)) {
                return true;
            }
        }
        return false;
    }

// Método que verifica si una planilla ya fue registrada en una lista de salarios
    public static boolean existePlanilla(ArrayList<Salario> listaSalarios, cPlanilla planilla) {
        for (Salario sal : listaSalarios) {
            if ((sal.getNumPlanilla() == planilla.getNumero()) && (sal.getMes() == planilla.getMes()) && (sal.getAnio() == planilla.getAnio())) {
                return true;
            }
        }
        return false;
    }

// Método que verifica si un empleado tiene salarios registrados en una lista de salarios
    public static boolean tieneSalarios(ArrayList<Salario> listaSalarios, int id) {
        for (Salario salario : listaSalarios) {
            if (salario.getIdEmpleado() == id) {
                return true;
            }
        }
        return false;
    }

// Método que valida si una fecha es válida en formato yyyy-MM-dd
    public boolean esFechaValida(String fechaString) {
        DateTimeFormatter formatoFecha = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        try {
            LocalDate.parse(fechaString, formatoFecha);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    // Método que valida si un valor es un número entero
    public boolean esEntero(String valor) {
        try {
            Integer.parseInt(valor);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    // Método que valida si un valor es un número entero positivo (incluye el 0)
    public boolean esEnteroPositivo(String valor) {
        try {
            Integer.parseInt(valor);
            if (Integer.parseInt(valor) >= 0) {
                return true;
            } else {
                return false;
            }

        } catch (NumberFormatException e) {
            return false;
        }
    }

    // Método que valida si un valor es un número entero positivo (No incluye el 0)
    public boolean esEnteroPositivoCero(String valor) {
        try {
            Integer.parseInt(valor);
            if (Integer.parseInt(valor) > 0) {
                return true;
            } else {
                return false;
            }

        } catch (NumberFormatException e) {
            return false;
        }
    }
}
