package com.sistemagestion.ViewController.produccion;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.sistemagestion.entity.almacen.DetalleEntradaAlmacen;
import com.sistemagestion.entity.almacen.EntradaAlmacen;
import com.sistemagestion.entity.produccion.DetalleProduccion;
import com.sistemagestion.entity.produccion.OrdenProduccion;
import com.sistemagestion.repository.ColorRepository;
import com.sistemagestion.repository.PoloRepository;
import com.sistemagestion.repository.TallaRepository;
import com.sistemagestion.repository.almacen.EntradaAlmacenRepository;
import com.sistemagestion.repository.produccion.OrdenProduccionRepository;

@Controller
public class OrdenProduccionViewController {
	
	@Autowired
    private OrdenProduccionRepository ordenRepository;
	
    private final PoloRepository poloService;
    private final ColorRepository colorService;
    private final TallaRepository tallaService;

    public OrdenProduccionViewController(PoloRepository poloService, ColorRepository colorService, TallaRepository tallaService) {
        this.poloService = poloService;
		this.colorService = colorService;
		this.tallaService = tallaService;
    }

    @GetMapping("/ordenes")
    public String listarOrdenes(
            @RequestParam(required = false) Long id,
            @RequestParam(required = false) String estado,
            Model model) {

        List<OrdenProduccion> ordenes;

        if (id != null && estado != null && !estado.isEmpty()) {
            ordenes = ordenRepository.findByIdAndEstado(id, estado);

        } else if (id != null) {
            ordenes = ordenRepository.findById(id)
                    .map(List::of)
                    .orElse(List.of());

        } else if (estado != null && !estado.isEmpty()) {
            ordenes = ordenRepository.findByEstado(estado);

        } else {
            ordenes = ordenRepository.findAll();
        }

        model.addAttribute("ordenes", ordenes);
        return "ordenes";
    }
    
    @GetMapping("/ordenes/{id}")
    public String verOrden(@PathVariable Long id, Model model) {

        OrdenProduccion orden = ordenRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No encontrada"));

        model.addAttribute("orden", orden);

        return "detalle_orden";
    }
    
    @GetMapping("/ordenes/pendientes")
    public String pendientes(Model model) {
        model.addAttribute("ordenes", ordenRepository.findByEstado("Pendiente"));
        return "ordenes";
    }

    @GetMapping("/ordenes/completadas")
    public String completadas(Model model) {
        model.addAttribute("ordenes", ordenRepository.findByEstado("Completada"));
        return "ordenes";
    }
    
    @GetMapping("/ordenes/nueva")
    public String mostrarFormulario(Model model) {
    	OrdenProduccion orden = new OrdenProduccion();
        orden.setDetalles(new ArrayList<>()); // importante
        model.addAttribute("orden", orden);
        model.addAttribute("polos", poloService.findAll());
        model.addAttribute("colores", colorService.findAll());
        model.addAttribute("tallas", tallaService.findAll());
        return "crear_orden";
    }

    @PostMapping("/ordenes")
    public String guardarOrden(@ModelAttribute OrdenProduccion orden) {
    	
        if (orden.getDetalles() == null || orden.getDetalles().isEmpty()) {
            throw new RuntimeException("La orden debe tener al menos un detalle");
        }

        // 🔥 MUY IMPORTANTE: asignar la relación
        for (DetalleProduccion d : orden.getDetalles()) {
            d.setOrdenProduccion(orden);
        }

        ordenRepository.save(orden);
        return "redirect:/ordenes";
    }
    
    @GetMapping("/ordenes/editar/{id}")
    public String editarOrden(@PathVariable Long id, Model model) {

        OrdenProduccion orden = ordenRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Orden no encontrada"));

        model.addAttribute("orden", orden);

        model.addAttribute("polos", poloService.findAll());
        model.addAttribute("tallas", tallaService.findAll());
        model.addAttribute("colores", colorService.findAll());

        return "editar_orden";
    }
    
    @PostMapping("/ordenes/actualizar/{id}")
    public String actualizarOrden(@PathVariable Long id,
                                  @ModelAttribute OrdenProduccion form) {

        OrdenProduccion orden = ordenRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No encontrada"));

        
        String estadoAnterior = orden.getEstado();
        
        // datos generales
        orden.setFecha(form.getFecha());
        orden.setResponsable(form.getResponsable());
        orden.setEstado(form.getEstado());
        orden.setObservaciones(form.getObservaciones());

        // detalles
        orden.getDetalles().clear();

        for (DetalleProduccion d : form.getDetalles()) {
            d.setOrdenProduccion(orden);
            orden.getDetalles().add(d);
        }

        OrdenProduccion ordenActualizada = ordenRepository.save(orden);
        
        // 🔥 AQUÍ ESTÁ LA MAGIA
        if (!"Completada".equals(estadoAnterior) && "Completada".equals(ordenActualizada.getEstado())) {
            crearEntradaDesdeOrden(ordenActualizada);
        }

        return "redirect:/ordenes";
    }
    
    @Autowired
    private EntradaAlmacenRepository entradaRepository;

    private void crearEntradaDesdeOrden(OrdenProduccion orden) {

        EntradaAlmacen entrada = new EntradaAlmacen();
        entrada.setFecha(orden.getFecha());
        entrada.setObservaciones("Generada desde orden #" + orden.getId());

        List<DetalleEntradaAlmacen> detallesEntrada = new ArrayList<>();

        for (DetalleProduccion d : orden.getDetalles()) {

            DetalleEntradaAlmacen detalle = new DetalleEntradaAlmacen();

            detalle.setEntrada(entrada);
            detalle.setPolo(d.getPolo());
            detalle.setTalla(d.getTalla());
            detalle.setColor(d.getColor());
            detalle.setCantidad(d.getCantidad());

            detallesEntrada.add(detalle);
        }

        entrada.setDetalles(detallesEntrada);

        entradaRepository.save(entrada);
    }

}
