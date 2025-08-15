#  Proyecto RMI - Gestión de Salarios

## Descripción
Este proyecto implementa un sistema **Cliente-Servidor** utilizando **Java RMI (Remote Method Invocation)** siguiendo el **patrón Interface**.  
El objetivo es permitir que un cliente capture el número de empleados y el número de meses, y que el servidor procese:

- Generación de una **matriz de salarios** aleatoria.
- Cálculo del **total pagado por cada empleado**.
- Cálculo del **promedio mensual de salarios**.
- Cálculo del **total general pagado**.

De esta forma, se ejemplifica cómo distribuir la lógica de negocio en un entorno **distribuido** con Java RMI.

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
- └─ Cliente.java # Interfaz de consola para el usuario

---

## Tecnologías utilizadas
- **Java SE 17+**
- **Java RMI API**
- **Visual Studio Code** con Java Extension Pack

---

## Flujo de Ejecución
1. **El cliente** solicita número de empleados y meses.
2. **El servidor** genera una matriz de salarios aleatoria y calcula:
   - Totales por empleado.
   - Promedio por mes.
   - Total general.
3. **El cliente** recibe y muestra los resultados en consola.

---

## Ejecución del Proyecto

### 1. Compilación
- Run Servidor
- Run Cliente
## Bibliografía
- Oracle Java Documentation: https://docs.oracle.com/javase/8/docs/technotes/guides/rmi/
- Coulouris, G., Dollimore, J., Kindberg, T. (2013). Sistemas Distribuidos: Conceptos y Diseño. Addison-Wesley.

## Autores
- Mateo Berrío Cardona
- Mariana Montoya Sepúlveda

