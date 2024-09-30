package Programacion2.HoldingEmpresas.services;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import Programacion2.HoldingEmpresas.repositories.UserRepository;
import Programacion2.HoldingEmpresas.entities.Administrador;
import Programacion2.HoldingEmpresas.entities.Rol;
import Programacion2.HoldingEmpresas.entities.UserEntity;
import lombok.AllArgsConstructor;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService {
    
    private final UserRepository repositorio;
    private final PasswordEncoder passwordEncoder;

    public List<UserEntity> getAll() {
        return repositorio.findAll();
    }

    public UserEntity getById(Long id) {
        return repositorio.findById(id).orElse(null);
    }

    public Optional<UserEntity> getByName(String username) {
        return repositorio.findByUsername(username);
    }

    public List<UserEntity> filterUsers(String username, String rol, Long id) {
        if(id != null) {
            return repositorio.findByIdOrUsernameContainingIgnoreCase(id, null);
        }

        if(username != null && !username.isEmpty() && rol != null && !rol.isEmpty()) {
            return repositorio.findByUsernameContainingIgnoreCaseAndRol(username, Rol.valueOf(rol));
        }

        if(rol != null&& !rol.isEmpty()) {
            return repositorio.findByRol(Rol.valueOf(rol));
        }

        if(username != null && !username.isEmpty()) {
            return repositorio.findByUsernameContainingIgnoreCase(username);
        }

        return repositorio.findAll();
    }

    public long count() {
        return repositorio.count();
    }
    public Boolean isAnyUserRegistered() {
        return count() > 0;
    }

    public void registerFirtsUser(String username, String password) {
        UserEntity usuario = new Administrador();
        usuario.setUsername(username);
        usuario.setPassword(passwordEncoder.encode(password));
        usuario.setRol(Rol.ADMIN);
        repositorio.save(usuario);
    }

    public UserEntity getLoggedUser() {
        return (UserEntity) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    public String getLoggedUserRol() {
        return getLoggedUser().getRol().name();
    }

    public Boolean isSomeoneAuthenticated() {
        var user = SecurityContextHolder.getContext().getAuthentication();
        return user != null && user.isAuthenticated() && !user.getPrincipal().equals("anonymousUser");
    }
}
