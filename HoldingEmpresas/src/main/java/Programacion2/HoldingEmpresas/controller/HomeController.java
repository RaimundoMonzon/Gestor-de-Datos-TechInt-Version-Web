package Programacion2.HoldingEmpresas.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import Programacion2.HoldingEmpresas.services.UserService;
import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
public class HomeController {
    
    private UserService userService;

    @GetMapping({"/", ""})
    public String redirectHome() {
        return "redirect:/home";
    }

    @GetMapping("/home")
    public String defaultHome() {
        if(userService.isSomeoneAuthenticated()) {
            return "home";
        }
        return "redirect:/login";
    }
}
