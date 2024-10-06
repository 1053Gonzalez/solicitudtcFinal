package com.centaurosbank.solicitudtc.controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import com.centaurosbank.solicitudtc.modelo.SolicitudTC;
import com.centaurosbank.solicitudtc.repositorio.Repositorio;

@Controller
public class ControladorWeb {

    @Autowired
    private Repositorio repo;  // Interacción con la base de datos

    // Página de inicio
    @GetMapping("/index")
    public String index() {
        return "index";  // Redirecciona a la vista index.html
    }

    // Mostrar formulario para solicitar tarjeta
    @GetMapping("/solicitartarjeta")
    public String mostrarFormulario(Model model) {
        model.addAttribute("solicitud", new SolicitudTC());  // Se pasa una nueva instancia de SolicitudTC
        return "solicitartarjeta";  // Redirecciona a la vista solicitartarjeta.html
    }

    // Procesar solicitud de tarjeta
    @PostMapping("/solicitartarjeta")
    public String registrarSolicitud(SolicitudTC solicitud) {
        repo.save(solicitud);  // Guarda la solicitud en la base de datos
        return "redirect:/solicitudes";  // Redirige a la página de solicitudes
    }

    // Mostrar lista de solicitudes
    @GetMapping("/solicitudes")
    public String verSolicitudes(Model model) {
        model.addAttribute("solicitudes", repo.findAll());  // Pasa todas las solicitudes al modelo
        return "solicitudes";  // Redirecciona a la vista solicitudes.html
    }
}
