package com.sistemagestion.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sistemagestion.entity.dto.StockDTO;
import com.sistemagestion.entity.venta.DetalleVenta;
import com.sistemagestion.entity.venta.Venta;
import com.sistemagestion.entity.almacen.*;
import com.sistemagestion.entity.devolucion.DetalleDevolucion;
import com.sistemagestion.entity.devolucion.Devolucion;
import com.sistemagestion.repository.almacen.EntradaAlmacenRepository;
import com.sistemagestion.repository.almacen.SalidaAlmacenRepository;
import com.sistemagestion.repository.devolucion.DevolucionRepository;
import com.sistemagestion.repository.venta.VentaRepository;

import java.util.*;

@Service
public class StockService {

    @Autowired
    private EntradaAlmacenRepository entradaRepo;

    @Autowired
    private SalidaAlmacenRepository salidaRepo;

    @Autowired
    private VentaRepository ventaRepo;

    @Autowired
    private DevolucionRepository devolucionRepo;

    public List<StockDTO> calcularStock() {

        Map<String, StockDTO> mapa = new HashMap<>();


        // 🟢 1. ENTRADAS ALMACÉN (+)
        for (EntradaAlmacen e : entradaRepo.findAll()) {
            for (DetalleEntradaAlmacen d : e.getDetalles()) {

                String key = d.getPolo().getId() + "-" +
                        d.getTalla().getId() + "-" +
                        d.getColor().getId() + "ALMACEN";

                mapa.putIfAbsent(key, new StockDTO(
                        d.getPolo().getNombre(),
                        d.getTalla().getNombre(),
                        d.getColor().getNombre(),
                        0,
                        0,
                        "ALMACEN"
                ));

                mapa.get(key).setStockAlmacen(
                        mapa.get(key).getStockAlmacen() + d.getCantidad()
                );
                mapa.get(key).setStockTienda(
                        mapa.get(key).getStockTienda() + 0);
                mapa.get(key).setVentas(mapa.get(key).getVentas() + 0);
            }
        }

        // 🔴 2. SALIDAS ALMACÉN (- almacén / + tienda)
        for (SalidaAlmacen s : salidaRepo.findAll()) {
            for (DetalleSalidaAlmacen d : s.getDetalles()) {

                String keyTienda = d.getPolo().getId() + "-" +
                        d.getTalla().getId() + "-" +
                        d.getColor().getId() + s.getTienda().getNombre();
                        
                String keyAlmacen =  d.getPolo().getId() + "-" +
                        d.getTalla().getId() + "-" +
                        d.getColor().getId() + "ALMACEN";

                mapa.putIfAbsent(keyTienda, new StockDTO(
                        d.getPolo().getNombre(),
                        d.getTalla().getNombre(),
                        d.getColor().getNombre(),
                        0,
                        0,
                        s.getTienda().getNombre()
                ));

                // resta almacén
                mapa.get(keyAlmacen).setStockAlmacen(
                        mapa.get(keyAlmacen).getStockAlmacen() - d.getCantidad()
                );

                // suma tienda
                mapa.get(keyTienda).setStockTienda(
                        mapa.get(keyTienda).getStockTienda() + d.getCantidad()
                );
            }
        }

        // 🔴 3. VENTAS (- tienda)
        for (Venta v : ventaRepo.findAll()) {
            for (DetalleVenta d : v.getDetalles()) {

                String key = d.getPolo().getId() + "-" +
                        d.getTalla().getId() + "-" +
                        d.getColor().getId() + v.getTienda().getNombre();

                mapa.putIfAbsent(key, new StockDTO(
                        d.getPolo().getNombre(),
                        d.getTalla().getNombre(),
                        d.getColor().getNombre(), 
                        0,
                        0,
                        v.getTienda().getNombre()
                ));

                mapa.get(key).setStockTienda(
                        mapa.get(key).getStockTienda() - d.getCantidad());
                        
                mapa.get(key).setVentas(mapa.get(key).getVentas() + d.getCantidad());
               
            }
        }

        // 🟢 4. DEVOLUCIONES (+ tienda)
        for (Devolucion dev : devolucionRepo.findAll()) {
            for (DetalleDevolucion d : dev.getDetalles()) {

                String key = generarKey(d) + dev.getVenta().getTienda();

                mapa.putIfAbsent(key, new StockDTO(
                        d.getPolo().getNombre(),
                        d.getTalla().getNombre(),
                        d.getColor().getNombre(),
                        0,
                        0,
                        "DEVOLUCIONES"
                ));

                mapa.get(key).setStockTienda(
                        mapa.get(key).getStockTienda() + d.getCantidad()
                );
            }
        }

        return new ArrayList<>(mapa.values());
    }

    // 🔑 CLAVE ÚNICA
    private String generarKey(Object d) {

        if (d instanceof DetalleEntradaAlmacen de) {
            return de.getPolo().getId() + "-" + de.getTalla().getId() + "-" + de.getColor().getId();
        }

        if (d instanceof DetalleSalidaAlmacen ds) {
            return ds.getPolo().getId() + "-" + ds.getTalla().getId() + "-" + ds.getColor().getId();
        }

        if (d instanceof DetalleVenta dv) {
            return dv.getPolo().getId() + "-" + dv.getTalla().getId() + "-" + dv.getColor().getId();
        }

        if (d instanceof DetalleDevolucion dd) {
            return dd.getPolo().getId() + "-" + dd.getTalla().getId() + "-" + dd.getColor().getId();
        }

        return "";
    }
}