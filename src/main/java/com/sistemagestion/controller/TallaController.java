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

import com.sistemagestion.entity.Talla;
import com.sistemagestion.repository.TallaRepository;

@RestController
@RequestMapping("/api/tallas")
public class TallaController {
	
    @Autowired
    private TallaRepository tallaRepository;

    @GetMapping
    public List<Talla> getAllTallas() {
        return tallaRepository.findAll();
    }

    @PostMapping
    public Talla createTalla(@RequestBody Talla talla) {
        return tallaRepository.save(talla);
    }
    
    @GetMapping("/{id}")
    public Talla getTallaById(@PathVariable Long id) {
        return tallaRepository.findById(id).orElseThrow(() -> new RuntimeException("Talla no encontrada"));
    }

    @PutMapping("/{id}")
    public Talla updateTalla(@PathVariable Long id, @RequestBody Talla tallaDetails) {
        Talla talla = tallaRepository.findById(id).orElseThrow(() -> new RuntimeException("Talla no encontrada"));
        talla.setNombre(tallaDetails.getNombre());
        return tallaRepository.save(talla);
    }

    @DeleteMapping("/{id}")
    public String deleteTalla(@PathVariable Long id) {
        tallaRepository.deleteById(id);
        return "Talla eliminada";
    }

}
