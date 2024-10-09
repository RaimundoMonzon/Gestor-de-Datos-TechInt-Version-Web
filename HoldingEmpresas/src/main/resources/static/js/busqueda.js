document.addEventListener("DOMContentLoaded", function () {
    const entidadSelecta = document.getElementById("entidad");
    const vendedorFields = document.getElementById("vendedor-fields");
    const asesorFields = document.getElementById("asesor-fields");

    const empresaField = document.getElementById("empresa")
    const areasField = document.getElementById("areasOperadas")
    const titulacionField = document.getElementById("titulacion")

    function handleRoleChange() {
        const selectedRole = roleSelect.value;

        // Muestra los campos extra del VENDEDOR
        if (selectedRole === "VENDEDOR") {
            vendedorFields.style.display = "block";
            asesorFields.style.display = "none";  
            empresaField.setAttribute("required", "required");
            areasField.removeAttribute("required")
            titulacionField.removeAttribute("required")
        }
        // Oculta ambos campos en caso de que ninguno este seleccionado.
        else {
            vendedorFields.style.display = "none";
            asesorFields.style.display = "none";
            empresaField.removeAttribute("required");
            areasField.removeAttribute("required")
            titulacionField.removeAttribute("required")
        }
    }

    // Corre el controlador al caragr la pagina en caso de qeu algun rol este pre-selecto.
    handleRoleChange();

    // Agrega un event listener al dropdown del rol.
    roleSelect.addEventListener("change", handleRoleChange);
});
