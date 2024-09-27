package Simulador;

abstract class Modulo {
    protected String nombre;
    protected int consumoRecurso;
    protected int estado; 
    protected final int MAX_ESTADO = 100; 
    
    public Modulo(String nombre, int consumoRecurso) {
        this.nombre = nombre;
        this.consumoRecurso = consumoRecurso;
        this.estado = MAX_ESTADO;
    }

   
    public abstract void consumirRecursos();

    // Método para reparar el módulo
    public void reparar() {
        if (estado < MAX_ESTADO) {
            estado = MAX_ESTADO;
            System.out.println("El módulo " + nombre + " ha sido reparado.");
        } else {
            System.out.println("El módulo " + nombre + " no necesita reparación.");
        }
    }

    // Método para mostrar el estado del módulo
    public void mostrarEstado() {
        System.out.println(nombre + " estado: " + estado + "/100");
    }

    // Simular un fallo en el módulo
    public void fallo() {
        estado -= 30; 
        if (estado < 0) estado = 0;
        System.out.println("El módulo " + nombre + " ha sufrido un fallo!");
    }
}

