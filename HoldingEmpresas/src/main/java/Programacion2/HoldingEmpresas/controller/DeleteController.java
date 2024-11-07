package Programacion2.HoldingEmpresas.controller;

import java.sql.Date;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import Programacion2.HoldingEmpresas.entities.Administrador;
import Programacion2.HoldingEmpresas.entities.Area;
import Programacion2.HoldingEmpresas.entities.Asesor;
import Programacion2.HoldingEmpresas.entities.Empresa;
import Programacion2.HoldingEmpresas.entities.Pais;
import Programacion2.HoldingEmpresas.entities.Rol;
import Programacion2.HoldingEmpresas.entities.UserEntity;
import Programacion2.HoldingEmpresas.entities.Vendedor;
import Programacion2.HoldingEmpresas.services.AreaService;
import Programacion2.HoldingEmpresas.services.EmpresaService;
import Programacion2.HoldingEmpresas.services.PaisService;
import Programacion2.HoldingEmpresas.services.UserService;
import lombok.AllArgsConstructor;

@Controller
@RequestMapping("/delete")
@AllArgsConstructor
public class DeleteController {

    private final EmpresaService empresaService;
    private final AreaService areaService;
    private final UserService userService;
    private final PaisService paisService;


    @PostMapping("/user")
    public String editUser(@RequestParam Long id, @RequestParam String password, Model model) {
        if (userService.checkPassword(password)) {
            userService.delete(id);
            return "edit";
        }
        
        model.addAttribute("error", "La contraseña no coincide");
        return "redirect:/edit/user";
    }

    @PostMapping("/pais")
    public String editPais(@RequestParam Long id, @RequestParam String password, Model model) {
        if (userService.checkPassword(password)) {
            paisService.delete(id);
            return "edit";
        }
        
        model.addAttribute("error", "La contraseña no coincide");
        return "edit/pais";
    }

    @PostMapping("/area")
    public String editArea(@RequestParam Long id, @RequestParam String password, Model model) {
        if (userService.checkPassword(password)) {
            areaService.delete(id);
            return "edit";
        }
        
        model.addAttribute("error", "La contraseña no coincide");
        return "edit/area";
    }

    @PostMapping("/empresa")
    public String editEmpresa(@RequestParam Long id, @RequestParam String password, Model model) {
        if (userService.checkPassword(password)) {
            empresaService.delete(id);
            return "edit";
        }
        
        model.addAttribute("error", "La contraseña no coincide");
        return "edit/empresa";
    }

}
