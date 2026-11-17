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

import com.sistemagestion.entity.produccion.OrdenProduccion;
import com.sistemagestion.repository.produccion.OrdenProduccionRepository;

@RestController
@RequestMapping("/api/ordenes")
public class OrdenProduccionController {

	@Autowired
    private OrdenProduccionRepository ordenRepository;

    @GetMapping
    public List<OrdenProduccion> getAllOrdenes() {
        return ordenRepository.findAll();
    }

    @PostMapping
    public OrdenProduccion createOrden(@RequestBody OrdenProduccion orden) {
        return ordenRepository.save(orden);
    }

    @GetMapping("/{id}")
    public OrdenProduccion getOrdenById(@PathVariable Long id) {
        return ordenRepository.findById(id).orElseThrow(() -> new RuntimeException("Orden no encontrada"));
    }
    
    @PutMapping("/{id}")
    public OrdenProduccion updateOrden(@PathVariable Long id, @RequestBody OrdenProduccion ordenDetails) {
        OrdenProduccion orden = ordenRepository.findById(id).orElseThrow(() -> new RuntimeException("Orden no encontrada"));
        orden.setFecha(ordenDetails.getFecha());
        orden.setEstado(ordenDetails.getEstado());
        orden.setObservaciones(ordenDetails.getObservaciones());
        return ordenRepository.save(orden);
    }

    @DeleteMapping("/{id}")
    public String deleteOrden(@PathVariable Long id) {
        ordenRepository.deleteById(id);
        return "Orden eliminada";
    }
	
}
