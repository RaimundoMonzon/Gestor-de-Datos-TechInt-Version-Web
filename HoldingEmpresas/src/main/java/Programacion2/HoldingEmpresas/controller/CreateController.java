package Programacion2.HoldingEmpresas.controller;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;

import Programacion2.HoldingEmpresas.entities.Administrador;
import Programacion2.HoldingEmpresas.entities.Area;
import Programacion2.HoldingEmpresas.entities.Asesor;
import Programacion2.HoldingEmpresas.entities.Empresa;
import Programacion2.HoldingEmpresas.entities.Pais;
import Programacion2.HoldingEmpresas.entities.Rol;
import Programacion2.HoldingEmpresas.entities.Vendedor;
import Programacion2.HoldingEmpresas.repositories.UserRepository;
import Programacion2.HoldingEmpresas.services.AreaService;
import Programacion2.HoldingEmpresas.services.EmpresaService;
import Programacion2.HoldingEmpresas.services.PaisService;
import Programacion2.HoldingEmpresas.services.UserService;
import lombok.AllArgsConstructor;

import java.util.List;
import java.sql.Date;

@Controller
@RequestMapping("/create")
@AllArgsConstructor
public class CreateController {

    private final EmpresaService empresaService;
    private final AreaService areaService;
    private final PaisService paisService;
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    @GetMapping({ "/", "" })
    public String homeCreate(Model model) {
        return "create";
    }

    @GetMapping("/user")
    public String createUserForm(Model model) {
        List<Empresa> empresas = empresaService.getAll();
        List<Area> areas = areaService.getAll();
        model.addAttribute("empresas", empresas);
        model.addAttribute("areas", areas);
        if (userService.getLoggedUserRol() == "VENDEDOR") {
            model.addAttribute("vendedor", userService.getLoggedUser());
        }

        return "create/user";
    }

    @PostMapping("/user")
    public String createUser(
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
            return "create/user";
        }
        
        if (userRepository.findByUsername(username).isPresent()) {
            model.addAttribute("Error: ", "El nombre de usuario ya esta tomado.");
            return "create/user";
        }
        switch (rol) {
            case ADMIN:
                Administrador admin = new Administrador();
                admin.setUsername(username);
                admin.setPassword(passwordEncoder.encode(password));
                admin.setFechaIngreso(fechaIngreso);
                admin.setRol(rol);
                userService.save(admin);
                break;
            case ASESOR:
                Asesor asesor = new Asesor();
                asesor.setUsername(username);
                asesor.setPassword(passwordEncoder.encode(password));
                asesor.setFechaIngreso(fechaIngreso);
                asesor.setRol(rol);
                asesor.setAreasOperadas(areasOperadas);
                asesor.setTitulacion(titulacion);
                userService.save(asesor);
                break;
            case VENDEDOR:
                Vendedor vendedor = new Vendedor();
                vendedor.setUsername(username);
                vendedor.setPassword(passwordEncoder.encode(password));
                vendedor.setFechaIngreso(fechaIngreso);
                vendedor.setRol(rol);
                vendedor.setEmpresa(empresa);
                vendedor.setIngresosTotales( 0.00);
                userService.save(vendedor);
                if (userService.getLoggedUserRol() == "VENDEDOR") {
                    Vendedor manager = (Vendedor) userService.getLoggedUser();
                    manager.getSubContratados().add(vendedor);
                    userService.save(manager);
                }
                break;
            default:
                throw new IllegalArgumentException("Invalid Rol");
        }
        return "create";
    }

    @GetMapping("/pais")
    public String paisCreate() {
        return "create/pais";
    }

    @PostMapping("/pais")
    public String createPais(
            @RequestParam String nombre,
            @RequestParam String capital,
            @RequestParam Long poblacion,
            @RequestParam Float pbi) {
        Pais pais = new Pais();
        pais.setNombrePais(nombre);
        pais.setCapital(capital);
        pais.setPoblacion(poblacion);
        pais.setPbi(pbi);
        paisService.save(pais);
        return "create";
    }

    @GetMapping("/area")
    public String areaCreate() {
        return "create/area";
    }

    @PostMapping("/area")
    public String createArea(
            @RequestParam String nombre,
            @RequestParam String descripcion) {
        Area area = new Area();
        area.setNombreArea(nombre);
        area.setDescripcion(descripcion);
        areaService.save(area);
        return "create";
    }

    @GetMapping("/empresa")
    public String empresaCreate(Model model) {
        List<Area> areas = areaService.getAll();
        List<Pais> paises = paisService.getAll();
        model.addAttribute("areas", areas);
        model.addAttribute("paises", paises);
        return "create/empresa";
    }

    @PostMapping("/empresa")
    public String createEmpresa(
            @RequestParam String nombre,
            @RequestParam String sede,
            @RequestParam List<Pais> paisesOperados,
            @RequestParam List<Area> areasOperadas,
            @RequestParam Date fechaIngreso,
            @RequestParam Float fta) {
        Empresa empresa = new Empresa();
        empresa.setNombreEmpresa(nombre);
        empresa.setSede(sede);
        empresa.setPaisesOperados(paisesOperados);
        empresa.setAreasOperadas(areasOperadas);
        empresa.setFechaIngreso(fechaIngreso);
        empresa.setFta(fta);
        empresaService.save(empresa);
        return "create";
    }

}
