package com.centaurosbank.solicitudtc.controlador;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.centaurosbank.solicitudtc.modelo.SolicitudTC;
import com.centaurosbank.solicitudtc.repositorio.Repositorio;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
public class ControladorTest {

	@Autowired
	private MockMvc mockMvc;

	@Mock
	private Repositorio repo;

	@InjectMocks
	private Controlador controlador;

	@BeforeEach
	public void setup() {
		MockitoAnnotations.openMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(controlador).build();
	}

	// Prueba para GET: obtener todas las solicitudes
	@Test
	public void testGetSolicitudes() throws Exception {
		SolicitudTC solicitud1 = new SolicitudTC();
		solicitud1.setNombres("Juan");
		SolicitudTC solicitud2 = new SolicitudTC();
		solicitud2.setNombres("Maria");

		when(repo.findAll()).thenReturn(Arrays.asList(solicitud1, solicitud2));

		mockMvc.perform(get("/solicitudestc")).andExpect(status().isOk());
	}

	// Prueba para POST: crear una nueva solicitud
	@Test
	public void testPostSolicitud() throws Exception {
		SolicitudTC nuevaSolicitud = new SolicitudTC();
		nuevaSolicitud.setNombres("Carlos");
		nuevaSolicitud.setPrimer_apellido("Gomez");

		// Simular guardar la solicitud
		when(repo.save(any(SolicitudTC.class))).thenReturn(nuevaSolicitud);

		// Convertir la solicitud a JSON
		ObjectMapper objectMapper = new ObjectMapper();
		String solicitudJson = objectMapper.writeValueAsString(nuevaSolicitud);

		mockMvc.perform(post("/grabar").contentType(MediaType.APPLICATION_JSON).content(solicitudJson))
				.andExpect(status().isOk()).andExpect(content().string("Solicitud de Tarjeta realizada"));
	}

	// Prueba para PUT: actualizar una solicitud existente
	@Test
	public void testUpdateSolicitud() throws Exception {
		SolicitudTC solicitudExistente = new SolicitudTC();
		solicitudExistente.setId_solicitud(1);
		solicitudExistente.setNombres("Juan");

		SolicitudTC solicitudActualizada = new SolicitudTC();
		solicitudActualizada.setId_solicitud(1);
		solicitudActualizada.setNombres("Juan Carlos");

		when(repo.findById(1)).thenReturn(Optional.of(solicitudExistente));
		when(repo.save(any(SolicitudTC.class))).thenReturn(solicitudActualizada);

		ObjectMapper objectMapper = new ObjectMapper();
		String solicitudJson = objectMapper.writeValueAsString(solicitudActualizada);

		mockMvc.perform(put("/editar/1").contentType(MediaType.APPLICATION_JSON).content(solicitudJson))
				.andExpect(status().isOk()).andExpect(content().string("Solicitud de Tarjeta actualizada"));
	}

}