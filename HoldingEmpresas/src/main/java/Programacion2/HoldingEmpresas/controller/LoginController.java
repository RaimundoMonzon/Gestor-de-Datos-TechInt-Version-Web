package Programacion2.HoldingEmpresas.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import Programacion2.HoldingEmpresas.repositories.UserRepository;
import Programacion2.HoldingEmpresas.services.PopUpService;
import Programacion2.HoldingEmpresas.services.UserService;
import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
public class LoginController {

    private final UserService userService;
    private final UserRepository userRepository;

    @GetMapping("/login")
    public String showLoginForm(@RequestParam(required = false) String error, Model model) {
        if (!userService.isAnyUserRegistered()) {
            return "redirect:/register";
        }
        if (error != null) {
            PopUpService.showCredentialErrorPopUp(model);
        }
        return "login";
    }

    @GetMapping("/logout")
    public String logout() {
        userService.logout();
        return "logout-success";
    }

    @GetMapping("/register")
    public String showRegisterForm() {
        if (userService.isAnyUserRegistered()) {
            return "redirect:/login";
        }
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(@RequestParam String username, @RequestParam String password,
            @RequestParam String passwordConfirmation, Model model) {
        if (!password.equals(passwordConfirmation)) {
            model.addAttribute("error", "Las contraseñas no coinciden");
            return "register";
        }

        if (userRepository.findByUsername(username).isPresent()) {
            model.addAttribute("error: ", "El nombre de usuario ya esta tomado.");
            return "register";
        }

        userService.registerFirtsUser(username, password);
        return "redirect:/login";
    }
}