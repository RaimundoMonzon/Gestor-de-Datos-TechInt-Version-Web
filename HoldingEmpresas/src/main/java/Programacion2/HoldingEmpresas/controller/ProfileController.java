package Programacion2.HoldingEmpresas.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import Programacion2.HoldingEmpresas.entities.Asesor;
import Programacion2.HoldingEmpresas.entities.Rol;
import Programacion2.HoldingEmpresas.entities.UserEntity;
import Programacion2.HoldingEmpresas.entities.Vendedor;
import Programacion2.HoldingEmpresas.services.UserService;
import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
public class ProfileController {

    private final UserService userService;

    @GetMapping("/profile")
    public String userProfile(Model model) {
        
        UserEntity user = userService.getLoggedUser();
        if(user.getRol() == Rol.VENDEDOR) {
            Vendedor vendedor = (Vendedor) user;
            model.addAttribute("user", vendedor);
            if(vendedor.getManager() != null) {
                Vendedor manager = (Vendedor) vendedor.getManager();
                model.addAttribute("manager", manager);
            }
        }
        else if(user.getRol() == Rol.ASESOR) {
            Asesor asesor = (Asesor) user;
            model.addAttribute("user", asesor);
        }
        else {
            model.addAttribute("user", user);
        }
        return "profile";
    }
}
