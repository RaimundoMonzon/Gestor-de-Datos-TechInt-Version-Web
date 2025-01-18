package Programacion2.HoldingEmpresas.services;

import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

@Service
public class PopUpService {

    public static void showPopUp(Model model, String popupType, String popupTitle, String popupMessage) {
        model.addAttribute("showPopUp", true);
        model.addAttribute("popupType", popupType);
        model.addAttribute("popupTitle", popupTitle);
        model.addAttribute("popupMessage", popupMessage);
    }

    public static void showPasswordErrorPopUp(Model model) {
        showPopUp(model, "error", "Error", "Contraseña Incorrecta, Intentelo Nuevamente.");
    }

    public static void showSuccessPopUp(Model model) {
        showPopUp(model, "success", "Exito!", "La operacion fue realizada con exito.");
    }

    public static void showCredentialErrorPopUp(Model model) {
        showPopUp(model, "error", "Error", "Credenciales Incorrectas, Intentelo Nuevamente.");
    }

    public static void showPasswordMismatchPopUp(Model model) {
        showPopUp(model, "error", "Error", "Las contraseñas no coinciden, Intentelo Nuevamente.");
    }

    public static void showUsernameAlreadyTakenPopUp(Model model) {
        showPopUp(model, "error", "Error", "El nombre de usuario ya esta tomado, Intentelo Nuevamente.");
    }

    public static void showNameAlreadyTakenPopUp(Model model) {
        showPopUp(model, "error", "Error", "Ese nombre ya esta tomado, Intentelo Nuevamente.");
    }

    public static void showCannotDeleteSelfPopUp(Model model) {
        showPopUp(model, "error", "Error", "No puedes eliminarte a ti mismo, Intentelo Nuevamente.");
    }
}
