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
}
