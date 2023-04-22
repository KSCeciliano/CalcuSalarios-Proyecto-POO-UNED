/*
 * Autor: Kevin Steven Ceciliano Gamboa
 */
package CalcuSalariosPro;

import java.io.Serializable;

public class cSalario implements Serializable {

    //Variables de instancia
    private final double salarioBruto;
    private final double deduccionCuotaSS;
    private final double deduccionCuotaPension;
    private final double deduccionAhorroBP;
    private final double deduccionImpuestoRenta;
    private final double salarioNeto;

    //Constructor de la clase
    public cSalario(double salarioBruto) {

        //Asignación del salario bruto
        this.salarioBruto = salarioBruto;

        //Cálculo de las deducciones correspondientes y asignación a las variables de instancia
        this.deduccionCuotaSS = calcularCuotaSS();
        this.deduccionCuotaPension = calcularCuotaPension();
        this.deduccionAhorroBP = calcularAhorroBP();
        this.deduccionImpuestoRenta = calcularImpuestoRenta();

        //Cálculo del salario neto y asignación a la variable de instancia correspondiente
        this.salarioNeto = salarioBruto - deduccionCuotaSS - deduccionCuotaPension - deduccionAhorroBP - deduccionImpuestoRenta;
    }

    //Método para calcular la cuota de seguridad social
    private double calcularCuotaSS() {

        double cuotaSS = salarioBruto * 0.055; //5,5%
        return cuotaSS;
    }

    //Método para calcular la cuota de pensión
    private double calcularCuotaPension() {

        double cuotaPension = salarioBruto * 0.0417;//4,17%
        return cuotaPension;
    }

    //Método para calcular el ahorro voluntario
    private double calcularAhorroBP() {

        double ahorroBP = salarioBruto * 0.01;//1%
        return ahorroBP;
    }

    /**
     * Función que calcula el impuesto de renta a partir del salario bruto
     * ingresado.
     *
     * @return el valor del impuesto de renta calculado
     */
    private double calcularImpuestoRenta() {
        // Se calcula el exceso del salario bruto sobre el monto no gravable
        double exceso = salarioBruto - 941000;
        double impuestoRenta = 0;

        // Si hay exceso, se aplica la tarifa de impuestos correspondiente
        if (exceso > 0) {
            if (exceso <= 440000) {
                impuestoRenta = exceso * 0.10;//10%
            } else if (exceso <= 1482000) {
                //impuestoRenta = Impuesto del exceso anterior + 15% exceso correspondiente
                impuestoRenta = 44000 + (exceso - 440000) * 0.15;
            } else if (exceso <= 3904000) {
                //impuestoRenta = Impuesto del exceso anterior + 20% exceso correspondiente
                impuestoRenta = 44000 + 156300 + (exceso - 1482000) * 0.20;
            } else {
                //impuestoRenta = Impuesto del exceso anterior + 25% exceso correspondiente
                impuestoRenta = 44000 + 156300 + 484400 + (exceso - 3904000) * 0.25;
            }
        }
        // Se retorna el valor del impuesto de renta calculado
        return impuestoRenta;
    }

    //Getters
    public double getSalarioBruto() {
        return salarioBruto;
    }

    public double getDeduccionCuotaSS() {
        return deduccionCuotaSS;
    }

    public double getDeduccionCuotaPension() {
        return deduccionCuotaPension;
    }

    public double getDeduccionAhorroBP() {
        return deduccionAhorroBP;
    }

    public double getDeduccionImpuestoRenta() {
        return deduccionImpuestoRenta;
    }

    public double getSalarioNeto() {
        return salarioNeto;
    }
}