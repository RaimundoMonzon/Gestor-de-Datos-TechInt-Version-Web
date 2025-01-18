package Programacion2.HoldingEmpresas.repositories;

import Programacion2.HoldingEmpresas.entities.UserEntity;
import Programacion2.HoldingEmpresas.entities.Rol;

import java.util.Optional;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByUsername(String username);

    List<UserEntity> findByIdOrUsernameContainingIgnoreCase(Long id, String username);

    List<UserEntity> findByUsernameContainingIgnoreCase(String username);

    List<UserEntity> findByRol(Rol rol);

    List<UserEntity> findByUsernameContainingIgnoreCaseAndRol(String username, Rol rol);

    List<UserEntity> findByIdAndRol(Long id, Rol rol);

    @Query("SELECT u FROM UserEntity u WHERE u.username = :username")
    Optional<UserEntity> findByUsernameExact(@Param("username") String username);

}
