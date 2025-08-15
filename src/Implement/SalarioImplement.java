package Implement;
import Interface.SalarioInterface;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Random;

// Implementación real del objeto remoto.
// Implementa la interfaz SalarioInterface y extiende UnicastRemoteObject para habilitar RMI.
// Aquí se almacena la matriz de salarios y se realizan los cálculos solicitados por el cliente.
// Rol: aquí vive la lógica de negocio que el cliente invoca remotamente.
public class SalarioImplement extends UnicastRemoteObject implements SalarioInterface {
    private double[][] matriz; // Matriz de salarios [empleados][meses]
    private int empleados;
    private int meses;

    public SalarioImplement() throws RemoteException {}

    // Genera la matriz de salarios aleatorios para los empleados y meses indicados.
    @Override
    public void generarMatriz(int empleados, int meses) throws RemoteException {
        this.empleados = empleados;
        this.meses = meses;
        matriz = new double[empleados][meses];
        Random rand = new Random();

        for (int i = 0; i < empleados; i++) {
            for (int j = 0; j < meses; j++) {
                matriz[i][j] = 1000 + rand.nextInt(9001); // Salario aleatorio entre 1000 y 10000
            }
        }
    }

    // Calcula el total de salarios por cada empleado.
    @Override
    public double[] totalPorEmpleado() throws RemoteException {
        double[] totales = new double[empleados];
        for (int i = 0; i < empleados; i++) {
            double suma = 0;
            for (int j = 0; j < meses; j++) {
                suma += matriz[i][j];
            }
            totales[i] = suma;
        }
        return totales;
    }

    // Calcula el promedio de salarios por cada mes.
    @Override
    public double[] promedioPorMes() throws RemoteException {
        double[] promedios = new double[meses];
        for (int j = 0; j < meses; j++) {
            double suma = 0;
            for (int i = 0; i < empleados; i++) {
                suma += matriz[i][j];
            }
            promedios[j] = suma / empleados;
        }
        return promedios;
    }

    // Calcula el total general de todos los salarios.
    @Override
    public double totalGeneral() throws RemoteException {
        double total = 0;
        for (int i = 0; i < empleados; i++) {
            for (int j = 0; j < meses; j++) {
                total += matriz[i][j];
            }
        }
        return total;
    }
}
