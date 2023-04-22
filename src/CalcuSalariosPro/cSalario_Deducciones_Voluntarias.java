// Este paquete contiene la clase "cSalario_Deducciones_Voluntarias" que hereda de la clase "cSalario" y agrega funcionalidades para
// calcular deducciones voluntarias como la asociación solidarista, la cooperativa, el ahorro navideño y otros rebajos.
package CalcuSalariosPro;

public class cSalario_Deducciones_Voluntarias extends cSalario {
    
    // Se declaran las variables de instancia que se utilizarán en la clase.
    private boolean asociacionSolidarista;
    private boolean cooperativa;
    private double ahorroNavideno;
    private double otrosRebajos;
    
    // Constructor que recibe el salario, la asociación solidarista, la cooperativa, el ahorro navideño y otros rebajos.
    public cSalario_Deducciones_Voluntarias(double salario, boolean asociacionSolidarista, boolean cooperativa, double ahorroNavideno, double otrosRebajos) {
        super(salario); // Se llama al constructor de la clase padre (cSalario) para inicializar el salario bruto.
        
        // Se asignan los valores recibidos a las variables de instancia correspondientes.
        this.asociacionSolidarista = asociacionSolidarista;
        this.cooperativa = cooperativa;
        this.ahorroNavideno = ahorroNavideno;
        this.otrosRebajos = otrosRebajos;
    }
    
    // Método que se sobreescribe de la clase padre para calcular el salario neto, restando las deducciones voluntarias.
    @Override
    public double getSalarioNeto(){
        double salarioNeto = super.getSalarioNeto();
        salarioNeto = salarioNeto-getDeducciones();        
        return salarioNeto;
    }
    
    // Método para calcular todas las deducciones voluntarias.
    public double getDeducciones() {
        double deducciones = 0;
        // Si está activa la opción de asociación solidarista, se calcula la deducción correspondiente.
        if (asociacionSolidarista) {
            deducciones += calcularDeduccionSolidarista();
        }
        // Si está activa la opción de cooperativa, se calcula la deducción correspondiente.
        if (cooperativa) { 
            deducciones += calcularDeduccionCooperativa();
        }
        // Si el porcentaje de ahorro navideño es mayor a cero, se calcula la deducción correspondiente.
        if (ahorroNavideno > 0) {
            deducciones += calcularDeduccionAhorroNavideno();
        }
        // Si el valor de otros rebajos es mayor a cero, se agrega a las deducciones.
        if (otrosRebajos > 0) {
            deducciones += otrosRebajos;
        }
        return deducciones;
    }

    // Método para calcular la deducción de la asociación solidarista.
    public double calcularDeduccionSolidarista() {
        return this.getSalarioBruto() * 0.03;
    }

    // Método para calcular la deducción de la cooperativa.
    public double calcularDeduccionCooperativa() {
        return this.getSalarioBruto() * 0.01;
    }

    // Método para calcular la deducción del ahorro navideño.
    public double calcularDeduccionAhorroNavideno() {
        return this.getSalarioBruto() * (ahorroNavideno / 100);
    }

    //Setters
    public void setAsociacionSolidarista(boolean asociacionSolidarista) {
        this.asociacionSolidarista = asociacionSolidarista;
    }
    
    public void setCooperativa(boolean cooperativa) {
        this.cooperativa = cooperativa;
    }
    
    public void setAhorroNavideno(double ahorroNavideno) {
        this.ahorroNavideno = ahorroNavideno;
    }
    
    public void setOtrosRebajos(double otrosRebajos) {
        this.otrosRebajos = otrosRebajos;
    }
    
    //Getters
 
    public boolean isAsociacionSolidarista() {
        return asociacionSolidarista;
    }
    
    public boolean isCooperativa() {
        return cooperativa;
    }

    
    
    public double getAsociacionSolidarista() {
        if(asociacionSolidarista){
            return calcularDeduccionSolidarista();
        }       
        return 0;
    }
    
    public double getCooperativa() {
        if(cooperativa){
            return calcularDeduccionCooperativa();
        }       
        return 0;
    }
    
    public double getAhorroNavideno() {
        if(ahorroNavideno>0){
            return calcularDeduccionAhorroNavideno();
        }       
        return 0;
    }
    
    public double porcientoAhorroNavideno(){
        return ahorroNavideno;
    }
    
    public double getOtrosRebajos() {
        return otrosRebajos;
    }
}
