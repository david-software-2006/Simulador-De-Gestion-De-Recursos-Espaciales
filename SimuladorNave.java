package Simulador;

import java.util.Random;
import java.util.Scanner;

public class SimuladorNave {

    public static void escribirConAnimacion(String texto, int retardo) {
        for (char caracter : texto.toCharArray()) {
            System.out.print(caracter);
            try {
                Thread.sleep(retardo);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        escribirConAnimacion("¡Bienvenido al simulador de viaje espacial!", 50);
        escribirConAnimacion("Por favor, introduce tu nombre, Capitán: ", 50);

        String nombreCapitan = scanner.nextLine();
        escribirConAnimacion("Estás al mando de la nave. Tu misión es llevar una caja de antimateria a los científicos del Consejo Galáctico en el planeta Astraea.", 50);
        escribirConAnimacion("Esta antimateria es esencial para activar una fuente de energía que salvará a Astraea de una catástrofe inminente.", 50);

        escribirConAnimacion("¿Te gustaría ponerle un nombre a tu nave? (si/no): ", 50);
        String respuestaNombreNave = scanner.nextLine().trim().toLowerCase();

        String nombreNave;
        if (respuestaNombreNave.equals("si")) {
            escribirConAnimacion("Por favor, introduce el nombre de tu nave: ", 50);
            nombreNave = scanner.nextLine();
        } else {
            nombreNave = "Explorador Estelar";
        }

        escribirConAnimacion("La nave debe viajar por 5 sectores galácticos hasta llegar a Astraea.", 50);
        escribirConAnimacion("Cada sector implica desafíos, y tendrás que asegurar que los módulos de la nave funcionen correctamente.", 50);
        escribirConAnimacion("La caja de antimateria está almacenada en el módulo de carga. ¡Buena suerte, Capitán " + nombreCapitan + "!\n", 50);

        escribirConAnimacion("Introduce la cantidad de energía total disponible para la nave: ", 50);
        int energiaDisponible = scanner.nextInt();

        Propulsion propulsion = new Propulsion();
        Habitacion habitacion = new Habitacion();
        Laboratorio laboratorio = new Laboratorio();

        int sector = 1;
        while (sector <= 5) {
            escribirConAnimacion("\nSector " + sector + ":", 50);
            escribirConAnimacion("Viajando hacia Astraea...", 50);

            // Evento aleatorio en el sector
            int evento = random.nextInt(4); // 0-3 para diferentes eventos
            switch (evento) {
                case 0:
                    escribirConAnimacion("¡Has encontrado un campo de asteroides!", 50);
                    while (true) { // Repetir hasta que se asigne suficiente energía
                        escribirConAnimacion("El módulo de Propulsión ha sufrido un daño, ¿cuánta energía deseas asignar para repararlo?: ", 50);
                        int energiaReparacion = scanner.nextInt();
                        if (energiaReparacion >= Propulsion.RECURSO_NECESARIO) {
                            propulsion.reparar(energiaReparacion);
                            energiaDisponible -= energiaReparacion;
                            break; // Salir del bucle si la reparación fue exitosa
                        } else {
                            escribirConAnimacion("La cantidad de energía asignada es insuficiente. Debes asignar al menos " + Propulsion.RECURSO_NECESARIO + " unidades.", 50);
                        }
                    }
                    break;

                case 1:
                    escribirConAnimacion("Te encuentras con otra nave de exploración.", 50);
                    escribirConAnimacion("Te ofrecen intercambiar recursos. ¿Aceptas el intercambio? (si/no): ", 50);
                    scanner.nextLine(); // Limpiar el buffer
                    String intercambio = scanner.nextLine().trim().toLowerCase();
                    if (intercambio.equals("si")) {
                        int recursoIntercambiado = random.nextInt(30) + 10; // 10-39 unidades de energía
                        energiaDisponible += recursoIntercambiado;
                        escribirConAnimacion("Has recibido " + recursoIntercambiado + " unidades de energía.", 50);
                    } else {
                        escribirConAnimacion("Decidiste continuar sin intercambio.", 50);
                    }
                    break;

                case 2:
                    escribirConAnimacion("Un rayo cósmico ha golpeado la nave, consumiendo energía.", 50);
                    energiaDisponible -= 20; // Reduce 20 unidades de energía
                    escribirConAnimacion("La energía disponible ahora es: " + energiaDisponible, 50);
                    break;

                case 3:
                    escribirConAnimacion("Has encontrado un satélite abandonado, ¡puedes recolectar recursos!", 50);
                    escribirConAnimacion("¿Deseas recolectar los recursos? (si/no): ", 50);
                    scanner.nextLine(); // Limpiar el buffer
                    String recoleccion = scanner.nextLine().trim().toLowerCase();
                    if (recoleccion.equals("si")) {
                        int recursoRecogido = random.nextInt(20) + 5; // 5-24 unidades de energía
                        energiaDisponible += recursoRecogido;
                        escribirConAnimacion("Has recogido " + recursoRecogido + " unidades de energía.", 50);
                    } else {
                        escribirConAnimacion("Decidiste no recolectar recursos.", 50);
                    }
                    break;
            }

            // Consumo de energía por módulo
            switch (sector % 3) {
                case 1:
                    if (energiaDisponible >= Propulsion.RECURSO_NECESARIO) {
                        propulsion.consumirRecursos();
                        energiaDisponible -= Propulsion.RECURSO_NECESARIO;
                    } else {
                        escribirConAnimacion("No hay suficiente energía para el módulo de Propulsión.", 50);
                    }
                    break;
                case 2:
                    if (energiaDisponible >= Habitacion.RECURSO_NECESARIO) {
                        habitacion.consumirRecursos();
                        energiaDisponible -= Habitacion.RECURSO_NECESARIO;
                    } else {
                        escribirConAnimacion("No hay suficiente energía para el módulo de Habitación.", 50);
                    }
                    break;
                case 3:
                    if (energiaDisponible >= Laboratorio.RECURSO_NECESARIO) {
                        laboratorio.consumirRecursos();
                        energiaDisponible -= Laboratorio.RECURSO_NECESARIO;
                    } else {
                        escribirConAnimacion("No hay suficiente energía para el módulo de Laboratorio.", 50);
                    }
                    break;
            }

            // Fallos en los módulos a lo largo del viaje
            if (sector == 3) {
                escribirConAnimacion("¡El módulo de Propulsión ha fallado!", 50);
                while (true) { // Repetir hasta que se asigne suficiente energía
                    escribirConAnimacion("¿Cuánta energía deseas asignar para repararlo?: ", 50);
                    int energiaRepararPropulsion = scanner.nextInt();
                    if (energiaRepararPropulsion >= Propulsion.RECURSO_NECESARIO) {
                        propulsion.reparar(energiaRepararPropulsion);
                        energiaDisponible -= energiaRepararPropulsion;
                        break; // Salir del bucle si la reparación fue exitosa
                    } else {
                        escribirConAnimacion("La cantidad de energía asignada es insuficiente para reparar el módulo de Propulsión.", 50);
                    }
                }
            }
            if (sector == 4) {
                escribirConAnimacion("¡El módulo de Habitación ha fallado!", 50);
                while (true) { // Repetir hasta que se asigne suficiente energía
                    escribirConAnimacion("¿Cuánta energía deseas asignar para repararlo?: ", 50);
                    int energiaRepararHabitacion = scanner.nextInt();
                    if (energiaRepararHabitacion >= Habitacion.RECURSO_NECESARIO) {
                        habitacion.reparar(energiaRepararHabitacion);
                        energiaDisponible -= energiaRepararHabitacion;
                        break; // Salir del bucle si la reparación fue exitosa
                    } else {
                        escribirConAnimacion("La cantidad de energía asignada es insuficiente para reparar el módulo de Habitación.", 50);
                    }
                }
            }

            // Mostrar estado de los módulos
            propulsion.mostrarEstado();
            habitacion.mostrarEstado();
            laboratorio.mostrarEstado();

            // Mostrar energía restante
            escribirConAnimacion("Energía disponible: " + energiaDisponible, 50);
            escribirConAnimacion("----------------------------", 50);

            // Incrementar sector
            sector++;
        }

        // Llegada a Astraea
        if (energiaDisponible > 0) {
            escribirConAnimacion("¡Has llegado al planeta Astraea, Capitán " + nombreCapitan + "!", 50);
            escribirConAnimacion("Al llegar, te encuentras con un científico que dice ser el Dr. Zorax.", 50);
            escribirConAnimacion("Él está interesado en la antimateria que traes. ¿Deseas entregársela? (si/no): ", 50);
            scanner.nextLine(); // Limpiar el buffer
            String entregaAntimateria = scanner.nextLine().trim().toLowerCase();
            if (entregaAntimateria.equals("si")) {
                escribirConAnimacion("Pero de repente, revela que es un villano y ha planeado destruir la nave.", 50);
                escribirConAnimacion("La antimateria comienza a reaccionar de manera incontrolable. ¡Tu nave está en peligro!", 50);
                escribirConAnimacion("¡Debes actuar rápido para sobrevivir! ¿Qué deseas hacer?", 50);
                escribirConAnimacion("1. Activar el sistema de auto-destrucción.\n2. Tratar de desactivar la antimateria.", 50);
                int decision = scanner.nextInt();
                if (decision == 1) {
                    escribirConAnimacion("La nave se está autodestruyendo... ¡Tu misión ha terminado!", 50);
                } else {
                    escribirConAnimacion("Lograste desactivar la antimateria justo a tiempo. ¡Has salvado tu nave!", 50);
                }
            } else {
                escribirConAnimacion("Decidiste no entregar la antimateria.", 50);
                escribirConAnimacion("El Dr. Zorax se enoja y ordena a sus hombres que te ataquen.", 50);
                escribirConAnimacion("¡Debes luchar por tu vida! ¿Cómo deseas proceder?", 50);
                escribirConAnimacion("1. Huir a la nave y activar los escudos.\n2. Enfrentar al Dr. Zorax.", 50);
                int decision = scanner.nextInt();
                if (decision == 1) {
                    escribirConAnimacion("Lograste escapar a tu nave y activar los escudos. ¡Salvaste tu vida!", 50);
                } else {
                    escribirConAnimacion("Te enfrentas al Dr. Zorax, pero es demasiado poderoso. ¡Tu misión ha fracasado!", 50);
                }
            }
        } else {
            escribirConAnimacion("Tu nave no pudo completar la misión debido a la falta de energía. ¡Has fallado!", 50);
        }

        scanner.close();
    }
}
