package com.sistemagestion.ViewController.stock;

import com.sistemagestion.entity.dto.StockDTO;
import com.sistemagestion.repository.ColorRepository;
import com.sistemagestion.repository.PoloRepository;
import com.sistemagestion.repository.TallaRepository;
import com.sistemagestion.repository.TiendaRepository;
import com.sistemagestion.service.StockService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class StockViewController {

    @Autowired
    private StockService stockService;
    @Autowired
    private TiendaRepository tiendaRepository;
    @Autowired
    private PoloRepository poloRepository;
    @Autowired
    private TallaRepository tallaRepository;
    @Autowired
    private ColorRepository colorRepository;

    // 📊 VER STOCK GENERAL
    @GetMapping("/stock")
    public String verStock(Model model) {

        List<StockDTO> stock = stockService.calcularStock();

        model.addAttribute("stock", stock);
       // model.addAttribute("stock", stockService.obtenerStockPorTienda());
        model.addAttribute("tiendas", tiendaRepository.findAll());
        model.addAttribute("polos", poloRepository.findAll());
        model.addAttribute("tallas", tallaRepository.findAll());
        model.addAttribute("colores", colorRepository.findAll());

        return "stock"; // stock.html
    }
    
    @GetMapping("/dashboard")
    public String dashboard(Model model) {

        var stock = stockService.calcularStock();

        int totalStock = stock.stream()
                .mapToInt(s -> s.getStockAlmacen() + s.getStockTienda())
                .sum();

        long stockBajo = stock.stream()
                .filter(s -> (s.getStockAlmacen() + s.getStockTienda()) < 5)
                .count();

        model.addAttribute("stock", stock);
        model.addAttribute("totalStock", totalStock);
        model.addAttribute("stockBajo", stockBajo);

        return "dashboard_stock";
    }
}