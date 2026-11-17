package com.sistemagestion.repository.almacen;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.sistemagestion.entity.almacen.DetalleSalidaAlmacen;

public interface DetalleSalidaAlmacenRepository extends JpaRepository<DetalleSalidaAlmacen, Long>{
	
    @Query("""
            SELECT COALESCE(SUM(d.cantidad), 0)
            FROM DetalleSalidaAlmacen d
            WHERE d.polo.id = :poloId
            AND d.talla.id = :tallaId
            AND d.color.id = :colorId
        """)
	
    int sumarSalidas(
            @Param("poloId") Long poloId,
            @Param("tallaId") Long tallaId,
            @Param("colorId") Long colorId
        );

}
