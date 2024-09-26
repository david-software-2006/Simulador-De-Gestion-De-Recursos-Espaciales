package Simulador;

//Clase Propulsion hereda de Modulo
class Propulsion extends Modulo {
	public static final int RECURSO_NECESARIO = 50;
	
	public Propulsion() {
		super("Propulsion ", RECURSO_NECESARIO);
	}
	
	@Override
	public void consumirRecursos() {
		System.out.println("Propulsion consumiendo " + consumoRecurso + " unidades de energía");
	}
}
