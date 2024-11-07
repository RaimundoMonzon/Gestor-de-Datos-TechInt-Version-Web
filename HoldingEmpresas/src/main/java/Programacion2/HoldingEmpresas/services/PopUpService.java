package Programacion2.HoldingEmpresas.services;

import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

@Service
public class PopUpService {

    public static void showPasswordErrorPopUp(Model model) {
        model.addAttribute("showPopUp", true);
        model.addAttribute("popupType", "error");
        model.addAttribute("popupTitle", "Error");
        model.addAttribute("popupMessage", "Contraseña Incorrecta, Intentelo Nuevamente.");
        return;
    }

    public static void showSuccessPopUp(Model model) {
        model.addAttribute("showPopUp", true);
        model.addAttribute("popupType", "success");
        model.addAttribute("popupTitle", "Exito!");
        model.addAttribute("popupMessage", "La operacion fue realizada con exito.");
        return;
    }
}
