package Programacion2.HoldingEmpresas.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
import Programacion2.HoldingEmpresas.entities.Pais;

public interface PaisRepository extends JpaRepository <Pais, Long> {
    Optional<Pais> findByNombrePais(String nombrePais);
}
