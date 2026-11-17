package com.sistemagestion.ViewController.venta;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.sistemagestion.entity.venta.DetalleVenta;
import com.sistemagestion.entity.venta.Venta;
import com.sistemagestion.repository.*;
import com.sistemagestion.repository.venta.DetalleVentaRepository;
import com.sistemagestion.repository.venta.VentaRepository;

@Controller
@RequestMapping("/ventas")
public class VentaViewController {

    @Autowired
    private VentaRepository ventaRepository;

    @Autowired
    private DetalleVentaRepository detalleVentaRepository;

    @Autowired
    private PoloRepository poloRepository;

    @Autowired
    private TallaRepository tallaRepository;

    @Autowired
    private ColorRepository colorRepository;

    @Autowired
    private TiendaRepository tiendaRepository;

    // 🔹 LISTAR VENTAS
    @GetMapping
    public String listar(Model model) {
        model.addAttribute("ventas", ventaRepository.findAll());
        return "lista_ventas";
    }

    // 🔹 NUEVA VENTA
    @GetMapping("/nueva")
    public String nueva(Model model) {
        model.addAttribute("venta", new Venta());
        model.addAttribute("polos", poloRepository.findAll());
        model.addAttribute("tallas", tallaRepository.findAll());
        model.addAttribute("colores", colorRepository.findAll());
        model.addAttribute("tiendas", tiendaRepository.findAll());
        return "crear_venta";
    }

    // 🔥 GUARDAR VENTA
    @PostMapping("/guardar")
    public String guardar(@ModelAttribute Venta venta) {

        double total = 0;

        for (DetalleVenta d : venta.getDetalles()) {

            // 🔗 RELACIÓN
            d.setVenta(venta);

            // 💰 CALCULAR TOTAL
            total += d.getCantidad() * d.getPrecioUnitario();
        }

        venta.setTotal(total);

        ventaRepository.save(venta);

        return "redirect:/ventas";
    }

    // 🔹 VER DETALLE
    @GetMapping("/{id}")
    public String ver(@PathVariable Long id, Model model) {

        Venta venta = ventaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Venta no encontrada"));

        model.addAttribute("venta", venta);

        return "detalle_venta";
    }
    
    @GetMapping("/tienda/{id}")
    public String ventasPorTienda(@PathVariable Long id, Model model) {

        model.addAttribute("ventas", ventaRepository.findByTiendaId(id));
        model.addAttribute("tienda", tiendaRepository.findById(id).orElse(null));

        return "ventas_por_tienda";
    }
}