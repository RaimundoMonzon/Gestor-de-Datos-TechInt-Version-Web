package Programacion2.HoldingEmpresas.services;

import org.springframework.stereotype.Service;
import Programacion2.HoldingEmpresas.repositories.ContratoRepository;
import Programacion2.HoldingEmpresas.entities.Contrato;
import lombok.AllArgsConstructor;
import java.util.List;
import java.util.ArrayList;

@Service
@AllArgsConstructor
public class ContratoService {

    private final ContratoRepository contratoRepository;

    public List<Contrato> getAll() {
        return contratoRepository.findAll();
    }

    public Contrato getById(Long id) {
        return contratoRepository.findById(id).orElse(null);
    }

    public List<Contrato> filterContratos(Long id) {
        if(id != null) {
            List<Contrato> contratos = new ArrayList<Contrato>();
            contratos.add(contratoRepository.findById(id).orElse(null));
            return contratos;
        }
        return contratoRepository.findAll();
    }

    public void save(Contrato contrato) {
        contratoRepository.save(contrato);
    }
}
