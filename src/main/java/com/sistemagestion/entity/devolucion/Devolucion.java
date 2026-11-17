package com.sistemagestion.entity.devolucion;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.List;

import com.sistemagestion.entity.venta.Venta;

@Entity
@Table(name = "devolucion")
public class Devolucion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate fecha;

    private String motivo;

    // 🔗 RELACIÓN CON VENTA
    @ManyToOne
    @JoinColumn(name = "venta_id")
    private Venta venta;

    // 🔗 DETALLES
    @OneToMany(mappedBy = "devolucion", cascade = CascadeType.ALL)
    private List<DetalleDevolucion> detalles;

    // GETTERS Y SETTERS
    public Long getId() { return id; }

    public LocalDate getFecha() { return fecha; }
    public void setFecha(LocalDate fecha) { this.fecha = fecha; }

    public String getMotivo() { return motivo; }
    public void setMotivo(String motivo) { this.motivo = motivo; }

    public Venta getVenta() { return venta; }
    public void setVenta(Venta venta) { this.venta = venta; }

    public List<DetalleDevolucion> getDetalles() { return detalles; }
    public void setDetalles(List<DetalleDevolucion> detalles) { this.detalles = detalles; }
}