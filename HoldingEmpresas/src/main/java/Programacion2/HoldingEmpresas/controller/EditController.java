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
import Programacion2.HoldingEmpresas.services.PopUpService;
import Programacion2.HoldingEmpresas.services.UserService;
import lombok.AllArgsConstructor;

@Controller
@RequestMapping("/edit")
@AllArgsConstructor
public class EditController {

    private final EmpresaService empresaService;
    private final AreaService areaService;
    private final UserService userService;
    private final PaisService paisService;

    @GetMapping({ "/", "" })
    public String homeEdit(Model model) {
        return "edit";
    }

    @GetMapping("/user")
    public String editUserForm(@RequestParam(required = false) Long usuarioID, Model model) {

        List<UserEntity> usuarios = userService.getAll();
        model.addAttribute("usuarios", usuarios);

        if (usuarioID != null) {
            List<Empresa> empresas = empresaService.getAll();
            List<Area> areas = areaService.getAll();
            model.addAttribute("empresas", empresas);
            model.addAttribute("areas", areas);
            UserEntity selectedUser = userService.getById(usuarioID);
            model.addAttribute("usuario", selectedUser); // Add the selected user
            if ("VENDEDOR".equals(userService.getLoggedUserRol())) {
                model.addAttribute("vendedor", userService.getLoggedUser());
            }
        }
        model.addAttribute("usuarioID", usuarioID);
        return "edit/user";
    }

    @PostMapping("/user")
    public String editUser(
            @RequestParam Long id,
            @RequestParam String username,
            @RequestParam String password,
            @RequestParam("passwordConfirmation") String passwordConfirmation,
            @RequestParam Date fechaIngreso,
            @RequestParam Rol rol,
            @RequestParam(required = false) Empresa empresa,
            @RequestParam(required = false) List<Area> areasOperadas,
            @RequestParam(required = false) String titulacion,
            Model model) {

        if (!password.equals(passwordConfirmation)) {
            PopUpService.showPasswordMismatchPopUp(model);
            List<UserEntity> usuarios = userService.getAll();
            model.addAttribute("usuarios", usuarios);
            return "edit/user";
        }

        switch (rol) {
            case ADMIN:
                Administrador admin = (Administrador) userService.getById(id);
                if (handleUsernameCheck(model, username, admin)) {
                    return "edit/user";
                }
                admin.setUsername(username);
                admin = (Administrador) userService.updatePassword(admin, password);
                admin.setFechaIngreso(fechaIngreso);
                admin.setRol(rol);
                userService.save(admin);
                break;
            case ASESOR:
                Asesor asesor = (Asesor) userService.getById(id);
                if (handleUsernameCheck(model, username, asesor)) {
                    return "edit/user";
                }
                asesor.setUsername(username);
                asesor = (Asesor) userService.updatePassword(asesor, password);
                asesor.setFechaIngreso(fechaIngreso);
                asesor.setRol(rol);
                asesor.setAreasOperadas(areasOperadas);
                asesor.setTitulacion(titulacion);
                userService.save(asesor);
                break;
            case VENDEDOR:
                Vendedor vendedor = (Vendedor) userService.getById(id);
                if (handleUsernameCheck(model, username, vendedor)) {
                    return "edit/user";
                }
                vendedor.setUsername(username);
                vendedor = (Vendedor) userService.updatePassword(vendedor, password);
                vendedor.setFechaIngreso(fechaIngreso);
                vendedor.setEmpresa(empresa);
                userService.save(vendedor);
                break;
            default:
                throw new IllegalArgumentException("Invalid Rol");
        }
        return "edit";
    }

    @GetMapping("/pais")
    public String editPaisForm(@RequestParam(required = false) Long paisID, Model model) {
        model.addAttribute("paises", paisService.getAll());

        if (paisID != null) {
            model.addAttribute("pais", paisService.getById(paisID));
        }
        model.addAttribute("paisID", paisID);
        return "edit/pais";
    }

    @PostMapping("/pais")
    public String editPais(@ModelAttribute Pais pais, Model model) {
        Pais p = paisService.getById(pais.getId());
        if(paisService.isNameTaken(pais.getNombrePais()) && !p.getNombrePais().equals(pais.getNombrePais())) {
            PopUpService.showNameAlreadyTakenPopUp(model);
            List<Pais> paises = paisService.getAll();
            model.addAttribute("paises", paises);
            return "edit/pais";
        }
        paisService.save(pais);
        return "edit";
    }

    @GetMapping("/area")
    public String editAreasForm(@RequestParam(required = false) Long areaID, Model model) {
        model.addAttribute("areas", areaService.getAll());

        if (areaID != null) {
            model.addAttribute("area", areaService.getById(areaID));
        }
        model.addAttribute("areaID", areaID);
        return "edit/area";
    }

    @PostMapping("/area")
    public String editArea(@ModelAttribute Area area, Model model) {
        Area a = areaService.getById(area.getId());
        if(areaService.isNameTaken(area.getNombreArea()) && !a.getNombreArea().equals(area.getNombreArea())) {
            PopUpService.showNameAlreadyTakenPopUp(model);
            List<Area> areas = areaService.getAll();
            model.addAttribute("areas", areas);
            return "edit/area";
        }
        areaService.save(area);
        return "edit";
    }

    @GetMapping("/empresa")
    public String editEmpresaForm(@RequestParam(required = false) Long empresaID, Model model) {
        model.addAttribute("empresas", empresaService.getAll());

        if (empresaID != null) {
            List<Area> areas = areaService.getAll();
            model.addAttribute("areas", areas);
            List<Pais> paises = paisService.getAll();
            model.addAttribute("paises", paises);
            model.addAttribute("empresa", empresaService.getById(empresaID));
        }
        model.addAttribute("empresaID", empresaID);
        return "edit/empresa";
    }

    @PostMapping("/empresa")
    public String editEmpresa(@ModelAttribute Empresa empresa, Model model) {
        Empresa e = empresaService.getById(empresa.getId());
        if(empresaService.isNameTaken(empresa.getNombreEmpresa()) && !e.getNombreEmpresa().equals(empresa.getNombreEmpresa())) {
            PopUpService.showNameAlreadyTakenPopUp(model);
            List<Empresa> empresas = empresaService.getAll();
            model.addAttribute("empresas", empresas);
            return "edit/empresa";
        }
        empresaService.save(empresa);
        return "edit";
    }

    private boolean handleUsernameCheck(Model model, String username, UserEntity user) {
        if (userService.isUsernameTaken(username) && !username.equals(user.getUsername())) {
            PopUpService.showUsernameAlreadyTakenPopUp(model);
            List<UserEntity> usuarios = userService.getAll();
            model.addAttribute("usuarios", usuarios);
            return true;
        }
        return false;
    }

}
