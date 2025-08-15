package Interface;

import java.rmi.Remote;
import java.rmi.RemoteException;

// Esta es la interfaz remota para RMI. Define QUÉ servicios ofrece el servidor.
// Todas las funciones lanzan RemoteException porque pueden cruzar procesos o máquinas (contrato RMI).
public interface SalarioInterface extends Remote {

    // Genera la matriz de salarios según la cantidad de empleados y meses.
    void generarMatriz(int empleados, int meses) throws RemoteException;

    // Devuelve un arreglo con el total de salarios por cada empleado.
    double[] totalPorEmpleado() throws RemoteException;

    // Devuelve un arreglo con el promedio de salarios por cada mes.
    double[] promedioPorMes() throws RemoteException;

    // Devuelve el total general de todos los salarios.
    double totalGeneral() throws RemoteException;
}