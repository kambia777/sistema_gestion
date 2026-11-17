package com.sistemagestion.repository.produccion;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sistemagestion.entity.produccion.OrdenProduccion;

public interface OrdenProduccionRepository extends JpaRepository<OrdenProduccion, Long>  {
	
	List<OrdenProduccion> findByEstado(String estado);
	List<OrdenProduccion> findByIdAndEstado(Long id, String estado);
}
