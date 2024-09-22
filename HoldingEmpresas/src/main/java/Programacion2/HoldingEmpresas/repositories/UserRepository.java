package Programacion2.HoldingEmpresas.repositories;

import Programacion2.HoldingEmpresas.entities.UserEntity;

import java.util.Optional;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository <UserEntity, Long>{
    Optional<UserEntity> find_by_username(String username);

    List<UserEntity> find_by_id_or_username(Long id, String username);

    List<UserEntity> find_by_username_containing(String username);

}
