package com.sistemagestion.repository.almacen;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.sistemagestion.entity.almacen.DetalleEntradaAlmacen;

public interface DetalleEntradaAlmacenRepository extends JpaRepository<DetalleEntradaAlmacen, Long> {
	
	
    @Query("""
            SELECT COALESCE(SUM(d.cantidad), 0)
            FROM DetalleEntradaAlmacen d
            WHERE d.polo.id = :poloId
            AND d.talla.id = :tallaId
            AND d.color.id = :colorId
        """)
	
    int sumarEntradas(
            @Param("poloId") Long poloId,
            @Param("tallaId") Long tallaId,
            @Param("colorId") Long colorId
        );

}
