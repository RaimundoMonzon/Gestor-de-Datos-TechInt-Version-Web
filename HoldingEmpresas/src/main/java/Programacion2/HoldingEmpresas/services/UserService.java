package Programacion2.HoldingEmpresas.services;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import Programacion2.HoldingEmpresas.repositories.UserRepository;
import Programacion2.HoldingEmpresas.entities.Rol;
import Programacion2.HoldingEmpresas.entities.UserEntity;
import lombok.AllArgsConstructor;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService {
    
    private final UserRepository repositorio;
    private final PasswordEncoder encoder;

    public List<UserEntity> getAll() {
        return repositorio.findAll();
    }

    public UserEntity getById(Long id) {
        return repositorio.findById(id).orElse(null);
    }
}
