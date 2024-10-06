package Programacion2.HoldingEmpresas.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
import Programacion2.HoldingEmpresas.entities.Area;

public interface AreaRepository extends JpaRepository <Area, Long> {
    Optional<Area> findByNombreArea(String nombreArea);
}
