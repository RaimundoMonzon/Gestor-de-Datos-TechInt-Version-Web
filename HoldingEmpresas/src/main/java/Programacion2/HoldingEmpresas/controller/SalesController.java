package Programacion2.HoldingEmpresas.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import Programacion2.HoldingEmpresas.entities.Vendedor;
import Programacion2.HoldingEmpresas.services.UserService;
import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
public class SalesController {

    private final UserService userService;

    @GetMapping("/register-sale")
    public String salesHome(Model model) {
        return "register-sale";
    }

    @PostMapping("/register-sale")
    public String editArea(@RequestParam Double monto) {
        userService.registerSale((Vendedor) userService.getLoggedUser(), monto);
        return "register-sale";
    }

}
