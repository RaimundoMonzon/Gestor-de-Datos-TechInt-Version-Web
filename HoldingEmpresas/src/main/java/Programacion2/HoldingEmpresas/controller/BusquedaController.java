package Programacion2.HoldingEmpresas.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import Programacion2.HoldingEmpresas.services.UserService;
import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
public class BusquedaController {
    private UserService userService;

    @GetMapping("/busqueda")
    public String busqueda() {
        return "/busqueda";
    }
}
