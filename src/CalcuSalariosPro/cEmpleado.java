package CalcuSalariosPro;

import java.time.LocalDate;

public class cEmpleado {

    // Atributos
    private int idEmpleado; // Identificador único del empleado
    private String nombre; // Nombre del empleado
    private String apellidos; // Apellidos del empleado
    private int edad; // Edad del empleado
    private String puestoDesempenado; // Puesto que desempeña el empleado
    private LocalDate fechaIngreso; // Fecha de ingreso del empleado a la empresa

    // Constructor
    public cEmpleado(int idEmpleado, String nombre, String apellidos, int edad, String puestoDesempenado, LocalDate fechaIngreso) {
        // Inicialización de los atributos a través de los parámetros del constructor
        this.idEmpleado = idEmpleado;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.edad = edad;
        this.puestoDesempenado = puestoDesempenado;
        this.fechaIngreso = fechaIngreso;
    }

    // Métodos Getters para acceder a los atributos desde fuera de la clase
    public int getIdEmpleado() {
        return idEmpleado;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public int getEdad() {
        return edad;
    }

    public String getPuestoDesempenado() {
        return puestoDesempenado;
    }

    public LocalDate getFechaIngreso() {
        return fechaIngreso;
    }

    // Métodos Setters para modificar los atributos desde fuera de la clase
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public void setPuestoDesempenado(String puestoDesempenado) {
        this.puestoDesempenado = puestoDesempenado;
    }

    public void setFechaIngreso(LocalDate fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    // Método para imprimir información detallada del empleado
    public String toStringReporte() {
        return "Empleado: "
                + "ID " + idEmpleado
                + ", " + nombre
                + " " + apellidos
                + ", " + edad
                + " años, " + puestoDesempenado
                + ", " + fechaIngreso;
    }
}
