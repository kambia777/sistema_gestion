package com.sistemagestion.ViewController.almacen;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.sistemagestion.entity.almacen.*;
import com.sistemagestion.repository.*;
import com.sistemagestion.repository.almacen.DetalleEntradaAlmacenRepository;
import com.sistemagestion.repository.almacen.DetalleSalidaAlmacenRepository;
import com.sistemagestion.repository.almacen.SalidaAlmacenRepository;

@Controller
@RequestMapping("/salidas")
public class SalidaAlmacenViewController {

    @Autowired
    private SalidaAlmacenRepository salidaRepository;

    @Autowired
    private DetalleEntradaAlmacenRepository detalleEntradaRepository;

    @Autowired
    private DetalleSalidaAlmacenRepository detalleSalidaRepository;

    @Autowired
    private PoloRepository poloRepository;

    @Autowired
    private TallaRepository tallaRepository;

    @Autowired
    private ColorRepository colorRepository;

    @Autowired
    private TiendaRepository tiendaRepository;

    // 📋 LISTAR
    @GetMapping
    public String listar(Model model) {
        model.addAttribute("salidas", salidaRepository.findAll());
        return "lista_salidas_almacen";
    }

    // ➕ NUEVA SALIDA
    @GetMapping("/nueva")
    public String nueva(Model model) {
        model.addAttribute("salida", new SalidaAlmacen());
        model.addAttribute("polos", poloRepository.findAll());
        model.addAttribute("tallas", tallaRepository.findAll());
        model.addAttribute("colores", colorRepository.findAll());
        model.addAttribute("tiendas", tiendaRepository.findAll());
        return "crear_salida_almacen";
    }

    // 💾 GUARDAR
    @PostMapping("/guardar")
    public String guardar(@ModelAttribute SalidaAlmacen salida, Model model) {

        // 🔥 VALIDAR STOCK
        for (DetalleSalidaAlmacen d : salida.getDetalles()) {

            int entradas = detalleEntradaRepository
                    .sumarEntradas(d.getPolo().getId(), d.getTalla().getId(), d.getColor().getId());

            int salidas = detalleSalidaRepository
                    .sumarSalidas(d.getPolo().getId(), d.getTalla().getId(), d.getColor().getId());

            int stock = entradas - salidas;

            if (d.getCantidad() > stock) {
                model.addAttribute("error", "Stock insuficiente");

                // ⚠️ recargar listas
                model.addAttribute("polos", poloRepository.findAll());
                model.addAttribute("tallas", tallaRepository.findAll());
                model.addAttribute("colores", colorRepository.findAll());
                model.addAttribute("tiendas", tiendaRepository.findAll());

                return "crear_salida_almacen";
            }
        }

        // 🔗 RELACIÓN PADRE-HIJO
        for (DetalleSalidaAlmacen d : salida.getDetalles()) {
            d.setSalida(salida);
        }

        salidaRepository.save(salida);

        return "redirect:/salidas";
    }

    // 🔍 VER DETALLE
    @GetMapping("/{id}")
    public String ver(@PathVariable Long id, Model model) {
        model.addAttribute("salida", salidaRepository.findById(id).orElse(null));
        return "detalle_salida_almacen";
    }
}