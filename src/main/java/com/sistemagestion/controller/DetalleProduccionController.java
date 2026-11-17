package com.sistemagestion.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sistemagestion.entity.produccion.DetalleProduccion;
import com.sistemagestion.repository.produccion.DetalleProduccionRepository;

@RestController
@RequestMapping("/api/detalles-produccion")
public class DetalleProduccionController {
	
	@Autowired
    private DetalleProduccionRepository detalleRepository;

    @GetMapping
    public List<DetalleProduccion> getAllDetalles() {
        return detalleRepository.findAll();
    }

    @PostMapping
    public DetalleProduccion createDetalle(@RequestBody DetalleProduccion detalle) {
        return detalleRepository.save(detalle);
    }

    @GetMapping("/{id}")
    public DetalleProduccion getDetalleById(@PathVariable Long id) {
        return detalleRepository.findById(id).orElseThrow(() -> new RuntimeException("Detalle no encontrado"));
    }
    
    @PutMapping("/{id}")
    public DetalleProduccion updateDetalle(@PathVariable Long id, @RequestBody DetalleProduccion detalleDetails) {
        DetalleProduccion detalle = detalleRepository.findById(id).orElseThrow(() -> new RuntimeException("Detalle no encontrado"));
        detalle.setOrdenProduccion(detalleDetails.getOrdenProduccion());
        detalle.setPolo(detalleDetails.getPolo());
        detalle.setTalla(detalleDetails.getTalla());
        detalle.setColor(detalleDetails.getColor());
        detalle.setCantidad(detalleDetails.getCantidad());
        detalle.setFechaEntrega(detalleDetails.getFechaEntrega());
        detalle.setEstado(detalleDetails.getEstado());
        return detalleRepository.save(detalle);
    }

    @DeleteMapping("/{id}")
    public String deleteDetalle(@PathVariable Long id) {
        detalleRepository.deleteById(id);
        return "Detalle eliminado";
    }

}
