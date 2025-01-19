package Programacion2.HoldingEmpresas.data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MapaAtributos {
    
    public static class Atributo {
        public String value;
        public String text;

        public Atributo(String value, String text) {
            this.value = value;
            this.text = text;
        }
    }

    public static final Map<String, List<Atributo>> mapaAtributos = new HashMap<>();

    static {
        mapaAtributos.put("USERS", new ArrayList<>());
        mapaAtributos.get("USERS").add(new Atributo("id", "ID"));
        mapaAtributos.get("USERS").add(new Atributo("username", "Nombre"));
        mapaAtributos.get("USERS").add(new Atributo("rol", "Rol"));
        mapaAtributos.get("USERS").add(new Atributo("titulacion", "Titulación"));
        mapaAtributos.get("USERS").add(new Atributo("areasOperadas", "Áreas Operadas"));
        mapaAtributos.get("USERS").add(new Atributo("manager", "Manager"));
        mapaAtributos.get("USERS").add(new Atributo("ingresos", "Ingresos"));
        mapaAtributos.get("USERS").add(new Atributo("empresa", "Empresa"));
        mapaAtributos.get("USERS").add(new Atributo("subContratados", "Vendedores Contratados"));
        mapaAtributos.get("USERS").add(new Atributo("fechaIngreso", "Fecha Ingreso"));

        mapaAtributos.put("ADMIN", new ArrayList<>());
        mapaAtributos.get("ADMIN").add(new Atributo("id", "ID"));
        mapaAtributos.get("ADMIN").add(new Atributo("username", "Nombre"));
        mapaAtributos.get("ADMIN").add(new Atributo("rol", "Rol"));
        mapaAtributos.get("ADMIN").add(new Atributo("fechaIngreso", "Fecha Ingreso"));

        mapaAtributos.put("ASESOR", new ArrayList<>());
        mapaAtributos.get("ASESOR").add(new Atributo("id", "ID"));
        mapaAtributos.get("ASESOR").add(new Atributo("username", "Nombre"));
        mapaAtributos.get("ASESOR").add(new Atributo("rol", "Rol"));
        mapaAtributos.get("ASESOR").add(new Atributo("titulacion", "Titulación"));
        mapaAtributos.get("ASESOR").add(new Atributo("areasOperadas", "Áreas Operadas"));
        mapaAtributos.get("ASESOR").add(new Atributo("fechaIngreso", "Fecha Ingreso"));

        mapaAtributos.put("VENDEDOR", new ArrayList<>());
        mapaAtributos.get("VENDEDOR").add(new Atributo("id", "ID"));
        mapaAtributos.get("VENDEDOR").add(new Atributo("username", "Nombre"));
        mapaAtributos.get("VENDEDOR").add(new Atributo("rol", "Rol"));
        mapaAtributos.get("VENDEDOR").add(new Atributo("manager", "Manager"));
        mapaAtributos.get("VENDEDOR").add(new Atributo("ingresos", "Ingresos"));
        mapaAtributos.get("VENDEDOR").add(new Atributo("empresa", "Empresa"));
        mapaAtributos.get("VENDEDOR").add(new Atributo("subContratados", "Vendedores Contratados"));
        mapaAtributos.get("VENDEDOR").add(new Atributo("fechaIngreso", "Fecha Ingreso"));

        mapaAtributos.put("EMPRESA", new ArrayList<>());
        mapaAtributos.get("EMPRESA").add(new Atributo("id", "ID"));
        mapaAtributos.get("EMPRESA").add(new Atributo("nombreEmpresa", "Nombre"));
        mapaAtributos.get("EMPRESA").add(new Atributo("sede", "Sede"));
        mapaAtributos.get("EMPRESA").add(new Atributo("fta", "Facturación"));
        mapaAtributos.get("EMPRESA").add(new Atributo("paisesOperados", "Paises Operados"));
        mapaAtributos.get("EMPRESA").add(new Atributo("areasOperadasEmp", "Areas Operadas"));
        mapaAtributos.get("EMPRESA").add(new Atributo("vendedoresContratados", "Vendedores Contratados"));
        mapaAtributos.get("EMPRESA").add(new Atributo("asesoresContratados", "Asesores Contratados"));
        mapaAtributos.get("EMPRESA").add(new Atributo("fechaIngreso", "Fecha Ingreso"));

        mapaAtributos.put("AREA", new ArrayList<>());
        mapaAtributos.get("AREA").add(new Atributo("id", "ID"));
        mapaAtributos.get("AREA").add(new Atributo("nombreArea", "Nombre"));
        mapaAtributos.get("AREA").add(new Atributo("descripcion", "Descripcion"));

        mapaAtributos.put("PAIS", new ArrayList<>());
        mapaAtributos.get("PAIS").add(new Atributo("id", "ID"));
        mapaAtributos.get("PAIS").add(new Atributo("nombrePais", "Nombre"));
        mapaAtributos.get("PAIS").add(new Atributo("capital", "Capital"));
        mapaAtributos.get("PAIS").add(new Atributo("poblacion", "Poblacion"));
        mapaAtributos.get("PAIS").add(new Atributo("pbi", "PBI"));

        mapaAtributos.put("CONTRATO", new ArrayList<>());
        mapaAtributos.get("CONTRATO").add(new Atributo("id", "ID"));
        mapaAtributos.get("CONTRATO").add(new Atributo("asesorACargo", "Asesor a Cargo"));
        mapaAtributos.get("CONTRATO").add(new Atributo("empresaCliente", "Empresa Cliente"));
        mapaAtributos.get("CONTRATO").add(new Atributo("fechaCelebracion", "Fecha Celebracion"));
        mapaAtributos.get("CONTRATO").add(new Atributo("areasAsesoradas", "Areas Asesoradas"));

        mapaAtributos.put("ENTIDADES", new ArrayList<>());
        mapaAtributos.get("ENTIDADES").add(new Atributo("id", "ID"));
        mapaAtributos.get("ENTIDADES").add(new Atributo("nombre", "Nombre"));
        mapaAtributos.get("ENTIDADES").add(new Atributo("tipo", "Tipo"));
    }
}
