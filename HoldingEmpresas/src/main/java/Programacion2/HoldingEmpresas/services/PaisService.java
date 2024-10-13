package Programacion2.HoldingEmpresas.services;

import org.springframework.stereotype.Service;

import Programacion2.HoldingEmpresas.repositories.PaisRepository;
import Programacion2.HoldingEmpresas.entities.Pais;
import java.util.List;
import java.util.ArrayList;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class PaisService {
    
    private final PaisRepository paisRepository;

    public List<Pais> getAll() {
        return paisRepository.findAll();
    }

    public Pais getById(Long id) {
        return paisRepository.findById(id).orElse(null);
    }

    public List<Pais> filterPais(Long id) {
        if(id != null) {
            List<Pais> paises = new ArrayList<Pais>();
            paises.add(paisRepository.findById(id).orElse(null));
            return paises;
        }
        return paisRepository.findAll();
    }

    public void save(Pais pais) {
        paisRepository.save(pais);
    }
}
