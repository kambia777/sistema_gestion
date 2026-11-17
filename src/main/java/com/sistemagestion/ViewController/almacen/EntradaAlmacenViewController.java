package com.sistemagestion.ViewController.almacen;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import com.sistemagestion.entity.almacen.EntradaAlmacen;
import com.sistemagestion.repository.almacen.EntradaAlmacenRepository;

@Controller
@RequestMapping("/entradas")
public class EntradaAlmacenViewController {


    @Autowired
    private EntradaAlmacenRepository entradaRepository;
       
    
    // 📋 LISTAR ENTRADAS
    @GetMapping
    public String listarEntradas(Model model) {

        List<EntradaAlmacen> entradas = entradaRepository.findAll();

        model.addAttribute("entradas", entradas);

        return "entrada_almacen"; // 👉 entradas.html
    }
    
    @GetMapping("/{id}")
    public String verEntrada(@PathVariable Long id, Model model) {

        EntradaAlmacen entrada = entradaRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Entrada no encontrada"));

        model.addAttribute("entrada", entrada);

        return "detalle_entrada_almacen";
    }
    
}