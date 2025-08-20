#  Proyecto RMI - Gestión de Salarios

## Descripción
Este proyecto implementa un sistema Cliente-Servidor utilizando Java RMI (Remote Method Invocation) siguiendo el patrón Interface.  
El objetivo es permitir que un cliente capture el número de empleados y el número de meses, y que el servidor procese:

- Generación de una **matriz de salarios** aleatoria.
- Cálculo del **total pagado por cada empleado**.
- Cálculo del **promedio mensual de salarios**.
- Cálculo del **total general pagado**.

El sistema está diseñado para soportar múltiples clientes concurrentes, cada uno con su propia matriz de salarios. Los clientes se conectan a un servidor centralizado que expone los servicios remotos.

Se incluye además un cliente gráfico (Swing) que facilita la interacción con el sistema y la visualización de resultados.

---

##  Estructura del Proyecto
- src/
- ├─ Interface/
- │ └─ SalarioInterface.java # Contrato de métodos remotos
- ├─ Implement/
- │ └─ SalarioImplement.java # Lógica de negocio (objeto remoto)
- ├─ Server/
- │ └─ Servidor.java # Arranca el servicio RMI
- └─ Client/
- └─ ClienteGUI.java # Interfaz de consola para el usuario

---

## Tecnologías utilizadas
- **Java SE 17+**
- **Java RMI API**
- **Visual Studio Code** con Java Extension Pack

---

## Funcionalidades
1. Registro automático de clientes con un ID único.
2. Generación de una matriz de salarios aleatoria según el número de empleados y meses ingresados por el cliente.
3. Cálculo de:
   - Totales por empleado.
   - Promedios por mes
   - Total general de la matriz
4. Exportación de los resultados a un archivo CSV en la carpeta Docs/, con nombre único que incluye ID de cliente y timestamp
5. Consola del servidor con trazas en tiempo real para identificar conexiones y acciones de los clientes.

---

## Ejecución del Proyecto

### 1. Compilación
- Run Servidor
- Run Cliente
## Aprendizaje Independiente
Como parte del trabajo independiente en RMI se exploraro
- Persistencia en CSV para guardar resultados de cada cliente.
- Manejo de múltiples clientes concurrentes con HashMap en el servidor.
- Interfaces gráficas en el cliente usando Swing.
- Control de eventos en servidor para monitorear la conexión y acciones de los clientes.

Esto permitió comprender RMI no solo en un escenario académico, sino como un modelo base de sistemas distribuidos reales, reforzando conceptos de concurrencia, persistencia y comunicación remota.

## Bibliografía
- Oracle Java Documentation: https://docs.oracle.com/javase/8/docs/technotes/guides/rmi/
- Coulouris, G., Dollimore, J., Kindberg, T. (2013). Sistemas Distribuidos: Conceptos y Diseño. Addison-Wesley.

## Autores
Proyecto desarrollado por: 
- Mateo Berrío Cardona
- Mariana Montoya Sepúlveda.

## Programación Distribuida y Paralela.



