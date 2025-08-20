package Client;

import Interface.SalarioInterface;
import java.awt.*;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import javax.swing.*;

// Interfaz gráfica del cliente RMI para la gestión de nómina.
// Permite ingresar empleados y meses, consultar resultados y exportar a CSV.
public class ClienteGUI extends JFrame {
    private SalarioInterface servicio; // Referencia al objeto remoto RMI
    private int clienteId; // ID único del cliente

    private JTextField txtEmpleados, txtMeses; // Campos de entrada para empleados y meses
    private JTextArea areaResultados; // Área para mostrar resultados
    private JButton btnGenerar, btnExportar; // Botones de acciones

    public ClienteGUI() {
        setTitle("Cliente RMI - Nómina");
        setSize(500, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // Panel de entrada de datos
        JPanel panel = new JPanel(new GridLayout(3, 2));
        panel.add(new JLabel("Número de empleados:"));
        txtEmpleados = new JTextField();
        panel.add(txtEmpleados);
        panel.add(new JLabel("Número de meses:"));
        txtMeses = new JTextField();
        panel.add(txtMeses);

        btnGenerar = new JButton("Generar y calcular");
        btnExportar = new JButton("Exportar CSV");

        areaResultados = new JTextArea();
        areaResultados.setEditable(false);

        add(panel, BorderLayout.NORTH);
        add(new JScrollPane(areaResultados), BorderLayout.CENTER);

        // Panel de botones
        JPanel panelBotones = new JPanel();
        panelBotones.add(btnGenerar);
        panelBotones.add(btnExportar);
        add(panelBotones, BorderLayout.SOUTH);

        // Conexión al servidor RMI
        conectarRMI();

        // Acciones de los botones
        btnGenerar.addActionListener(e -> generarResultados());
        btnExportar.addActionListener(e -> exportarCSV());
    }

    // Conecta con el servidor RMI y registra el cliente
    private void conectarRMI() {
        try {
            Registry registry = LocateRegistry.getRegistry("localhost", 1099);
            servicio = (SalarioInterface) registry.lookup("SalarioService");
            clienteId = servicio.registrarCliente();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al conectar con el servidor: " + e.getMessage());
        }
    }

    // Llama a los métodos remotos para generar la matriz y mostrar los resultados
    private void generarResultados() {
        try {
            int empleados = Integer.parseInt(txtEmpleados.getText());
            int meses = Integer.parseInt(txtMeses.getText());

            servicio.generarMatriz(clienteId, empleados, meses);

            double[] totales = servicio.totalPorEmpleado(clienteId);
            double[] promedios = servicio.promedioPorMes(clienteId);
            double totalGeneral = servicio.totalGeneral(clienteId);

            StringBuilder sb = new StringBuilder();
            sb.append("=== Totales por Empleado ===\n");
            for (int i = 0; i < totales.length; i++) {
                sb.append("Empleado " + (i+1) + ": $" + totales[i] + "\n");
            }

            sb.append("\n=== Promedios por Mes ===\n");
            for (int j = 0; j < promedios.length; j++) {
                sb.append("Mes " + (j+1) + ": $" + promedios[j] + "\n");
            }

            sb.append("\n=== Total General ===\n$" + totalGeneral + "\n");

            areaResultados.setText(sb.toString());

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
        }
    }

    // Llama al método remoto para exportar los resultados a un archivo CSV
    private void exportarCSV() {
        try {
            servicio.exportarCSV(clienteId, "salarios_cliente_" + clienteId + ".csv");
            JOptionPane.showMessageDialog(this, "Archivo CSV exportado a carpeta Docs.");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error al exportar: " + ex.getMessage());
        }
    }

    // Método principal: inicia la interfaz gráfica
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ClienteGUI().setVisible(true));
    }
}
