package com.sistemagestion.repository.venta;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sistemagestion.entity.venta.Venta;

public interface VentaRepository extends JpaRepository<Venta, Long> {
	
	
	List<Venta> findByTiendaId(Long tiendaId);

}
