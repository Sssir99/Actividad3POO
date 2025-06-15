import modelos.*;
import operaciones.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        OperacionEmpresa opEmpresa = new OperacionEmpresa();
        OperacionEmpleado opEmpleado = new OperacionEmpleado();
        Scanner sc = new Scanner(System.in);
        int opcion;
        do {
            System.out.println("\n--- Menú Principal ---");
            System.out.println("1. Agregar empresa");
            System.out.println("2. Agregar empleado");
            System.out.println("3. Listar empresas");
            System.out.println("4. Listar empleados");
            System.out.println("5. Buscar empleado por documento");
            System.out.println("6. Calcular sueldo de empleado");
            System.out.println("7. Contar empleados por empresa");
            System.out.println("0. Salir");
            System.out.print("Seleccione una opción: ");
            opcion = sc.nextInt();
            sc.nextLine();
            switch (opcion) {
                case 1:
                    System.out.print("NIT: ");
                    String nit = sc.nextLine();
                    System.out.print("Nombre: ");
                    String nombre = sc.nextLine();
                    System.out.print("Dirección: ");
                    String direccion = sc.nextLine();
                    System.out.print("Ciudad: ");
                    String ciudad = sc.nextLine();
                    Empresa empresa = new Empresa(nit, nombre, direccion, ciudad);
                    opEmpresa.agregarEmpresa(empresa);
                    System.out.println("Empresa agregada.");
                    break;
                case 2:
                    System.out.print("Documento: ");
                    String doc = sc.nextLine();
                    System.out.print("Nombre: ");
                    String nom = sc.nextLine();
                    System.out.print("Sueldo por hora: ");
                    double sueldo = sc.nextDouble();
                    sc.nextLine();
                    System.out.print("NIT de la empresa: ");
                    String nitEmp = sc.nextLine();
                    Empresa emp = opEmpresa.buscarEmpresaPorNit(nitEmp);
                    if (emp == null) {
                        System.out.println("Empresa no encontrada.");
                        break;
                    }
                    System.out.println("Tipo de empleado: 1. Desarrollador 2. GestorProyectos 3. Admin");
                    int tipo = sc.nextInt();
                    sc.nextLine();
                    Empleado empleado = null;
                    if (tipo == 1) {
                        empleado = new Desarrollador(doc, nom, sueldo, emp);
                    } else if (tipo == 2) {
                        System.out.print("Área: ");
                        String area = sc.nextLine();
                        empleado = new GestorProyectos(doc, nom, sueldo, emp, area);
                    } else if (tipo == 3) {
                        empleado = new Admin(doc, nom, sueldo, emp);
                    }
                    if (empleado != null) {
                        opEmpleado.agregarEmpleado(empleado);
                        System.out.println("Empleado agregado.");
                    }
                    break;
                case 3:
                    System.out.println("--- Empresas ---");
                    for (Empresa e : opEmpresa.listarEmpresas()) {
                        System.out.println(e.getNit() + " - " + e.getNombre());
                    }
                    break;
                case 4:
                    System.out.println("--- Empleados ---");
                    for (Empleado e : opEmpleado.listarEmpleados()) {
                        System.out.println(e);
                    }
                    break;
                case 5:
                    System.out.print("Documento: ");
                    String docBuscado = sc.nextLine();
                    Empleado empBuscado = opEmpleado.buscarEmpleadoPorDocumento(docBuscado);
                    if (empBuscado != null) {
                        System.out.println(empBuscado);
                    } else {
                        System.out.println("Empleado no encontrado.");
                    }
                    break;
                case 6:
                    System.out.print("Documento: ");
                    String docSueldo = sc.nextLine();
                    System.out.print("Horas trabajadas: ");
                    int horas = sc.nextInt();
                    sc.nextLine();
                    double sueldoCalc = opEmpleado.calcularSueldoEmpleado(docSueldo, horas);
                    System.out.println("Sueldo calculado: " + sueldoCalc);
                    break;
                case 7:
                    System.out.print("NIT de la empresa: ");
                    String nitContar = sc.nextLine();
                    Empresa empContar = opEmpresa.buscarEmpresaPorNit(nitContar);
                    if (empContar != null) {
                        int cantidad = opEmpleado.contarEmpleadosPorEmpresa(empContar);
                        System.out.println("Cantidad de empleados: " + cantidad);
                    } else {
                        System.out.println("Empresa no encontrada.");
                    }
                    break;
                case 0:
                    System.out.println("Saliendo...");
                    break;
                default:
                    System.out.println("Opción inválida.");
            }
        } while (opcion != 0);
        sc.close();
    }
}