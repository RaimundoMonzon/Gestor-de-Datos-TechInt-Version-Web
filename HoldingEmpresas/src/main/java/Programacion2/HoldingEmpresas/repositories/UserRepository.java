package Programacion2.HoldingEmpresas.repositories;

import Programacion2.HoldingEmpresas.entities.UserEntity;

import java.util.Optional;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository <UserEntity, Long>{
    Optional<UserEntity> findByUsername(String username);

    List<UserEntity> findByIdOrUsernameContainingIgnoreCase(Long id, String username);

    List<UserEntity> findByUsernameContainingIgnoreCase(String username);

}
