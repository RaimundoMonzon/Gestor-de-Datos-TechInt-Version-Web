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
            model.addAttribute("Error: ", "La contraseña no coincide");
            return "edit/user";
        }

        switch (rol) {
            case ADMIN:
                Administrador admin = (Administrador) userService.getById(id);
                admin.setUsername(username);
                admin = (Administrador) userService.updatePassword(admin, password);
                admin.setFechaIngreso(fechaIngreso);
                admin.setRol(rol);
                userService.save(admin);
                break;
            case ASESOR:
                Asesor asesor = (Asesor) userService.getById(id);
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
        List<Pais> paises = paisService.getAll();
        model.addAttribute("paises", paises);

        if (paisID != null) {
            model.addAttribute("pais", paisService.getById(paisID));
        }

        return "edit/pais";
    }

    @PostMapping("/pais")
    public String editPais(@ModelAttribute Pais pais) {
        paisService.save(pais);
        return "edit";
    }

    @GetMapping("/area")
    public String editAreasForm(@RequestParam(required = false) Long areaID, Model model) {
        List<Area> areas = areaService.getAll();
        model.addAttribute("areas", areas);

        if (areaID != null) {
            model.addAttribute("area", areaService.getById(areaID));
        }

        return "edit/area";
    }

    @PostMapping("/area")
    public String editArea(@ModelAttribute Area area) {
        areaService.save(area);
        return "edit";
    }

    @GetMapping("/empresa")
    public String editEmpresaForm(@RequestParam(required = false) Long empresaID, Model model) {
        List<Empresa> empresas = empresaService.getAll();
        model.addAttribute("empresas", empresas);

        if (empresaID != null) {
            List<Area> areas = areaService.getAll();
            model.addAttribute("areas", areas);
            List<Pais> paises = paisService.getAll();
            model.addAttribute("paises", paises);
            model.addAttribute("empresa", empresaService.getById(empresaID));
        }

        return "edit/empresa";
    }

    @PostMapping("/empresa")
    public String editEmpresa(@ModelAttribute Empresa empresa) {
        empresaService.save(empresa);
        return "edit";
    }

}
