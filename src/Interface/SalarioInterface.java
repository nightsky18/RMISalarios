package Interface;

import java.rmi.Remote;
import java.rmi.RemoteException;

// Interfaz remota para RMI. Define QUÉ servicios expone el servidor.
// Todas las funciones lanzan RemoteException porque pueden cruzar procesos o máquinas (contrato RMI).
public interface SalarioInterface extends Remote {
    // Registra un nuevo cliente y devuelve un ID único.
    int registrarCliente() throws RemoteException;

    // Genera la matriz de salarios para el cliente especificado.
    void generarMatriz(int clienteId, int empleados, int meses) throws RemoteException;

    // Devuelve el total de salarios por empleado para el cliente.
    double[] totalPorEmpleado(int clienteId) throws RemoteException;

    // Devuelve el promedio de salarios por mes para el cliente.
    double[] promedioPorMes(int clienteId) throws RemoteException;

    // Devuelve el total general de salarios para el cliente.
    double totalGeneral(int clienteId) throws RemoteException;

    // Exporta los resultados a un archivo CSV para el cliente.
    void exportarCSV(int clienteId, String nombreArchivo) throws RemoteException;
}
