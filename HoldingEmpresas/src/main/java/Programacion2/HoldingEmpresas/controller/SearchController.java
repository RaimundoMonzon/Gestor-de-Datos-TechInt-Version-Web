package Programacion2.HoldingEmpresas.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.ui.Model;

import Programacion2.HoldingEmpresas.data.MapaAtributos;
import Programacion2.HoldingEmpresas.services.*;
import lombok.AllArgsConstructor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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

        if (exactMatch == null) {
            exactMatch = false;
        }

        List<String> headers = new ArrayList<>();
        if (atributos != null || (entidad != null && entidad != "")) {
            Map<String, String> attributeMap = MapaAtributos.mapaAtributos.get(entidad).stream()
                    .collect(Collectors.toMap(a -> a.value, a -> a.text, (e1, e2) -> e1, LinkedHashMap::new));

            if (atributos != null) {
                for (String atributo : attributeMap.keySet()) {
                    if (atributos.contains(atributo)) {
                        headers.add(attributeMap.get(atributo));
                    }
                }
            } else {
                headers = attributeMap.values().stream().collect(Collectors.toList());
            }
            model.addAttribute("headers", headers);
        }

        // Se filtran los datos
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
                    model.addAttribute("usuarios",
                            userService.filterUsers(nombre, !entidad.equals("USERS") ? entidad : null, id, exactMatch));
                    break;
            }
        } else if (entidad == "" && id != null) {
            model.addAttribute("listaEntidades", populateEntityList(nombre, id, exactMatch));
            model.addAttribute("headers", MapaAtributos.mapaAtributos.get("ENTIDADES").stream().map(a -> a.text)
                    .collect(Collectors.toList()));
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
        userService.filterUsers(nombre, null, id, exactMatch)
                .forEach(user -> addEntityToList(list, user.getId(), user.getUsername(), user.getRol().name()));
        empresaService.filterEmpresas(nombre, id)
                .forEach(empresa -> addEntityToList(list, empresa.getId(), empresa.getNombreEmpresa(), "EMPRESA"));
        paisService.filterPais(id).forEach(pais -> addEntityToList(list, pais.getId(), pais.getNombrePais(), "PAIS"));
        areaService.filterAreas(id).forEach(area -> addEntityToList(list, area.getId(), area.getNombreArea(), "AREA"));
        contratoService.filterContratos(id).forEach(contrato -> addEntityToList(list, contrato.getId(),
                contrato.getAsesor() + " Asesora a:" + contrato.getEmpresa().getNombreEmpresa(), "CONTRATO"));
        return list;
    }

}
