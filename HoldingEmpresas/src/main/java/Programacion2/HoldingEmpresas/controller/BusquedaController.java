package Programacion2.HoldingEmpresas.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.ui.Model;

import Programacion2.HoldingEmpresas.services.*;
import Programacion2.HoldingEmpresas.entities.Pais;
import Programacion2.HoldingEmpresas.entities.Area;
import Programacion2.HoldingEmpresas.entities.Contrato;
import Programacion2.HoldingEmpresas.entities.Empresa;
import Programacion2.HoldingEmpresas.entities.UserEntity;
import lombok.AllArgsConstructor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@AllArgsConstructor
public class BusquedaController {

    private final UserService userService;
    private final PaisService paisService;
    private final AreaService areaService;
    private final ContratoService contratoService;
    private final EmpresaService empresaService;

    @GetMapping("/busqueda")
    public String busqueda(
            @RequestParam(value = "entidad", required = false) String entidad,
            @RequestParam(value = "atributos", required = false) List<String> atributos,
            @RequestParam(value = "nombre", required = false) String nombre,
            @RequestParam(value = "id", required = false) Long id,
            Model model) {
        if (entidad != "" && entidad != null) {
            switch (entidad) {
                case "PAIS":
                    List<Pais> paises = paisService.filterPais(id);
                    model.addAttribute("paises", paises);
                    break;
                case "AREA":
                    List<Area> areas = areaService.filterAreas(id);
                        model.addAttribute("areas", areas);
                    break;
                case "CONTRATO":
                    List<Contrato> contratos = contratoService.filterContratos(id);
                    model.addAttribute("contratos", contratos);
                    break;
                case "EMPRESA":
                    List<Empresa> empresas = empresaService.filterEmpresas(nombre, id);
                    model.addAttribute("empresas", empresas);
                    break;
                default:
                    List<UserEntity> usuarios = userService.filterUsers(nombre, !entidad.equals("USERS") ? entidad : null, id);
                    model.addAttribute("usuarios", usuarios);
                    break;
            }
        } else if (entidad == "" && id != null) {

            List<Map<String, Object>> listaEntidades = new ArrayList<>();

            for (UserEntity user : userService.filterUsers(nombre, null, id)) {
                if (user != null) {
                    Map<String, Object> datosEntidad = new HashMap<>();
                    datosEntidad.put("id", user.getId());
                    datosEntidad.put("nombre", user.getUsername());
                    datosEntidad.put("tipo", user.getRol());
                    listaEntidades.add(datosEntidad);
                }
            }

            for (Empresa empresa : empresaService.filterEmpresas(nombre, id)) {
                if (empresa != null) {
                    Map<String, Object> datosEntidad = new HashMap<>();
                    datosEntidad.put("id", empresa.getId());
                    datosEntidad.put("nombre", empresa.getNombreEmpresa());
                    datosEntidad.put("tipo", "EMPRESA");
                    listaEntidades.add(datosEntidad);
                }

            }

            for (Pais pais : paisService.filterPais(id)) {
                if (pais != null) {
                    Map<String, Object> datosEntidad = new HashMap<>();
                    datosEntidad.put("id", pais.getId());
                    datosEntidad.put("nombre", pais.getNombrePais());
                    datosEntidad.put("tipo", "PAIS");
                    listaEntidades.add(datosEntidad);
                }

            }

            for (Area area : areaService.filterAreas(id)) {
                if (area != null) {
                    Map<String, Object> datosEntidad = new HashMap<>();
                    datosEntidad.put("id", area.getId());
                    datosEntidad.put("nombre", area.getNombreArea());
                    datosEntidad.put("tipo", "AREA");
                    listaEntidades.add(datosEntidad);
                }

            }

            for (Contrato contrato : contratoService.filterContratos(id)) {
                if (contrato != null) {
                    Map<String, Object> datosEntidad = new HashMap<>();
                    datosEntidad.put("id", contrato.getId());
                    datosEntidad.put("nombre",
                            contrato.getAsesor() + " Asesora a:" + contrato.getEmpresa().getNombreEmpresa());
                    datosEntidad.put("tipo", "CONTRATO");
                    listaEntidades.add(datosEntidad);
                }
            }
            model.addAttribute("listaEntidades", listaEntidades);
        }

        // Se los devuelvo para que mantengan persistencia

        model.addAttribute("atributos", atributos);
        model.addAttribute("entidad", entidad);
        model.addAttribute("username", nombre);
        model.addAttribute("id", id);
        return "busqueda";
    }
}
