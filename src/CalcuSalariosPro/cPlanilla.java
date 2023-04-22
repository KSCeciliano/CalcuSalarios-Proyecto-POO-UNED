package CalcuSalariosPro;

import java.util.ArrayList;

public class cPlanilla {

    //Atributos
    private final int numero;
    private final int mes;
    private final int anio;
    private ArrayList<cEmpleado> empleados;

    //Constructor
    public cPlanilla(int numero, int mes, int anio) {
        this.numero = numero;
        this.mes = mes;
        this.anio = anio;
        this.empleados = new ArrayList<cEmpleado>();
    }

    //Getters
    public int getNumero() {
        return numero;
    }

    public int getMes() {
        return mes;
    }

    public int getAnio() {
        return anio;
    }

    public void agregarEmpleado(cEmpleado empleado) {
        empleados.add(empleado);
    }

    public ArrayList<cEmpleado> getEmpleados() {
        return empleados;
    }

}
