package Simulador;

import java.util.Scanner;

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

    public void reparar(int energiaAsignada) {
        if (energiaAsignada < consumoRecurso) {
            System.out.println("La energía asignada es menor a la necesaria para la reparación. Se requieren al menos " + consumoRecurso + " unidades.");
            return;  // No realizar la reparación si la energía es insuficiente
        }

        if (estado < MAX_ESTADO) {
            estado += energiaAsignada;
            if (estado > MAX_ESTADO) {
                estado = MAX_ESTADO;
            }
            System.out.println("El módulo " + nombre + " ha sido reparado con " + energiaAsignada + " puntos de energía.");
        } else {
            System.out.println("El módulo " + nombre + " no necesita reparación.");
        }
    }

    public void mostrarEstado() {
        System.out.println(nombre + " estado: " + estado + "/100");
    }

    public void fallo(Scanner scanner) {
        estado -= 30;  // Sufre un fallo que reduce el estado
        if (estado < 0) estado = 0;
        System.out.println("El módulo " + nombre + " ha sufrido un fallo. Estado actual: " + estado);

        // Preguntar si desea repararlo
        System.out.print("¿Deseas asignar energía para reparar el módulo? (s/n): ");
        String respuesta = scanner.next();

        if (respuesta.equalsIgnoreCase("s")) {
            System.out.print("Ingresa la cantidad de energía que deseas asignar para la reparación: ");
            int energiaAsignada = scanner.nextInt();
            reparar(energiaAsignada);
        }
    }
}
