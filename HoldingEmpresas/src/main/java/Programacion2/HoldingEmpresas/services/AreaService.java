package Programacion2.HoldingEmpresas.services;

import org.springframework.stereotype.Service;
import Programacion2.HoldingEmpresas.repositories.AreaRepository;
import Programacion2.HoldingEmpresas.entities.Area;

import java.util.List;
import java.util.ArrayList;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AreaService {
    
    private final AreaRepository areaRepository;

    public List<Area> getAll() {
        return areaRepository.findAll();
    }

    public Area getById(Long id) {
        return areaRepository.findById(id).orElse(null);
    }

    public List<Area> filterAreas(Long id) {
        if(id != null) {
            List<Area> areas = new ArrayList<Area>();
            areaRepository.findById(id).ifPresent(areas::add);
            return areas;
        }
        return areaRepository.findAll();
    }

    public void save(Area area) {
        areaRepository.save(area);
    }

    public void delete(Long id) {
        areaRepository.deleteById(id);
    }

    public boolean isNameTaken(String nombreArea) {
        return areaRepository.findByNombreArea(nombreArea).isPresent();
    }
}
