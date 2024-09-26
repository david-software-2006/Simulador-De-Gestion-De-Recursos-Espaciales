package Simulador;

//Clase Laboratorio hereda de Modulo
class Laboratorio extends Modulo {
	public static final int RECURSO_NECESARIO = 40;
	
	public Laboratorio() {
		super("Laboratorio", RECURSO_NECESARIO);
	}
	
	@Override
	public void consumirRecursos() {
		System.out.println("Laboratorio consumiendo " + consumoRecurso + " unidades de energía");
	}
}
