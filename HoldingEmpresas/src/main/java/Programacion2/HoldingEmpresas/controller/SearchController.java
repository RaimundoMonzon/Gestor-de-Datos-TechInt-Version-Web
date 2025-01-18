package Programacion2.HoldingEmpresas.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.ui.Model;

import Programacion2.HoldingEmpresas.services.*;
import lombok.AllArgsConstructor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@AllArgsConstructor
public class SearchController {

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
            @RequestParam(value = "exactMatch", required = false) Boolean exactMatch,
            Model model) {

        if (exactMatch == null) {exactMatch = false;}

        Map<String, List<String>> entityHeaders = Map.of(
            "USERS", List.of("ID", "Nombre", "Rol", "Titulación", "Áreas Operadas", "Manager", "Ingresos", "Empresa", "Subcontratados", "Fecha de Ingreso"),
            "ADMIN" , List.of("ID", "Nombre", "Rol", "Fecha de Ingreso"),
            "ASESOR", List.of("ID", "Nombre", "Rol", "Titulación", "Áreas Operadas", "Fecha de Ingreso"),
            "VENDEDOR", List.of("ID", "Nombre", "Rol", "Manager", "Ingresos", "Empresa", "Subcontratados", "Fecha de Ingreso"),
            "PAIS", List.of("ID", "Nombre", "Capital", "Poblacion", "PBI"),
            "AREA", List.of("ID", "Nombre", "Descripcion"),
            "CONTRATO", List.of("ID", "Asesor", "Empresa", "Fecha de Celebracion", "Fecha de Caducidad", "Areas Asesoradas"),
            "EMPRESA", List.of("ID", "Nombre de Empresa", "Sede", "Facturacion Anual", "Paises Operados", "Areas Operadas", "Vendedores Contratados", "Asesores Contratados", "Fecha de Ingreso"),
            "ENTIDADES", List.of("ID", "Nombre", "Entidad")
        );

        if (entidad != null && entityHeaders.containsKey(entidad)) {
            model.addAttribute("headers", entityHeaders.get(entidad));
        } else if (atributos != null) {
            model.addAttribute("headers", atributos);
        }

        if (entidad != "" && entidad != null) {
            switch (entidad) {
                case "PAIS":
                    model.addAttribute("paises", paisService.filterPais(id));
                    break;
                case "AREA":
                    model.addAttribute("areas", areaService.filterAreas(id));
                    break;
                case "CONTRATO":
                    model.addAttribute("contratos", contratoService.filterContratos(id));
                    break;
                case "EMPRESA":
                    model.addAttribute("empresas", empresaService.filterEmpresas(nombre, id));
                    break;
                default:
                    model.addAttribute("usuarios", userService.filterUsers(nombre, !entidad.equals("USERS") ? entidad : null, id, exactMatch));
                    break;
            }
        } else if (entidad == "" && id != null) {
            model.addAttribute("listaEntidades", populateEntityList(nombre, id, exactMatch));
            model.addAttribute("headers", entityHeaders.get("ENTIDADES"));
        }

        // Se los devuelvo para que mantengan persistencia

        model.addAttribute("atributos", atributos);
        model.addAttribute("entidad", entidad);
        model.addAttribute("nombre", nombre);
        model.addAttribute("id", id);
        model.addAttribute("exactMatch", exactMatch);
        return "busqueda";
    }

    private void addEntityToList(List<Map<String, Object>> list, Long id, String name, String type) {
        Map<String, Object> entityMap = new HashMap<>();
        entityMap.put("id", id);
        entityMap.put("nombre", name);
        entityMap.put("tipo", type);
        list.add(entityMap);
    }

    private List<Map<String, Object>> populateEntityList(String nombre, Long id, Boolean exactMatch) {
        List<Map<String, Object>> list = new ArrayList<>();
        userService.filterUsers(nombre, null, id, exactMatch).forEach(user -> addEntityToList(list, user.getId(), user.getUsername(), user.getRol().name()));
        empresaService.filterEmpresas(nombre, id).forEach(empresa -> addEntityToList(list, empresa.getId(), empresa.getNombreEmpresa(), "EMPRESA"));
        paisService.filterPais(id).forEach(pais -> addEntityToList(list, pais.getId(), pais.getNombrePais(), "PAIS"));
        areaService.filterAreas(id).forEach(area -> addEntityToList(list, area.getId(), area.getNombreArea(), "AREA"));
        contratoService.filterContratos(id).forEach(contrato -> addEntityToList(list, contrato.getId(), contrato.getAsesor() + " Asesora a:" + contrato.getEmpresa().getNombreEmpresa(), "CONTRATO"));
        return list;
    }

}
