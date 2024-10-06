package com.centaurosbank.solicitudtc.controlador;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.centaurosbank.solicitudtc.modelo.SolicitudTC;
import com.centaurosbank.solicitudtc.repositorio.Repositorio;

@RestController
public class Controlador {

	@Autowired
	private Repositorio repo; // metodo repo que tendra todas las intecacciones con BD

	@GetMapping()
	public String index() {
		return "Conectado";

	}

	// consultar
	@GetMapping("solicitudestc") // personas = Solicitudestc
	public List<SolicitudTC> getSolicitudes() {

		return repo.findAll(); // pemite buscar todo dentro de la vase de datos
	}

	// crear
	@PostMapping("grabar")
	public String post(@RequestBody SolicitudTC solicitud) {// el objeto de tipo SolicitudTc que llega por peticion
		repo.save(solicitud); // Guarda la nueva solicitud en la base de datos
		return "Solicitud de Tarjeta realizada";
	}

	// editar
	@PutMapping("editar/{id}")
	public String update(@PathVariable Integer id, @RequestBody SolicitudTC solicitud) {
		SolicitudTC updateSolicitudTC = repo.findById(id).get();
		updateSolicitudTC.setNombres(solicitud.getNombres());
		updateSolicitudTC.setPrimer_apellido(solicitud.getPrimer_apellido());
		updateSolicitudTC.setSegundo_apellido(solicitud.getSegundo_apellido());
		updateSolicitudTC.setFecha_nacimiento(solicitud.getFecha_nacimiento());
		updateSolicitudTC.setCedula_ciudadania(solicitud.getCedula_ciudadania());
		updateSolicitudTC.setFecha_expedicion(solicitud.getFecha_expedicion());
		updateSolicitudTC.setNumero_celular(solicitud.getNumero_celular());
		updateSolicitudTC.setCorreo_electronico(solicitud.getCorreo_electronico());
		updateSolicitudTC.setDireccion(solicitud.getDireccion());
		updateSolicitudTC.setAutoriza_tratamiento_datos(solicitud.isAutoriza_tratamiento_datos());

		// Guardar los cambios en la base de datos
		repo.save(updateSolicitudTC);

		return "Solicitud de Tarjeta actualizada"; // Mensaje de respuesta

	}

	@DeleteMapping("eliminar/{id}")
	public String delete(@PathVariable Integer id) {
		SolicitudTC deleteSolicitudTC = repo.findById(id).get();
		repo.delete(deleteSolicitudTC); // Elimina la solicitud de la base de datos
		return "Solicitud de Tarjeta eliminada"; // Mensaje de respuesta
	}
	
	

}
