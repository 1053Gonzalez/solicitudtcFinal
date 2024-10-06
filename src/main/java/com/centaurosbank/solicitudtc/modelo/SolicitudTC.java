package com.centaurosbank.solicitudtc.modelo;

import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Getter;
import lombok.Setter;

@Entity
public class SolicitudTC {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Getter
	@Setter
	@Column
	private int id_solicitud;

	@Getter
	@Setter
	@Column
	private String nombres;

	@Getter
	@Setter
	@Column(nullable = false)
	private String primer_apellido;

	@Getter
	@Setter
	@Column(nullable = true)
	private String segundo_apellido;

	@Getter
	@Setter
	@Column(nullable = false)
	private Date fecha_nacimiento;

	@Getter
	@Setter
	@Column(nullable = false, length = 20)
	private String cedula_ciudadania;

	@Getter
	@Setter
	@Temporal(TemporalType.DATE)
	@Column(nullable = false)	private Date fecha_expedicion;

	@Getter
	@Setter
	@Column(nullable = false, length = 20)
	private String numero_celular;

	@Getter
	@Setter
	@Column(nullable = false, length = 100)
	private String correo_electronico;

	@Getter
	@Setter
	@Column(nullable = false, length = 200)
	private String direccion;

	@Getter
	@Setter
	@Column(nullable = false)
	private boolean autoriza_tratamiento_datos;

}
