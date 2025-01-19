import {mapaAtributos} from './Data/mapaAtributos.js';

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
