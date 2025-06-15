package modelos;

public class Admin extends Empleado {
    public Admin() {
        super();
    }

    public Admin(String documento, String nombre, double sueldoHora, Empresa empresa) {
        super(documento, nombre, sueldoHora, empresa);
    }

    @Override
    public String getTipoEmpleado() {
        return "Admin";
    }
}