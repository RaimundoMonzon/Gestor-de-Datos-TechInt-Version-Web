const mapaAtributos = {
    'USERS': [
        { value: 'id', text: 'ID' },
        { value: 'username', text: 'Nombre' },
        { value: 'rol', text: 'Rol' },
        { value: 'fechaIngreso', text: 'Fecha Ingreso' },
        { value: 'titulacion', text: 'Titulación' },
        { value: 'areasOperadas', text: 'Áreas Operadas' },
        { value: 'ingresos', text: 'Ingresos' },
        { value: 'empresa', text: 'Empresa' },
        { value: 'manager', text: 'Manager' },
        { value: 'subContratados', text: 'Vendedores Contratados' }
    ],
    'ADMIN': [
        { value: 'id', text: 'ID' },
        { value: 'username', text: 'Nombre' },
        { value: 'rol', text: 'Rol' },
        { value: 'fechaIngreso', text: 'Fecha Ingreso' }
    ],
    'ASESOR': [
        { value: 'id', text: 'ID' },
        { value: 'username', text: 'Nombre' },
        { value: 'rol', text: 'Rol' },
        { value: 'fechaIngreso', text: 'Fecha Ingreso' },
        { value: 'titulacion', text: 'Titulación' },
        { value: 'areasOperadas', text: 'Áreas Operadas' }
    ],
    'VENDEDOR': [
        { value: 'id', text: 'ID' },
        { value: 'username', text: 'Nombre' },
        { value: 'rol', text: 'Rol' },
        { value: 'fechaIngreso', text: 'Fecha Ingreso' },
        { value: 'ingresos', text: 'Ingresos' },
        { value: 'empresa', text: 'Empresa' },
        { value: 'manager', text: 'Manager' },
        { value: 'subContratados', text: 'Vendedores Contratados' }
    ],
    'EMPRESA': [
        { value: 'id', text: 'ID' },
        { value: 'nombreEmpresa', text: 'Nombre' },
        { value: 'sede', text: 'Sede' },
        { value: 'fechaIngreso', text: 'Fecha Ingreso' },
        { value: 'fta', text: 'Facturación' },
        { value: 'paisesOperados', text: 'Paises Operados' },
        { value: 'areasOperadasEmp', text: 'Areas Operadas' },
        { value: 'vendedoresContratados', text: 'Vendedores Contratados' },
        { value: 'asesoresContratados', text: 'Asesores Contratados' }
    ],
    'AREA': [
        { value: 'id', text: 'ID' },
        { value: 'nombreArea', text: 'Nombre' },
        { value: 'descripcion', text: 'Descripcion' }
    ],
    'PAIS': [
        { value: 'id', text: 'ID' },
        { value: 'nombrePais', text: 'Nombre' },
        { value: 'capital', text: 'Capital' },
        { value: 'poblacion', text: 'Poblacion' },
        { value: 'pbi', text: 'PBI' }
    ],
    'CONTRATO': [
        { value: 'id', text: 'ID' },
        { value: 'asesorACargo', text: 'Asesor a Cargo' },
        { value: 'empresaCliente', text: 'Empresa Cliente' },
        { value: 'fechaCelebracion', text: 'Fecha Celebracion' },
        { value: 'areasAsesoradas', text: 'Areas Asesoradas' }
    ]
};

document.addEventListener('DOMContentLoaded', function () {
    const entidadSelect = new Choices('#entidad', { removeItemButton: false });
    const selectorAtributos = new Choices('#atributos', { removeItemButton: true });

    const selectorEntidad = document.getElementById("entidad");
    const atributosField = document.getElementById("atributos");
    const atributosLabel = document.getElementById("atributosLabel");
    const selectorAtributosContainer = selectorAtributos.containerOuter.element;

    // Ocultar o no la busqueda por id que podria y que no buscar.

    function handleEntidadChange() {
        //selectorAtributos.clearChoices();
        if (selectorEntidad.value !== "") {
            atributosField.setAttribute("required", "required");
            selectorAtributosContainer.style.display = "block";
            atributosLabel.style.display = "block";

            switch (selectorEntidad.value) {
                case "USERS": addToSelectorAtributos(mapaAtributos.USERS); break;
                case "ADMIN": addToSelectorAtributos(mapaAtributos.ADMIN); break;
                case "ASESOR": addToSelectorAtributos(mapaAtributos.ASESOR); break;
                case "VENDEDOR": addToSelectorAtributos(mapaAtributos.VENDEDOR); break;
                case "EMPRESA": addToSelectorAtributos(mapaAtributos.EMPRESA); break;
                default:
                    atributosField.removeAttribute("required");
                    selectorAtributosContainer.style.display = "none";
                    atributosLabel.style.display = "none";
                    break;
            }
        } else {
            atributosField.removeAttribute("required");
            selectorAtributosContainer.style.display = "none";
            atributosLabel.style.display = "none";
        }

    }

    handleEntidadChange();

    selectorEntidad.onchange = function () {
        selectorAtributos.removeActiveItems();
        handleEntidadChange();
    }

    function addToSelectorAtributos(arr) {
        const options = arr.map(element => {
            return { value: element.value, label: element.text };
        });

        selectorAtributos.setChoices(options, 'value', 'label', true);
    }
});
