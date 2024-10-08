package Simulador;

class Habitacion extends Modulo {
    public static final int RECURSO_NECESARIO = 40;

    public Habitacion() {
        super("Habitación", RECURSO_NECESARIO);
    }

    @Override
    public void consumirRecursos() {
        System.out.println("El módulo de Habitación está consumiendo " + consumoRecurso + " unidades de energía.");
    }
}
