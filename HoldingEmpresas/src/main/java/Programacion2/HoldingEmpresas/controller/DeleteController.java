package Programacion2.HoldingEmpresas.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import Programacion2.HoldingEmpresas.services.AreaService;
import Programacion2.HoldingEmpresas.services.EmpresaService;
import Programacion2.HoldingEmpresas.services.ManagerService;
import Programacion2.HoldingEmpresas.services.PaisService;
import Programacion2.HoldingEmpresas.services.UserService;
import Programacion2.HoldingEmpresas.services.PopUpService;
import lombok.AllArgsConstructor;

@Controller
@RequestMapping("/delete")
@AllArgsConstructor
public class DeleteController {

    private final EmpresaService empresaService;
    private final AreaService areaService;
    private final UserService userService;
    private final PaisService paisService;
    private final ManagerService managerService;

    @PostMapping("/user")
    public String editUser(@RequestParam Long id, @RequestParam(required = false) Long newManagerID, @RequestParam String password, Model model) {

        if(userService.getLoggedUser().getId().equals(id)) {
            PopUpService.showCannotDeleteSelfPopUp(model);
            return "edit/user";
        }

        if (userService.checkPassword(password)) {
            if(userService.isVendedor(id)) {
                managerService.deleteManagerAndTransferSubordinates(id, newManagerID);
            }
            userService.delete(id);
            PopUpService.showSuccessPopUp(model);
            return "edit";
        }

        model.addAttribute("usuarios", userService.getAll());
        PopUpService.showPasswordErrorPopUp(model);
        return "edit/user";
    }

    @PostMapping("/pais")
    public String editPais(@RequestParam Long id, @RequestParam String password, Model model) {
        if (userService.checkPassword(password)) {
            paisService.delete(id);
            PopUpService.showSuccessPopUp(model);
            return "edit";
        }

        model.addAttribute("paises", paisService.getAll());
        PopUpService.showPasswordErrorPopUp(model);
        return "edit/pais";
    }

    @PostMapping("/area")
    public String editArea(@RequestParam Long id, @RequestParam String password, Model model) {
        if (userService.checkPassword(password)) {
            areaService.delete(id);
            PopUpService.showSuccessPopUp(model);
            return "edit";
        }

        model.addAttribute("areas", areaService.getAll());
        PopUpService.showPasswordErrorPopUp(model);
        return "edit/area";
    }

    @PostMapping("/empresa")
    public String editEmpresa(@RequestParam Long id, @RequestParam String password, Model model) {
        if (userService.checkPassword(password)) {
            empresaService.delete(id);
            PopUpService.showSuccessPopUp(model);
            return "edit";
        }

        model.addAttribute("empresas", empresaService.getAll());
        PopUpService.showPasswordErrorPopUp(model);
        return "edit/empresa";
    }

}
