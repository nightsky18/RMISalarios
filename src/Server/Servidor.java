package Server;
import Implement.SalarioImplement;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Servidor {
    public static void main(String[] args) {
        try {
            // Crea o abre el RMI Registry en el puerto 1099 (no es necesario lanzar rmiregistry aparte).
            Registry registry = LocateRegistry.createRegistry(1099);

            // Crea una instancia de SalarioImplement y la publica en el registro bajo el nombre "SalarioService".
            registry.rebind("SalarioService", new SalarioImplement());

            // Mensaje de confirmación: el servidor RMI está listo y escuchando.
            System.out.println("Servidor RMI listo y escuchando...");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}