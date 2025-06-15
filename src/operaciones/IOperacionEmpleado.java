package operaciones;

import modelos.Empleado;
import modelos.Empresa;
import java.util.List;

public interface IOperacionEmpleado {
    void agregarEmpleado(Empleado empleado);

    Empleado buscarEmpleadoPorDocumento(String documento);

    List<Empleado> listarEmpleados();

    double calcularSueldoEmpleado(String documento, int horasTrabajadas);

    int contarEmpleadosPorEmpresa(Empresa empresa);
}