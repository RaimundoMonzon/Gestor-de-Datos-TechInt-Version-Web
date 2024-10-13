package Programacion2.HoldingEmpresas.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import Programacion2.HoldingEmpresas.entities.Contrato;

public interface ContratoRepository extends JpaRepository <Contrato, Long> {
    
}
