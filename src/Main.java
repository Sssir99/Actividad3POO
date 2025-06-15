import modelos.*;
import operaciones.*;
import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        OperacionEmpresa opEmpresa = new OperacionEmpresa();
        OperacionEmpleado opEmpleado = new OperacionEmpleado();
        String[] opciones = {
                "Agregar empresa",
                "Agregar empleado",
                "Listar empresas",
                "Listar empleados",
                "Buscar empleado por documento",
                "Calcular sueldo de empleado",
                "Contar empleados por empresa",
                "Salir"
        };
        int opcion;
        do {
            opcion = JOptionPane.showOptionDialog(
                    null,
                    "Seleccione una opción:",
                    "Menú Principal",
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.INFORMATION_MESSAGE,
                    null,
                    opciones,
                    opciones[0]);
            switch (opcion) {
                case 0: // Agregar empresa
                    String nit = JOptionPane.showInputDialog("NIT:");
                    if (nit == null || nit.trim().isEmpty()) {
                        JOptionPane.showMessageDialog(null, "El NIT no puede estar vacío.");
                        break;
                    }
                    if (opEmpresa.buscarEmpresaPorNit(nit) != null) {
                        JOptionPane.showMessageDialog(null, "Ya existe una empresa con ese NIT.");
                        break;
                    }
                    String nombre = JOptionPane.showInputDialog("Nombre:");
                    if (nombre == null || nombre.trim().isEmpty()) {
                        JOptionPane.showMessageDialog(null, "El nombre no puede estar vacío.");
                        break;
                    }
                    String direccion = JOptionPane.showInputDialog("Dirección:");
                    if (direccion == null || direccion.trim().isEmpty()) {
                        JOptionPane.showMessageDialog(null, "La dirección no puede estar vacía.");
                        break;
                    }
                    String ciudad = JOptionPane.showInputDialog("Ciudad:");
                    if (ciudad == null || ciudad.trim().isEmpty()) {
                        JOptionPane.showMessageDialog(null, "La ciudad no puede estar vacía.");
                        break;
                    }
                    Empresa empresa = new Empresa(nit, nombre, direccion, ciudad);
                    opEmpresa.agregarEmpresa(empresa);
                    JOptionPane.showMessageDialog(null, "Empresa agregada.");
                    break;
                case 1: // Agregar empleado
                    String doc = JOptionPane.showInputDialog("Documento:");
                    if (doc == null || doc.trim().isEmpty()) {
                        JOptionPane.showMessageDialog(null, "El documento no puede estar vacío.");
                        break;
                    }
                    if (opEmpleado.buscarEmpleadoPorDocumento(doc) != null) {
                        JOptionPane.showMessageDialog(null, "Ya existe un empleado con ese documento.");
                        break;
                    }
                    String nom = JOptionPane.showInputDialog("Nombre:");
                    if (nom == null || nom.trim().isEmpty()) {
                        JOptionPane.showMessageDialog(null, "El nombre no puede estar vacío.");
                        break;
                    }
                    double sueldo = 0;
                    try {
                        String sueldoStr = JOptionPane.showInputDialog("Sueldo por hora:");
                        if (sueldoStr == null || sueldoStr.trim().isEmpty()) {
                            JOptionPane.showMessageDialog(null, "El sueldo no puede estar vacío.");
                            break;
                        }
                        sueldo = Double.parseDouble(sueldoStr);
                        if (sueldo <= 0) {
                            JOptionPane.showMessageDialog(null, "El sueldo debe ser mayor a cero.");
                            break;
                        }
                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(null, "Valor de sueldo inválido.");
                        break;
                    }
                    String nitEmp = JOptionPane.showInputDialog("NIT de la empresa:");
                    if (nitEmp == null || nitEmp.trim().isEmpty()) {
                        JOptionPane.showMessageDialog(null, "El NIT de la empresa no puede estar vacío.");
                        break;
                    }
                    Empresa emp = opEmpresa.buscarEmpresaPorNit(nitEmp);
                    if (emp == null) {
                        JOptionPane.showMessageDialog(null, "Empresa no encontrada. Debe crear la empresa primero.");
                        break;
                    }
                    String[] tipos = { "Desarrollador", "GestorProyectos", "Admin" };
                    int tipo = JOptionPane.showOptionDialog(
                            null,
                            "Tipo de empleado:",
                            "Tipo de empleado",
                            JOptionPane.DEFAULT_OPTION,
                            JOptionPane.QUESTION_MESSAGE,
                            null,
                            tipos,
                            tipos[0]);
                    Empleado empleado = null;
                    if (tipo == 0) {
                        empleado = new Desarrollador(doc, nom, sueldo, emp);
                    } else if (tipo == 1) {
                        String area = JOptionPane.showInputDialog("Área:");
                        if (area == null || area.trim().isEmpty()) {
                            JOptionPane.showMessageDialog(null, "El área no puede estar vacía.");
                            break;
                        }
                        empleado = new GestorProyectos(doc, nom, sueldo, emp, area);
                    } else if (tipo == 2) {
                        empleado = new Admin(doc, nom, sueldo, emp);
                    }
                    if (empleado != null) {
                        opEmpleado.agregarEmpleado(empleado);
                        JOptionPane.showMessageDialog(null, "Empleado agregado.");
                    }
                    break;
                case 2: // Listar empresas
                    StringBuilder sbEmp = new StringBuilder("--- Empresas ---\n");
                    for (Empresa e : opEmpresa.listarEmpresas()) {
                        sbEmp.append(e.getNit()).append(" - ").append(e.getNombre()).append("\n");
                    }
                    if (sbEmp.toString().equals("--- Empresas ---\n")) {
                        JOptionPane.showMessageDialog(null, "No hay empresas registradas.");
                    } else {
                        JOptionPane.showMessageDialog(null, sbEmp.toString());
                    }
                    break;
                case 3: // Listar empleados
                    StringBuilder sbEmpl = new StringBuilder("--- Empleados ---\n");
                    for (Empleado e : opEmpleado.listarEmpleados()) {
                        sbEmpl.append(e).append("\n");
                    }
                    if (sbEmpl.toString().equals("--- Empleados ---\n")) {
                        JOptionPane.showMessageDialog(null, "No hay empleados registrados.");
                    } else {
                        JOptionPane.showMessageDialog(null, sbEmpl.toString());
                    }
                    break;
                case 4: // Buscar empleado por documento
                    String docBuscado = JOptionPane.showInputDialog("Documento:");
                    if (docBuscado == null || docBuscado.trim().isEmpty()) {
                        JOptionPane.showMessageDialog(null, "El documento no puede estar vacío.");
                        break;
                    }
                    Empleado empBuscado = opEmpleado.buscarEmpleadoPorDocumento(docBuscado);
                    if (empBuscado != null) {
                        JOptionPane.showMessageDialog(null, empBuscado.toString());
                    } else {
                        JOptionPane.showMessageDialog(null, "Empleado no encontrado.");
                    }
                    break;
                case 5: // Calcular sueldo de empleado
                    String docSueldo = JOptionPane.showInputDialog("Documento:");
                    if (docSueldo == null || docSueldo.trim().isEmpty()) {
                        JOptionPane.showMessageDialog(null, "El documento no puede estar vacío.");
                        break;
                    }
                    int horas = 0;
                    try {
                        String horasStr = JOptionPane.showInputDialog("Horas trabajadas:");
                        if (horasStr == null || horasStr.trim().isEmpty()) {
                            JOptionPane.showMessageDialog(null, "Las horas no pueden estar vacías.");
                            break;
                        }
                        horas = Integer.parseInt(horasStr);
                        if (horas <= 0) {
                            JOptionPane.showMessageDialog(null, "Las horas deben ser mayores a cero.");
                            break;
                        }
                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(null, "Valor de horas inválido.");
                        break;
                    }
                    double sueldoCalc = opEmpleado.calcularSueldoEmpleado(docSueldo, horas);
                    if (sueldoCalc > 0) {
                        JOptionPane.showMessageDialog(null, "Sueldo calculado: " + sueldoCalc);
                    } else {
                        JOptionPane.showMessageDialog(null, "Empleado no encontrado o sueldo inválido.");
                    }
                    break;
                case 6: // Contar empleados por empresa
                    String nitContar = JOptionPane.showInputDialog("NIT de la empresa:");
                    if (nitContar == null || nitContar.trim().isEmpty()) {
                        JOptionPane.showMessageDialog(null, "El NIT de la empresa no puede estar vacío.");
                        break;
                    }
                    Empresa empContar = opEmpresa.buscarEmpresaPorNit(nitContar);
                    if (empContar != null) {
                        int cantidad = opEmpleado.contarEmpleadosPorEmpresa(empContar);
                        JOptionPane.showMessageDialog(null, "Cantidad de empleados: " + cantidad);
                    } else {
                        JOptionPane.showMessageDialog(null, "Empresa no encontrada.");
                    }
                    break;
                case 7: // Salir
                    JOptionPane.showMessageDialog(null, "Saliendo...");
                    break;
                default:
                    break;
            }
        } while (opcion != 7 && opcion != JOptionPane.CLOSED_OPTION);
    }
}