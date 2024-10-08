package Simulador;

class Laboratorio extends Modulo {
    public static final int RECURSO_NECESARIO = 30;

    public Laboratorio() {
        super("Laboratorio", RECURSO_NECESARIO);
    }

    @Override
    public void consumirRecursos() {
        System.out.println("El módulo de Laboratorio está consumiendo " + consumoRecurso + " unidades de energía.");
    }
}
