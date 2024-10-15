package Programacion2.HoldingEmpresas.services;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import Programacion2.HoldingEmpresas.repositories.UserRepository;
import jakarta.servlet.http.HttpSession;
import Programacion2.HoldingEmpresas.entities.Administrador;
import Programacion2.HoldingEmpresas.entities.Rol;
import Programacion2.HoldingEmpresas.entities.UserEntity;
import Programacion2.HoldingEmpresas.entities.Vendedor;
import lombok.AllArgsConstructor;

import java.util.List;
import java.util.Optional;
import java.sql.Date;
import java.time.LocalDate;

@Service
@AllArgsConstructor
public class UserService {

    private final EmpresaService empresaService;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final HttpSession session;

    public List<UserEntity> getAll() {
        return userRepository.findAll();
    }

    public UserEntity getById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    public Optional<UserEntity> getByName(String username) {
        return userRepository.findByUsername(username);
    }

    public List<? extends UserEntity> getByRol(Rol rol) {
        switch (rol) {
            case ADMIN:
                return userRepository.findByRol(rol);
            case ASESOR:
                return userRepository.findByRol(rol);
            case VENDEDOR:
                return userRepository.findByRol(rol);
            default:
                throw new IllegalArgumentException("Rol no válido: " + rol);
        }
    }

    public List<UserEntity> filterUsers(String username, String rol, Long id) {
        if (id != null && rol != null && !rol.isEmpty()) {
            return userRepository.findByIdAndRol(id, Rol.valueOf(rol));
        }
        
        if (id != null) {
            return userRepository.findByIdOrUsernameContainingIgnoreCase(id, null);
        }

        if (username != null && !username.isEmpty() && rol != null && !rol.isEmpty()) {
            return userRepository.findByUsernameContainingIgnoreCaseAndRol(username, Rol.valueOf(rol));
        }

        if (rol != null && !rol.isEmpty()) {
            return userRepository.findByRol(Rol.valueOf(rol));
        }

        if (username != null && !username.isEmpty()) {
            return userRepository.findByUsernameContainingIgnoreCase(username);
        }

        return userRepository.findAll();
    }

    public long count() {
        return userRepository.count();
    }

    public Boolean isAnyUserRegistered() {
        return count() > 0;
    }

    public void registerFirtsUser(String username, String password) {
        UserEntity usuario = new Administrador();
        usuario.setUsername(username);
        usuario.setPassword(passwordEncoder.encode(password));
        usuario.setRol(Rol.ADMIN);
        usuario.setFechaIngreso(Date.valueOf(LocalDate.now()));
        userRepository.save(usuario);
    }

    public void save(UserEntity user) {
        userRepository.save(user);
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

    public void logout() {
        SecurityContextHolder.clearContext();
        session.invalidate();
    }

    public UserEntity updatePassword(UserEntity user, String password) {
        if (!password.isEmpty()){
            user.setPassword(passwordEncoder.encode(password));
        } else{
            user.setPassword(getById(user.getId()).getPassword()); // Si no se cambia la contraseña, se mantiene la anterior
        }
        return user;
    }

    public void registerSale(Vendedor ven, Double monto) {
        ven.setIngresosTotales(ven.getIngresosTotales() + monto);
        empresaService.registerSale(ven.getEmpresa(), monto);
        save(ven);
    }
}
