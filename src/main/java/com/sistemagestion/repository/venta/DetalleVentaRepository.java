package com.sistemagestion.repository.venta;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sistemagestion.entity.venta.DetalleVenta;

public interface DetalleVentaRepository extends JpaRepository<DetalleVenta, Long> {

}
