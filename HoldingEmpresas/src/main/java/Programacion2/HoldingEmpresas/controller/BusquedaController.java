package Programacion2.HoldingEmpresas.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.ui.Model;

import Programacion2.HoldingEmpresas.services.AreaService;
import Programacion2.HoldingEmpresas.services.EmpresaService;
import Programacion2.HoldingEmpresas.services.PaisService;
import Programacion2.HoldingEmpresas.services.UserService;
import Programacion2.HoldingEmpresas.entities.Pais;
import Programacion2.HoldingEmpresas.entities.Area;
import Programacion2.HoldingEmpresas.entities.Empresa;
import Programacion2.HoldingEmpresas.entities.UserEntity;
import lombok.AllArgsConstructor;
import java.util.List;
import java.util.ArrayList;

@Controller
@AllArgsConstructor
public class BusquedaController {

    private final UserService userService;
    private final PaisService paisService;
    private final AreaService areaService;
    // private final ContratoService contratoService;
    private final EmpresaService empresaService;

    @GetMapping("/busqueda")
    public String busqueda(
            @RequestParam(value = "entidad", required = false) String entidad,
            @RequestParam(value = "atributos", required = false) List<String> atributos,
            @RequestParam(value = "nombre", required = false) String nombre,
            @RequestParam(value = "rol", required = false) String rol,
            @RequestParam(value = "id", required = false) Long id,
            Model model) {
        if (entidad != null) {
            switch (entidad) {
                case "PAIS":
                    List<Pais> paises = paisService.getAll();
                    model.addAttribute("paises", paises);
                    break;
                case "AREA":
                    List<Area> areas = areaService.getAll();
                    model.addAttribute("areas", areas);
                    break;
                case "CONTRATO":
                    // Finiquitar
                    break;
                case "EMPRESA":
                    List<Empresa> empresas = empresaService.getAll();
                    model.addAttribute("empresas", empresas);
                    break;
                default:
                    List<UserEntity> usuarios = userService.filterUsers(nombre, rol, id);
                    model.addAttribute("usuarios", usuarios);
                    break;
            }
            model.addAttribute("atributos", atributos);
        } else {
            List<UserEntity> usuarios = userService.filterUsers(nombre, rol, id);
            atributos = new ArrayList<>();
            atributos.add("id");
            atributos.add("username");
            atributos.add("fechaIngreso");
            atributos.add("rol");
            atributos.add("areasOperadas");
            atributos.add("titulacion");
            atributos.add("empresa");
            atributos.add("manager");
            atributos.add("subContratados");
            atributos.add("ingresos");
            entidad = "USERS";
            model.addAttribute("atributos", atributos);
            model.addAttribute("usuarios", usuarios);
            model.addAttribute("entidad", entidad);
        }

        // Se los dedvuevlo para que mantengan persistencia
        
        model.addAttribute("username", nombre);
        model.addAttribute("rol", rol);
        model.addAttribute("id", id);
        return "busqueda";
    }
}
