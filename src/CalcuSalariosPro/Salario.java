package CalcuSalariosPro;

public class Salario {

    //Atributos
    private int idEmpleado;
    private cSalario_Deducciones_Voluntarias objSalario;
    private int numPlanilla;
    private int mes;
    private int anio;

    //Constructor
    public Salario(int idEmpleado, cSalario_Deducciones_Voluntarias objSalario, int numPlanilla, int mes, int anio) {
        this.idEmpleado = idEmpleado;
        this.objSalario = objSalario;
        this.numPlanilla = numPlanilla;
        this.mes = mes;
        this.anio = anio;
    }

    //Getters y Setters
    public int getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(int idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    public cSalario_Deducciones_Voluntarias getObjSalario() {
        return objSalario;
    }

    public void setobjSalario(cSalario_Deducciones_Voluntarias objSalario) {
        this.objSalario = objSalario;
    }

    public int getNumPlanilla() {
        return numPlanilla;
    }

    public void setNumPlanilla(int numPlanilla) {
        this.numPlanilla = numPlanilla;
    }

    public int getMes() {
        return mes;
    }

    public void setMes(int mes) {
        this.mes = mes;
    }

    public int getAnio() {
        return anio;
    }

    public void setAnio(int anio) {
        this.anio = anio;
    }

}
