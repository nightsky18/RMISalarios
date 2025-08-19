package Client;
import Interface.SalarioInterface;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

// Cliente de consola para el sistema de salarios.
// Rol: front-end de consola.
// Pide empleados y meses por consola, busca el servicio remoto y muestra los resultados.
public class Cliente {
    public static void main(String[] args) {
        try {
            // Busca el servicio remoto en el registro RMI (localhost:1099) y obtiene la referencia.
            Registry registry = LocateRegistry.getRegistry("localhost", 1099);
            SalarioInterface servicio = (SalarioInterface) registry.lookup("SalarioService");

            // Solicita al usuario la cantidad de empleados y meses.
            Scanner sc = new Scanner(System.in);
            System.out.print("Ingrese número de empleados: ");
            int empleados = sc.nextInt();
            System.out.print("Ingrese número de meses: ");
            int meses = sc.nextInt();

            // Llama al método remoto para generar la matriz de salarios.
            servicio.generarMatriz(empleados, meses);

            // Llama y muestra el total por empleado.
            System.out.println("\nTotal por empleado:");
            double[] totales = servicio.totalPorEmpleado();
            for (int i = 0; i < totales.length; i++) {
                System.out.println("Empleado " + (i+1) + ": $" + totales[i]);
            }

            // Llama y muestra el promedio por mes.
            System.out.println("\nPromedio por mes:");
            double[] promedios = servicio.promedioPorMes();
            for (int j = 0; j < promedios.length; j++) {
                System.out.println("Mes " + (j+1) + ": $" + promedios[j]);
            }

            // Llama y muestra el total general.
            System.out.println("\nTotal general pagado: $" + servicio.totalGeneral());
            System.out.print("\n¿Desea exportar los resultados a un archivo CSV? (s/n): ");
            char opcion = sc.next().charAt(0);
            if (opcion == 's' || opcion == 'S') {
                servicio.exportarCSV("salarios.csv");
                System.out.println("Resultados exportados en 'salarios.csv'");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
