package Programacion2.HoldingEmpresas.services;

import java.util.List;
import org.springframework.stereotype.Service;

import Programacion2.HoldingEmpresas.entities.Empresa;
import Programacion2.HoldingEmpresas.repositories.EmpresaRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class EmpresaService {
    
    private final EmpresaRepository empresaReposiroty;

    public List<Empresa> getAll() {
        return empresaReposiroty.findAll();
    }

    public List<Empresa> filterEmpresas(String nombre, Long id) {
        if (id != null) {
            return empresaReposiroty.findByIdOrNombreEmpresaContainingIgnoreCase(id, null);
        }

        if (nombre != null && !nombre.isEmpty()) {
            return empresaReposiroty.findByNombreEmpresaContainingIgnoreCase(nombre);
        }

        return empresaReposiroty.findAll();
    }

    public Empresa getById(Long id) {
        return empresaReposiroty.findById(id).orElse(null);
    }

    public void save(Empresa empresa) {
        empresaReposiroty.save(empresa);
    }

    public void registerSale(Empresa emp, Double monto) {
        emp.setFta(emp.getFta() + monto);
        save(emp);
    }

    public void delete(Long id) {
        empresaReposiroty.deleteById(id);
    }
}
