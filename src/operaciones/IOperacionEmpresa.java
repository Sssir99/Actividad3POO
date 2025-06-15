package operaciones;

import modelos.Empresa;
import java.util.List;

public interface IOperacionEmpresa {
    void agregarEmpresa(Empresa empresa);

    Empresa buscarEmpresaPorNit(String nit);

    List<Empresa> listarEmpresas();
}