package com.sistemagestion.ViewController.devolucion;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.sistemagestion.entity.devolucion.DetalleDevolucion;
import com.sistemagestion.entity.devolucion.Devolucion;

import com.sistemagestion.repository.*;
import com.sistemagestion.repository.devolucion.DevolucionRepository;
import com.sistemagestion.repository.venta.VentaRepository;

@Controller
@RequestMapping("/devoluciones")
public class DevolucionViewController {

    @Autowired
    private DevolucionRepository devolucionRepository;

    @Autowired
    private VentaRepository ventaRepository;

    @Autowired
    private PoloRepository poloRepository;

    @Autowired
    private TallaRepository tallaRepository;

    @Autowired
    private ColorRepository colorRepository;

    // 🔹 LISTAR DEVOLUCIONES
    @GetMapping
    public String listar(Model model) {
        model.addAttribute("devoluciones", devolucionRepository.findAll());
        return "lista_devoluciones";
    }

    // 🔹 FORMULARIO NUEVA DEVOLUCIÓN
    @GetMapping("/nueva")
    public String nueva(Model model) {
        model.addAttribute("devolucion", new Devolucion());
        model.addAttribute("ventas", ventaRepository.findAll());
        model.addAttribute("polos", poloRepository.findAll());
        model.addAttribute("tallas", tallaRepository.findAll());
        model.addAttribute("colores", colorRepository.findAll());
        return "crear_devolucion";
    }

    // 🔥 GUARDAR DEVOLUCIÓN
    @PostMapping("/guardar")
    public String guardar(@ModelAttribute Devolucion devolucion) {

        // 🔗 RELACIÓN PADRE-HIJO
        for (DetalleDevolucion d : devolucion.getDetalles()) {
            d.setDevolucion(devolucion);
        }

        devolucionRepository.save(devolucion);

        return "redirect:/devoluciones";
    }

    // 🔍 VER DETALLE
    @GetMapping("/{id}")
    public String ver(@PathVariable Long id, Model model) {

        Devolucion devolucion = devolucionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Devolución no encontrada"));

        model.addAttribute("devolucion", devolucion);

        return "detalle_devolucion";
    }
}