package Programacion2.HoldingEmpresas.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import Programacion2.HoldingEmpresas.entities.Contrato;
import Programacion2.HoldingEmpresas.entities.Empresa;
import Programacion2.HoldingEmpresas.entities.Area;
import Programacion2.HoldingEmpresas.entities.Asesor;
import Programacion2.HoldingEmpresas.services.ContratoService;
import Programacion2.HoldingEmpresas.services.EmpresaService;
import Programacion2.HoldingEmpresas.services.UserService;
import lombok.AllArgsConstructor;

import java.util.List;
import java.util.ArrayList;

@Controller
@AllArgsConstructor
public class ContractController {

    private final UserService userService;
    private final EmpresaService empresaService;
    private final ContratoService contratoService;

    @GetMapping("/sign-contract")
    public String createContractForm(@RequestParam(required = false) Long empresaID, Model model) {
        model.addAttribute("empresas", empresaService.getAll());

        if (empresaID != null) {
            Asesor asesor = (Asesor) userService.getLoggedUser();
            List<Area> areasComunes = new ArrayList<>();
            Empresa empresa = empresaService.getById(empresaID);
            List<Area> areasEmpresa = empresa.getAreasOperadas();
            asesor.getAreasOperadas().forEach(area -> {
                if (areasEmpresa.contains(area)) {
                    areasComunes.add(area);
                }
            });
            if (areasComunes.isEmpty()) {
                model.addAttribute("error", "No hay areas en comun.");
            } else {
                Contrato contrato = new Contrato();
                contrato.setAsesor(asesor);
                contrato.setEmpresa(empresa);
                model.addAttribute("contrato", contrato);
                model.addAttribute("areas", areasComunes);
            }
            model.addAttribute("empresaID", empresaID);
        }
        return "sign-contract";
    }

    @PostMapping("/sign-contract")
    public String createContract(@ModelAttribute Contrato contrato, Model model) {
        contratoService.save(contrato);
        return "redirect:/home";
    }

}
