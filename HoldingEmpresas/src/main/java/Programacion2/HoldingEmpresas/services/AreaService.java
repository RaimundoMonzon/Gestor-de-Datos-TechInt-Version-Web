package Programacion2.HoldingEmpresas.services;

import org.springframework.stereotype.Service;

import Programacion2.HoldingEmpresas.repositories.AreaRepository;
import Programacion2.HoldingEmpresas.entities.Area;
import java.util.List;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AreaService {
    
    private final AreaRepository areaRepository;

    public List<Area> getAll() {
        return areaRepository.findAll();
    }

    public void save(Area area) {
        areaRepository.save(area);
    }
}
