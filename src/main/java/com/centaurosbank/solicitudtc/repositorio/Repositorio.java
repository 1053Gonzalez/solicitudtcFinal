package com.centaurosbank.solicitudtc.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import com.centaurosbank.solicitudtc.modelo.SolicitudTC;

public interface Repositorio extends JpaRepository<SolicitudTC, Integer>{//Repository  Persona=SolicitudTC

	
}
