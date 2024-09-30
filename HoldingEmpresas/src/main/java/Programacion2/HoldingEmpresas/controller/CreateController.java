package Programacion2.HoldingEmpresas.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import Programacion2.HoldingEmpresas.entities.Empresa;
import Programacion2.HoldingEmpresas.services.EmpresaService;
import lombok.AllArgsConstructor;

import org.springframework.ui.Model;
import java.util.List;

@Controller
@RequestMapping("/create")
@AllArgsConstructor
public class CreateController {

    private final EmpresaService empresaService;

    @GetMapping({"/", ""})
    public String homeCreate(Model model) {
        return "create";
    }

    @GetMapping("/user")
    public String userCreate(Model model) {
        List<Empresa> empresas = empresaService.getAll();
        model.addAttribute("empresas", empresas);
        return "create/user";
    }

}
