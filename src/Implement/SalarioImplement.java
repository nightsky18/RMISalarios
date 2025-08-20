package Implement;

import Interface.SalarioInterface;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.*;

// Implementación del objeto remoto para RMI.
// Implementa la lógica de negocio y maneja los datos de cada cliente por separado.
public class SalarioImplement extends UnicastRemoteObject implements SalarioInterface {
    // Mapas para almacenar la matriz de salarios, empleados y meses por cada cliente.
    private Map<Integer, double[][]> matrices;
    private Map<Integer, Integer> empleadosPorCliente;
    private Map<Integer, Integer> mesesPorCliente;
    private int contadorClientes; // Contador para asignar IDs únicos a los clientes.

    public SalarioImplement() throws RemoteException {
        matrices = new HashMap<>();
        empleadosPorCliente = new HashMap<>();
        mesesPorCliente = new HashMap<>();
        contadorClientes = 0;
    }

    // Registra un nuevo cliente y devuelve un ID único.
    @Override
    public synchronized int registrarCliente() throws RemoteException {
        contadorClientes++;
        System.out.println("Cliente registrado con ID: " + contadorClientes);
        return contadorClientes;
    }

    // Genera una matriz de salarios aleatorios para el cliente especificado.
    @Override
    public void generarMatriz(int clienteId, int empleados, int meses) throws RemoteException {
        double[][] matriz = new double[empleados][meses];
        Random rand = new Random();
        for (int i = 0; i < empleados; i++) {
            for (int j = 0; j < meses; j++) {
                matriz[i][j] = 1000 + rand.nextInt(9001); // Salario aleatorio entre 1000 y 10000
            }
        }
        matrices.put(clienteId, matriz);
        empleadosPorCliente.put(clienteId, empleados);
        mesesPorCliente.put(clienteId, meses);
         System.out.println(" Cliente " + clienteId + " generó una matriz de " + empleados + " empleados y " + meses + " meses.");
    }

    // Calcula el total de salarios por empleado para el cliente.
    @Override
    public double[] totalPorEmpleado(int clienteId) throws RemoteException {
        double[][] matriz = matrices.get(clienteId);
        int empleados = empleadosPorCliente.get(clienteId);
        int meses = mesesPorCliente.get(clienteId);
        double[] totales = new double[empleados];
        for (int i = 0; i < empleados; i++) {
            double suma = 0;
            for (int j = 0; j < meses; j++) suma += matriz[i][j];
            totales[i] = suma;
        }
        return totales;
    }

    // Calcula el promedio de salarios por mes para el cliente.
    @Override
    public double[] promedioPorMes(int clienteId) throws RemoteException {
        double[][] matriz = matrices.get(clienteId);
        int empleados = empleadosPorCliente.get(clienteId);
        int meses = mesesPorCliente.get(clienteId);
        double[] promedios = new double[meses];
        for (int j = 0; j < meses; j++) {
            double suma = 0;
            for (int i = 0; i < empleados; i++) suma += matriz[i][j];
            promedios[j] = suma / empleados;
        }
        return promedios;
    }

    // Calcula el total general de salarios para el cliente.
    @Override
    public double totalGeneral(int clienteId) throws RemoteException {
        double[][] matriz = matrices.get(clienteId);
        int empleados = empleadosPorCliente.get(clienteId);
        int meses = mesesPorCliente.get(clienteId);
        double total = 0;
        for (int i = 0; i < empleados; i++) {
            for (int j = 0; j < meses; j++) total += matriz[i][j];
        }
        return total;
    }

    // Exporta los resultados del cliente a un archivo CSV en la carpeta Docs.
    @Override
    public void exportarCSV(int clienteId, String nombreArchivo) throws RemoteException {
        try {
            // Crear carpeta Docs si no existe
            File carpetaDocs = new File("Docs");
            if (!carpetaDocs.exists()) {
                carpetaDocs.mkdirs();
            }

            // Generar timestamp para el nombre del archivo
            String timestamp = new java.text.SimpleDateFormat("yyyy-MM-dd_HH-mm-ss").format(new java.util.Date());

            // Nombre final del archivo con clienteId y timestamp
            String nombreFinal = "cliente_" + clienteId + "_" + timestamp + "_" + nombreArchivo;
            File archivo = new File(carpetaDocs, nombreFinal);

            // Recuperar matriz y datos del cliente
            double[][] matriz = matrices.get(clienteId);
            int empleados = empleadosPorCliente.get(clienteId);
            int meses = mesesPorCliente.get(clienteId);

            try (PrintWriter pw = new PrintWriter(new FileWriter(archivo))) {
                // Escribir encabezado
                pw.print("Empleado/Mes");
                for (int j = 0; j < meses; j++) {
                    pw.print(",Mes " + (j + 1));
                }
                pw.println(",Total Empleado");

                // Escribir filas por empleado
                for (int i = 0; i < empleados; i++) {
                    pw.print("Empleado " + (i + 1));
                    double suma = 0;
                    for (int j = 0; j < meses; j++) {
                        pw.print("," + matriz[i][j]);
                        suma += matriz[i][j];
                    }
                    pw.println("," + suma);
                }

                // Escribir promedios por mes
                pw.print("Promedio por mes");
                for (int j = 0; j < meses; j++) {
                    double sumaMes = 0;
                    for (int i = 0; i < empleados; i++) {
                        sumaMes += matriz[i][j];
                    }
                    pw.print("," + (sumaMes / empleados));
                }
                pw.println(",");

                // Escribir total general
                double total = 0;
                for (int i = 0; i < empleados; i++) {
                    for (int j = 0; j < meses; j++) {
                        total += matriz[i][j];
                    }
                }
                pw.println("Total General," + total);
            }

            System.out.println("💾 Cliente " + clienteId + " exportó datos a CSV: " + nombreArchivo);

        } catch (Exception e) {
            e.printStackTrace();
            throw new RemoteException("Error al exportar CSV", e);
        }
    }
}
