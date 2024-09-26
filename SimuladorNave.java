package Simulador;

//Clase principal que simula la nave espacial
public class SimuladorNave {
 public static void main(String[] args) {
     // Crear instancias de cada módulo
     Propulsion propulsion = new Propulsion();
     Habitacion habitacion = new Habitacion();
     Laboratorio laboratorio = new Laboratorio();

     // Variables de recursos
     int energiaDisponible = 200;

     // Ciclo que simula la gestión de recursos y fallos
     for (int i = 0; i < 5; i++) { // 5 ciclos de simulación
         System.out.println("Ciclo " + (i + 1) + ":");
         
         // Monitorear consumo de recursos
         switch (i % 3) { // Simular la asignación de recursos a un módulo por ciclo
             case 0:
                 if (energiaDisponible >= Propulsion.RECURSO_NECESARIO) {
                     propulsion.consumirRecursos();
                     energiaDisponible -= Propulsion.RECURSO_NECESARIO;
                 } else {
                     System.out.println("No hay suficiente energía para el módulo de Propulsión.");
                 }
                 break;
             case 1:
                 if (energiaDisponible >= Habitacion.RECURSO_NECESARIO) {
                     habitacion.consumirRecursos();
                     energiaDisponible -= Habitacion.RECURSO_NECESARIO;
                 } else {
                     System.out.println("No hay suficiente energía para el módulo de Habitación.");
                 }
                 break;
             case 2:
                 if (energiaDisponible >= Laboratorio.RECURSO_NECESARIO) {
                     laboratorio.consumirRecursos();
                     energiaDisponible -= Laboratorio.RECURSO_NECESARIO;
                 } else {
                     System.out.println("No hay suficiente energía para el módulo de Laboratorio.");
                 }
                 break;
         }

         // Simular fallos en los módulos
         if (i == 2) {
             propulsion.fallo();
         }
         if (i == 3) {
             habitacion.fallo();
         }

         // Mostrar estado de los módulos
         propulsion.mostrarEstado();
         habitacion.mostrarEstado();
         laboratorio.mostrarEstado();

         // Reparar módulos si es necesario
         if (propulsion.estado < 50) {
             propulsion.reparar();
         }
         if (habitacion.estado < 50) {
             habitacion.reparar();
         }

         // Mostrar energía restante
         System.out.println("Energía disponible: " + energiaDisponible);
         System.out.println("----------------------------");
     }
 }
}
