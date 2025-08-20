package Server;
import Implement.SalarioImplement;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Servidor {
    public static void main(String[] args) {
        try {
            // Crear registro RMI en el puerto 1099
            Registry registry = LocateRegistry.createRegistry(1099);

            // Instanciar implementación del servicio
            SalarioImplement servicio = new SalarioImplement();

            // Publicar el servicio
            registry.rebind("SalarioService", servicio);

            System.out.println("==============================================");
            System.out.println(" Servidor RMI iniciado en el puerto 1099");
            System.out.println(" Servicio publicado con el nombre: SalarioService");
            System.out.println(" Esperando conexiones de clientes...");
            System.out.println("==============================================");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}