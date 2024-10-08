package Simulador;

class Propulsion extends Modulo {
    public static final int RECURSO_NECESARIO = 50;

    public Propulsion() {
        super("Propulsión", RECURSO_NECESARIO);
    }

    @Override
    public void consumirRecursos() {
        System.out.println("El módulo de Propulsión está consumiendo " + consumoRecurso + " unidades de energía.");
    }
}
