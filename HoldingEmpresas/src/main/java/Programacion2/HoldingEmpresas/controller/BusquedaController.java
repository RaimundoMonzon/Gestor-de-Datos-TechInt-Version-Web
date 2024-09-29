package Programacion2.HoldingEmpresas.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.ui.Model;
import Programacion2.HoldingEmpresas.services.UserService;
import Programacion2.HoldingEmpresas.entities.UserEntity;
import lombok.AllArgsConstructor;
import java.util.List;


@Controller
@AllArgsConstructor
public class BusquedaController {
    private UserService userService;

    @GetMapping("/busqueda")
    public String busqueda(
        @RequestParam(value = "username", required = false) String username,
        @RequestParam(value = "rol", required = false) String rol,
        @RequestParam(value = "id", required = false) Long id,
        Model model) {
        List<UserEntity> usuarios = userService.filterUsers(username, rol, id);
        model.addAttribute("usuarios", usuarios);
        model.addAttribute("username", username);
        model.addAttribute("rol", rol);
        model.addAttribute("id", id);
        return "busqueda";
    }
}
