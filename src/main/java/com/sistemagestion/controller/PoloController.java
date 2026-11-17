package com.sistemagestion.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sistemagestion.entity.Polo;
import com.sistemagestion.repository.PoloRepository;

@RestController
@RequestMapping("/polos")
public class PoloController {
	
	private final PoloRepository poloRepository;
	
	
	public PoloController(PoloRepository poloRepository) {
        this.poloRepository = poloRepository;
    }

    @GetMapping
    public List<Polo> obtenerPolos() {
        return poloRepository.findAll();
    }

    @PostMapping
    public Polo crearPolo(@RequestBody Polo polo) {
        return poloRepository.save(polo);
    }

}
