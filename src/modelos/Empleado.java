package modelos;

public abstract class Empleado {
    private String documento;
    private String nombre;
    private double sueldoHora;
    private Empresa empresa;

    public Empleado() {
    }

    public Empleado(String documento, String nombre, double sueldoHora, Empresa empresa) {
        this.documento = documento;
        this.nombre = nombre;
        this.sueldoHora = sueldoHora;
        this.empresa = empresa;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getSueldoHora() {
        return sueldoHora;
    }

    public void setSueldoHora(double sueldoHora) {
        this.sueldoHora = sueldoHora;
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    public abstract String getTipoEmpleado();

    @Override
    public String toString() {
        return "Empleado{" +
                "documento='" + documento + '\'' +
                ", nombre='" + nombre + '\'' +
                ", sueldoHora=" + sueldoHora +
                ", empresa=" + (empresa != null ? empresa.getNombre() : "Sin empresa") +
                '}';
    }
}