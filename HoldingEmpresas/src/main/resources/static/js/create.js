// Wait for the DOM to load
document.addEventListener("DOMContentLoaded", function () {
    // Get the role dropdown and the VENDEDOR/ASESOR-specific fields
    const roleSelect = document.getElementById("rol");
    const vendedorFields = document.getElementById("vendedor-fields");
    const asesorFields = document.getElementById("asesor-fields"); // If you have fields for Asesor

    const empresaField = document.getElementById("empresa")
    const areasField = document.getElementById("areasoperadas")
    const titulacionField = document.getElementById("titulacion")

    // Function to handle role change
    function handleRoleChange() {
        const selectedRole = roleSelect.value;

        // Show VENDEDOR fields if the selected role is VENDEDOR
        if (selectedRole === "VENDEDOR") {
            vendedorFields.style.display = "block";
            asesorFields.style.display = "none";  // Hide Asesor fields if shown
            empresaField.setAttribute("required", "required");
            areasField.removeAttribute("required")
            titulacionField.removeAttribute("required")
        }
        // Show ASESOR fields if the selected role is ASESOR
        else if (selectedRole === "ASESOR") {
            vendedorFields.style.display = "none";  // Hide Vendedor fields if shown
            asesorFields.style.display = "block";
            empresaField.removeAttribute("required");
            areasField.setAttribute("required", "required")
            titulacionField.setAttribute("required", "required")
        }
        // Hide both if no specific role is selected
        else {
            vendedorFields.style.display = "none";
            asesorFields.style.display = "none";
            empresaField.removeAttribute("required");
            areasField.removeAttribute("required")
            titulacionField.removeAttribute("required")
        }
    }

    // Run the handler on page load in case a role is pre-selected or page is refreshed
    handleRoleChange();

    // Add an event listener to the role dropdown
    roleSelect.addEventListener("change", handleRoleChange);
});
