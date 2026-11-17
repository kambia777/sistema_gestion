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

import com.sistemagestion.entity.Tienda;
import com.sistemagestion.repository.TiendaRepository;

@RestController
@RequestMapping("/api/tiendas")
public class TiendaController {
	
	@Autowired
    private TiendaRepository tiendaRepository;

    @GetMapping
    public List<Tienda> getAllTiendas() {
        return tiendaRepository.findAll();
    }

    @PostMapping
    public Tienda createTienda(@RequestBody Tienda tienda) {
        return tiendaRepository.save(tienda);
    }
    
    @GetMapping("/{id}")
    public Tienda getTiendaById(@PathVariable Long id) {
        return tiendaRepository.findById(id).orElseThrow(() -> new RuntimeException("Tienda no encontrada"));
    }

    @PutMapping("/{id}")
    public Tienda updateTienda(@PathVariable Long id, @RequestBody Tienda tiendaDetails) {
        Tienda tienda = tiendaRepository.findById(id).orElseThrow(() -> new RuntimeException("Tienda no encontrada"));
        tienda.setNombre(tiendaDetails.getNombre());
        tienda.setDireccion(tiendaDetails.getDireccion());
        tienda.setTelefono(tiendaDetails.getTelefono());
        return tiendaRepository.save(tienda);
    }

    @DeleteMapping("/{id}")
    public String deleteTienda(@PathVariable Long id) {
        tiendaRepository.deleteById(id);
        return "Tienda eliminada";
    }

}
