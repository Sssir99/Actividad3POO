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
                    String nombre = JOptionPane.showInputDialog("Nombre:");
                    String direccion = JOptionPane.showInputDialog("Dirección:");
                    String ciudad = JOptionPane.showInputDialog("Ciudad:");
                    Empresa empresa = new Empresa(nit, nombre, direccion, ciudad);
                    opEmpresa.agregarEmpresa(empresa);
                    JOptionPane.showMessageDialog(null, "Empresa agregada.");
                    break;
                case 1: // Agregar empleado
                    String doc = JOptionPane.showInputDialog("Documento:");
                    String nom = JOptionPane.showInputDialog("Nombre:");
                    double sueldo = 0;
                    try {
                        sueldo = Double.parseDouble(JOptionPane.showInputDialog("Sueldo por hora:"));
                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(null, "Valor de sueldo inválido.");
                        break;
                    }
                    String nitEmp = JOptionPane.showInputDialog("NIT de la empresa:");
                    Empresa emp = opEmpresa.buscarEmpresaPorNit(nitEmp);
                    if (emp == null) {
                        JOptionPane.showMessageDialog(null, "Empresa no encontrada.");
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
                    JOptionPane.showMessageDialog(null, sbEmp.toString());
                    break;
                case 3: // Listar empleados
                    StringBuilder sbEmpl = new StringBuilder("--- Empleados ---\n");
                    for (Empleado e : opEmpleado.listarEmpleados()) {
                        sbEmpl.append(e).append("\n");
                    }
                    JOptionPane.showMessageDialog(null, sbEmpl.toString());
                    break;
                case 4: // Buscar empleado por documento
                    String docBuscado = JOptionPane.showInputDialog("Documento:");
                    Empleado empBuscado = opEmpleado.buscarEmpleadoPorDocumento(docBuscado);
                    if (empBuscado != null) {
                        JOptionPane.showMessageDialog(null, empBuscado.toString());
                    } else {
                        JOptionPane.showMessageDialog(null, "Empleado no encontrado.");
                    }
                    break;
                case 5: // Calcular sueldo de empleado
                    String docSueldo = JOptionPane.showInputDialog("Documento:");
                    int horas = 0;
                    try {
                        horas = Integer.parseInt(JOptionPane.showInputDialog("Horas trabajadas:"));
                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(null, "Valor de horas inválido.");
                        break;
                    }
                    double sueldoCalc = opEmpleado.calcularSueldoEmpleado(docSueldo, horas);
                    JOptionPane.showMessageDialog(null, "Sueldo calculado: " + sueldoCalc);
                    break;
                case 6: // Contar empleados por empresa
                    String nitContar = JOptionPane.showInputDialog("NIT de la empresa:");
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