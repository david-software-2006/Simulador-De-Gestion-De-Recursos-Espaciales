package Simulador;

//Clase Habitacion hereda de Modulo
class Habitacion extends Modulo {
	public static final int RECURSO_NECESARIO = 30;
	
	public Habitacion() {
		super("Habitacion", RECURSO_NECESARIO);
	}
	
	@Override
	public void consumirRecursos() {
		System.out.println("Habitacion consumiendo " + consumoRecurso + " unidades de energía");
	}
}
