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

import com.sistemagestion.entity.Color;
import com.sistemagestion.repository.ColorRepository;

@RestController
@RequestMapping("/api/colors")
public class ColorController {

    @Autowired
    private ColorRepository colorRepository;

    // Listar todos
    @GetMapping
    public List<Color> getAllColors() {
        return colorRepository.findAll();
    }

    // Crear nuevo
    @PostMapping
    public Color createColor(@RequestBody Color color) {
        return colorRepository.save(color);
    }

    // Obtener por id
    @GetMapping("/{id}")
    public Color getColorById(@PathVariable Long id) {
        return colorRepository.findById(id).orElseThrow(() -> new RuntimeException("Color no encontrado"));
    }

    // Actualizar
    @PutMapping("/{id}")
    public Color updateColor(@PathVariable Long id, @RequestBody Color colorDetails) {
        Color color = colorRepository.findById(id).orElseThrow(() -> new RuntimeException("Color no encontrado"));
        color.setNombre(colorDetails.getNombre());
        return colorRepository.save(color);
    }

    // Eliminar
    @DeleteMapping("/{id}")
    public String deleteColor(@PathVariable Long id) {
        colorRepository.deleteById(id);
        return "Color eliminado";
    }

}
