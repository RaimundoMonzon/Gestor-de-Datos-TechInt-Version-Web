package Programacion2.HoldingEmpresas.repositories;

import Programacion2.HoldingEmpresas.entities.Empresa;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmpresaRepository extends JpaRepository <Empresa, Long> {
    Optional<Empresa> findByNombreEmpresa(String nombreEmpresa);
}
