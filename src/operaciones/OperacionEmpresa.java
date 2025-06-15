package operaciones;

import modelos.Empresa;
import java.util.ArrayList;
import java.util.List;

public class OperacionEmpresa implements IOperacionEmpresa {
    private List<Empresa> empresas = new ArrayList<>();

    @Override
    public void agregarEmpresa(Empresa empresa) {
        empresas.add(empresa);
    }

    @Override
    public Empresa buscarEmpresaPorNit(String nit) {
        for (Empresa e : empresas) {
            if (e.getNit().equals(nit)) {
                return e;
            }
        }
        return null;
    }

    @Override
    public List<Empresa> listarEmpresas() {
        return empresas;
    }
}